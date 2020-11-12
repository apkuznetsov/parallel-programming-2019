import nbody.Coords;
import nbody.NbodySolvers;
import nbodygui.Frame;

import java.awt.*;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        Frame frame = new Frame(NbodySolvers.MIN_BODIES_NUM);
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
