import nbody.Coords;
import nbody.NbodySolver;
import nbody.NbodySolvers;
import nbodygui.Frame;
import nbodygui.Frames;
import nbodygui.Panel;
import nbodygui.Panels;

import java.awt.*;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        Coords[] randomCoordsArr = randomCoordsArr(NbodySolvers.MIN_BODIES_NUM, new Dimension(Frames.DEFAULT_WIDTH - 100, Frames.DEFAULT_HEIGHT - 100));

        final NbodySolver solver = new NbodySolver(randomCoordsArr, NbodySolvers.MIN_DELTA_TIME);
        final nbodygui.Panel panel = new Panel(solver);

        Frame frame = new Frame(panel);
        frame.setVisible(true);
    }

    public static Coords[] randomCoordsArr(int num, Dimension bounds) {

        Coords[] randomCoordsArr = new Coords[num];

        int x, y;
        Random random = new Random();

        for (int i = 0; i < randomCoordsArr.length; i++) {
            x = Math.abs(random.nextInt()) % bounds.width;
            y = Math.abs(random.nextInt()) % bounds.height;
            randomCoordsArr[i] = new Coords(x, y);
        }

        return randomCoordsArr;
    }
}
