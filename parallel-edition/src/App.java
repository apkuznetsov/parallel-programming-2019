import nbody.Coords;
import nbody.NbodySettings;
import nbody.NbodySolver;
import nbodygui.Frame;
import nbodygui.Panel;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class App {

    public static void main(String[] args) throws IOException {

        final AppSettingsParser parser = new AppSettingsParser();
        final AppSettings settings = parser.parseSettings();

        final Dimension coordsBounds = new Dimension(settings.width - 100, settings.height - 100);
        final Coords[] randomCoordsArr = randomCoordsArr(settings.bodiesNum, coordsBounds);
        final NbodySettings nbodySettings = new NbodySettings(settings.bodyMass, settings.deltaTime, settings.errorDistance, settings.threadsNum);
        final NbodySolver solver = new NbodySolver(randomCoordsArr, nbodySettings);

        final Panel panel = new Panel(solver, settings.durationMillis);

        final Frame frame = new Frame(settings.width, settings.height, panel);
        frame.setVisible(true);

        long startMillis = System.currentTimeMillis();
        panel.start();
        long finishMillis = System.currentTimeMillis();
        long consumedMillis = finishMillis - startMillis;
        System.out.println("Consumed millis: " + consumedMillis);
    }

    public static Coords[] randomCoordsArr(int num, Dimension coordsBounds) {

        final Coords[] randomCoordsArr = new Coords[num];

        int x, y;
        final Random random = new Random();

        for (int i = 0; i < randomCoordsArr.length; i++) {
            x = Math.abs(random.nextInt()) % coordsBounds.width;
            y = Math.abs(random.nextInt()) % coordsBounds.height;
            randomCoordsArr[i] = new Coords(x, y);
        }

        return randomCoordsArr;
    }
}
