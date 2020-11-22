import nbody.Body;
import nbody.Coords;
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
        final NbodySolver solver = new NbodySolver(randomCoordsArr, settings.bodyMass, settings.deltaTime, settings.errorDistance, settings.threadsNum);

        final Panel panel = new Panel(solver, settings.durationMillis);

        final Frame frame = new Frame(settings.width, settings.height, panel);
        frame.setVisible(true);

        //final NbodySolver solver = new NbodySolver(pseudoEarthAndMoon(250, 200), NbodySolvers.DEFAULT_DELTA_TIME);
        //final Panel panel = new Panel(solver, Panels.MAX_DURATION_MILLIS);
        //final Frame frame = new Frame(Frames.DEFAULT_WIDTH, Frames.DEFAULT_HEIGHT, panel);
        //frame.setVisible(true);
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

    public static Body[] pseudoEarthAndMoon(double x, double y) {
        Body earth = new Body(new Coords(x, y), 6e10);
        Body moon = new Body(new Coords(earth.p().x(), earth.p().y() + 300), 6e4, new Coords(0.02, 0.0));
        return new Body[]{earth, moon};
    }
}
