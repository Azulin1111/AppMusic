package tds.AppMusic.GUI;

import javax.swing.*;

public abstract class AppWindow extends JFrame {
    protected void say(String windowTitle, String windowText) {
        JOptionPane.showMessageDialog(this, windowText, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    protected boolean ask(String windowTitle, String windowText) {
        return JOptionPane.showConfirmDialog(this, windowText, windowTitle, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
