package nbodygui;

import nbody.Coords;
import nbody.NbodySolvers;
import nbody.NbodySolver;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import static nbodygui.Surfaces.TITLE;

public class Frame extends JFrame {

    public Frame() {
        super(TITLE);
        initGui();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setVisible(true);
        });
    }

    private void initGui() {

        Coords[] coords = new Coords[NbodySolvers.MIN_BODIES_NUM];
        int x, y;
        Random random = new Random();

        for (int i = 0; i < coords.length; i++) {
            x = Math.abs(random.nextInt()) % Surfaces.WIDTH;
            y = Math.abs(random.nextInt()) % Surfaces.HEIGHT;
            coords[i] = new Coords(x, y);
        }

        final NbodySolver solver = new NbodySolver(coords, NbodySolvers.MIN_DELTA_TIME);
        final Surface surface = new Surface(solver);
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.timer();
                timer.stop();
            }
        });

        setSize(Surfaces.WIDTH, Surfaces.HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
