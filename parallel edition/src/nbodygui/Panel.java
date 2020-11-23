package nbodygui;

import nbody.NbodySolver;
import nbodygui.exceptions.DurationMillisOutOfBoundsException;

import javax.swing.*;
import java.awt.*;

import static nbodygui.Panels.*;

public class Panel extends JPanel {

    private final NbodySolver solver;
    private final int durationMillis;

    public Panel(NbodySolver solver, int durationMillis) {
        if (durationMillis < MIN_DURATION_MILLIS || durationMillis > MAX_DURATION_MILLIS) {
            throw new DurationMillisOutOfBoundsException();
        }

        this.solver = solver;

        this.durationMillis = durationMillis;
    }

    public void start() {
        for (int t = 0; t <= durationMillis; t = t + solver.dt()) {
            solver.recalcBodiesCoords();
            repaint();
        }
        solver.stop();
    }

    private void drawRandomPoints(Graphics gr) {
        Graphics2D graphics = (Graphics2D) gr;
        graphics.setPaint(DEFAULT_POINTS_COLOR);

        for (int i = 0; i < solver.n(); i++) {
            int x = solver.bodyX(i);
            int y = solver.bodyY(i);
            graphics.fillOval(x, y, DEFAULT_POINTS_SIZE, DEFAULT_POINTS_SIZE);
        }
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        drawRandomPoints(gr);
    }
}
