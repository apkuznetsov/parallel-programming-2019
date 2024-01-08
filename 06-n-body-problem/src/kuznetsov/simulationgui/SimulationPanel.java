package kuznetsov.simulationgui;

import kuznetsov.point.Point;
import kuznetsov.simulation.Simulation;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimulationPanel extends JPanel implements ActionListener {

    static final Color DEFAULT_POINTS_COLOR = Color.BLACK;
    static final int DEFAULT_POINTS_SIZE = 5;

    private final Timer timer;
    private final int durationMillis;
    private final Simulation simulation;
    private int consumedMillis;

    SimulationPanel(Simulation simulation, int durationMillis) {
        this.simulation = simulation;

        this.consumedMillis = 0;
        this.durationMillis = durationMillis;

        timer = new Timer(simulation.dt(), this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        simulation.compute();
        drawPoints(gr);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (consumedMillis >= durationMillis) {
            timer.stop();
            System.out.println(consumedMillis);
            return;
        }

        consumedMillis += timer.getDelay();
        repaint();
    }

    Timer timer() {
        return timer;
    }

    private void drawPoints(Graphics gr) {
        Graphics2D graphics = (Graphics2D) gr;
        graphics.setPaint(DEFAULT_POINTS_COLOR);

        for (int i = 0; i < simulation.n(); i++) {
            Point p = simulation.p(i);
            graphics.fillOval(
                    (int) p.x(), (int) p.y(),
                    DEFAULT_POINTS_SIZE, DEFAULT_POINTS_SIZE
            );
        }
    }

}
