import nbody.Coords;
import nbody.NbodySolver;
import nbody.NbodySolvers;
import nbodygui.Frame;
import nbodygui.Frames;
import nbodygui.Panels;

import java.awt.*;
import java.io.IOException;
import java.util.Random;


public class App {

    public static void main(String[] args) throws IOException {

        final AppSettingsParser settingsParser = new AppSettingsParser();

        final Dimension coordsBounds = new Dimension(Frames.DEFAULT_WIDTH - 100, Frames.DEFAULT_HEIGHT - 100);
        final Coords[] randomCoordsArr = randomCoordsArr(NbodySolvers.MIN_BODIES_NUM, coordsBounds);
        final NbodySolver solver = new NbodySolver(randomCoordsArr, NbodySolvers.MIN_DELTA_TIME);

        final nbodygui.Panel panel = new nbodygui.Panel(solver, Panels.MIN_DURATION_MILLIS);
        final Frame frame = new Frame(panel);
        frame.setVisible(true);
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
