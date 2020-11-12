package nbodygui;

import nbody.Bodies;
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

        Point[] point = new Point[Bodies.MIN_N];
        int x, y;
        Random random = new Random();

        for (int i = 0; i < point.length; i++) {
            x = Math.abs(random.nextInt()) % Surfaces.WIDTH;
            y = Math.abs(random.nextInt()) % Surfaces.HEIGHT;
            point[i] = new Point(x, y);
        }

        final NbodySolver solver = new NbodySolver(point.length, Bodies.MIN_DT, point);
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
