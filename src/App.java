import nbody.Coords;
import nbody.NbodySolver;
import nbody.NbodySolvers;
import nbodygui.Frame;
import nbodygui.Frames;
import nbodygui.Panel;
import nbodygui.Panels;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class App {

    public static void main(String[] args) throws IOException {

        final AppSettingsParser parser = new AppSettingsParser();
        final int parsedWidth = (parser.width() == null) ? Frames.DEFAULT_WIDTH : parser.width();
        final int parsedHeight = (parser.height() == null) ? Frames.DEFAULT_HEIGHT : parser.height();
        final int parsedDurationMillis = (parser.durationMillis() == null) ? Panels.DEFAULT_DURATION_MILLIS : parser.durationMillis();

        final Dimension coordsBounds = new Dimension(parsedWidth - 100, parsedHeight - 100);
        final Coords[] randomCoordsArr = randomCoordsArr(NbodySolvers.MIN_BODIES_NUM, coordsBounds);
        final NbodySolver solver = new NbodySolver(randomCoordsArr, NbodySolvers.MIN_DELTA_TIME);

        final Panel panel = new Panel(solver, parsedDurationMillis);

        final Frame frame = new Frame(parsedWidth, parsedHeight, panel);
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
