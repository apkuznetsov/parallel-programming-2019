package nbodygui;

import nbodygui.exceptions.HeightOutOfBoundsException;
import nbodygui.exceptions.WidthOutOfBoundsException;

import javax.swing.*;

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

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
