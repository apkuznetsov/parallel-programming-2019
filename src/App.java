import nbody.Coords;
import nbody.NbodySolver;
import nbody.NbodySolvers;
import nbodygui.Frame;
import nbodygui.Frames;

import java.awt.*;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        final Dimension coordsBounds = new Dimension(Frames.DEFAULT_WIDTH - 100, Frames.DEFAULT_HEIGHT - 100);
        final Coords[] randomCoordsArr = randomCoordsArr(NbodySolvers.MIN_BODIES_NUM, coordsBounds);
        final NbodySolver solver = new NbodySolver(randomCoordsArr, NbodySolvers.MIN_DELTA_TIME);

        final nbodygui.Panel panel = new nbodygui.Panel(solver);
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
