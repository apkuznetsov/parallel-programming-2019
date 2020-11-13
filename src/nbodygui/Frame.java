package nbodygui;

import nbodygui.exceptions.HeightOutOfBoundsException;
import nbodygui.exceptions.WidthOutOfBoundsException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static nbodygui.Frames.*;

public class Frame extends JFrame {

    public Frame(int width, int height, Panel panel) {
        super(DEFAULT_TITLE);

        if (width < MIN_WIDTH || width > MAX_WIDTH) {
            throw new WidthOutOfBoundsException();
        }

        if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new HeightOutOfBoundsException();
        }

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
