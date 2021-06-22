package tds.AppMusic.GUI;

import javax.swing.*;

public abstract class AppWindow extends JFrame {

    public AppWindow() {
        setContentPane($$$getRootComponent$$$());
    }


    protected void say(String windowTitle, String windowText) {
        JOptionPane.showMessageDialog(this, windowText, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    public abstract JComponent $$$getRootComponent$$$();
}
