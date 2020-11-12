package nbodygui;

import nbody.NbodySolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nbodygui.Surfaces.*;

public class Surface extends JPanel implements ActionListener {

    private final NbodySolver solver;
    private final Timer timer;

    public Surface(NbodySolver solver) {
        this.solver = solver;
        timer = new Timer(solver.dt(), this);
        timer.start();
    }

    public Timer timer() {
        return timer;
    }

    private void drawRandomPoints(Graphics gr) {
        Graphics2D graphics = (Graphics2D) gr;
        graphics.setPaint(POINT_COLOR);

        for (int i = 0; i < solver.n(); i++) {
            int x = solver.bodyX(i);
            int y = solver.bodyY(i);
            graphics.fillOval(x, y, POINT_SIZE, POINT_SIZE);
        }
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        solver.recalcNBodiesCoords();
        drawRandomPoints(gr);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
