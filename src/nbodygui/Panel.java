package nbodygui;

import nbody.NbodySolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nbodygui.Panels.DEFAULT_POINT_COLOR;
import static nbodygui.Panels.DEFAULT_POINT_SIZE;

public class Panel extends JPanel implements ActionListener {

    private final NbodySolver solver;
    private final Timer timer;
    private final int durationMillis;
    private int consumedMillis;

    public Panel(NbodySolver solver, int durationMillis) {
        this.solver = solver;
        timer = new Timer(solver.dt(), this);
        timer.start();

        this.durationMillis = durationMillis;
        consumedMillis = 0;
    }

    public Timer timer() {
        return timer;
    }

    private void drawRandomPoints(Graphics gr) {
        Graphics2D graphics = (Graphics2D) gr;
        graphics.setPaint(DEFAULT_POINT_COLOR);

        for (int i = 0; i < solver.n(); i++) {
            int x = solver.bodyX(i);
            int y = solver.bodyY(i);
            graphics.fillOval(x, y, DEFAULT_POINT_SIZE, DEFAULT_POINT_SIZE);
        }
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        solver.recalcBodiesCoords();
        drawRandomPoints(gr);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (consumedMillis >= durationMillis) {
            timer.stop();
            return;
        }

        consumedMillis += timer.getDelay();
        repaint();
    }
}
