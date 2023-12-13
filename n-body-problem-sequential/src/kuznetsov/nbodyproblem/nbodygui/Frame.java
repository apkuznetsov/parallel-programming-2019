package kuznetsov.nbodyproblem.nbodygui;

import kuznetsov.nbodyproblem.nbodygui.exceptions.HeightOutOfBoundsException;
import kuznetsov.nbodyproblem.nbodygui.exceptions.WidthOutOfBoundsException;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame extends JFrame {

    public Frame(int width, int height, Panel panel) {
        super(Frames.DEFAULT_TITLE);

        if (width < Frames.MIN_WIDTH || width > Frames.MAX_WIDTH) {
            throw new WidthOutOfBoundsException();
        }

        if (height < Frames.MIN_HEIGHT || height > Frames.MAX_HEIGHT) {
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
