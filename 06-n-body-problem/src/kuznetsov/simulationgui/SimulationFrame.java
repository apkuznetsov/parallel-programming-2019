package kuznetsov.simulationgui;

import kuznetsov.simulation.Simulation;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimulationFrame extends JFrame {

    public SimulationFrame(String title, int width, int height, int durationMillis, Simulation simulation) {
        super(title);

        SimulationPanel panel = new SimulationPanel(simulation, durationMillis);
        add(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = panel.timer();
                timer.stop();
            }
        });

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
