package nbodygui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame extends JFrame {

    public Frame(Panel panel) {
        super(Panels.DEFAULT_TITLE);

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
}
