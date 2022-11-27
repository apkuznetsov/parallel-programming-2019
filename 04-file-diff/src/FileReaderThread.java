import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FileReaderThread extends Thread {

    private final String filePathName;

    private String currLine;
    private boolean isMyTurn = false;
    private boolean isMyWorkFinished = false;

    private final Lock locker = new ReentrantLock();
    private final Condition readingNextLine = locker.newCondition();
    private final Condition readingNextLineIsFinished = locker.newCondition();


    public FileReaderThread(String filePathName) {
        this.filePathName = filePathName;
    }

    public void run() {

        /* для получения блокировки вызывается метод lock(),
         * а после окончания работы с общими ресурсами вызывается метод unlock(), который снимает блокировку: */
        locker.lock();
        try {

            final Scanner scanner = new Scanner(new File(filePathName));
            while (true) {
                if (!isMyTurn) {
                    readingNextLine.await();
                }

                if (scanner.hasNextLine()) {
                    currLine = scanner.nextLine();
                } else {
                    currLine = null;
                }

                isMyTurn = false;

                if (isMyWorkFinished) {
                    scanner.close();
                    break;
                }

                readingNextLineIsFinished.signal();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void runReadLine() {
        locker.lock();
        isMyTurn = true;
        readingNextLine.signal();
        locker.unlock();
    }

    public String currLine() {
        locker.lock();
        if (isMyTurn) {
            // causes the current thread to wait until it is signalled
            readingNextLineIsFinished.awaitUninterruptibly();
        }
        locker.unlock();

        return currLine;
    }

    public void finish() {
        locker.lock();
        isMyWorkFinished = true;
        readingNextLine.signal();
        locker.unlock();
    }
}
