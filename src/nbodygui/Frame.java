package nbodygui;

import nbody.Coords;
import nbody.NbodySolver;
import nbody.NbodySolvers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Frame extends JFrame {

    public Frame(Panel panel) {
        super(Panels.DEFAULT_TITLE);
        init(panel);
    }

    private void init(Panel panel) {


        add(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = panel.timer();
                timer.stop();
            }
        });

        setSize(Panels.DEFAULT_WIDTH + 100, Panels.DEFAULT_HEIGHT + 100);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Coords[] randomCoordsArr(int num, Dimension bounds) {

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
