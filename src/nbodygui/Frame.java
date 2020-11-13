package nbodygui;

import nbodygui.panel.Panel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame extends JFrame {

    public Frame(Panel panel) {
        super(Frames.DEFAULT_TITLE);

        add(panel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = panel.timer();
                timer.stop();
            }
        });

        setSize(Frames.DEFAULT_WIDTH, Frames.DEFAULT_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
