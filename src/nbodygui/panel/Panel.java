package nbodygui.panel;

import nbody.NbodySolver;
import nbodygui.exceptions.DurationMillisOutOfBoundsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nbodygui.panel.Panels.*;

public class Panel extends JPanel implements ActionListener {

    private final NbodySolver solver;
    private final Timer timer;
    private final int durationMillis;
    private int consumedMillis;

    public Panel(NbodySolver solver, PanelSettings settings) {
        durationMillis = settings.durationMillis == null ? DEFAULT_DURATION_MILLIS : settings.durationMillis;
        if (durationMillis < MIN_DURATION_MILLIS || durationMillis > MAX_DURATION_MILLIS) {
            throw new DurationMillisOutOfBoundsException();
        }

        this.solver = solver;
        timer = new Timer(solver.dt(), this);
        timer.start();

        consumedMillis = 0;
    }

    public Timer timer() {
        return timer;
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
