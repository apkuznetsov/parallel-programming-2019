import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static final int LINES_NUM = 10;
    public static final int SYMBOLS_NUM = 10;
    public static boolean SHOULD_IT_WORK_IN_PARALLEL = true;
    public static boolean SHOULD_IT_DELETE_FILES_AFTER_WORK = true;

    public static void main(String[] args) throws InterruptedException, IOException {

        System.out.println("Лабораторная работа №.\n" +
                "Синхронизация параллельных процессов. Сравнение файлов\n" +
                "Выполнил студент группы 6138 Кузнецов А.П.\n");

        System.out.println("НАСТРОЙКИ (задаются статическими полями класса Main):\n" +
                "кол-во строк    в каждом файле .... " + LINES_NUM + "\n" +
                "кол-во символов в каждой строке ... " + SYMBOLS_NUM + "\n" +
                "работать параллельно? ............. " + SHOULD_IT_WORK_IN_PARALLEL + "\n" +
                "удалить файлы после работы? ....... " + SHOULD_IT_DELETE_FILES_AFTER_WORK + "\n");

        final String[] filesNames = createFiles(LINES_NUM, SYMBOLS_NUM);

        if (SHOULD_IT_WORK_IN_PARALLEL) {
            parallel(filesNames);
        } else {
            sequential(filesNames);
        }

        if (SHOULD_IT_DELETE_FILES_AFTER_WORK) {
            Files.deleteIfExists(Paths.get(filesNames[0]));
            Files.deleteIfExists(Paths.get(filesNames[1]));
        }
    }

    private static String[] createFiles(int linesNum, int symbolsNum) {

        final String file1Name = "file1-" + linesNum + "x" + symbolsNum + ".txt";
        final String file2Name = "file2-" + linesNum + "x" + symbolsNum + ".txt";

        try (PrintWriter writer1 = new PrintWriter(file1Name, StandardCharsets.UTF_8);
             PrintWriter writer2 = new PrintWriter(file2Name, StandardCharsets.UTF_8)) {

            String strToWrite = "a".repeat(symbolsNum);

            for (int rowIndex = 0; rowIndex < linesNum - 1; ++rowIndex) {
                writer1.println(strToWrite);
                writer2.println(strToWrite);
            }

            writer1.println("b");
            writer2.println("c");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{file1Name, file2Name};
    }

    private static void parallel(String[] filesNames) throws InterruptedException {
        FileReaderThread frt1 = new FileReaderThread(filesNames[0]);
        FileReaderThread frt2 = new FileReaderThread(filesNames[1]);
        ParallelFilesComparatorThread pfct = new ParallelFilesComparatorThread(frt1, frt2);

        frt1.start();
        frt2.start();
        pfct.start();
        
        frt1.join();
        frt2.join();
        pfct.join();
    }

    private static void sequential(String[] filesNames) throws InterruptedException {
        SequentialFilesComparatorThread sfct = new SequentialFilesComparatorThread(filesNames);
        sfct.start();
        sfct.join();
    }
}
