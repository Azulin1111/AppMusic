package tds.AppMusic.GUI;

import javax.swing.*;
import java.awt.*;

public abstract class AppWindow extends JFrame {

    protected static final Color ERROR_COLOR = Color.RED;
    protected static final Color SUCCESS_COLOR = Color.GREEN;

    protected void say(String windowTitle, String windowText) {
        JOptionPane.showMessageDialog(this, windowText, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    protected boolean ask(String windowTitle, String windowText) {
        return JOptionPane.showConfirmDialog(this, windowText, windowTitle, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
