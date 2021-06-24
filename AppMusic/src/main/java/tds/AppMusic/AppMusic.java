package tds.AppMusic;

import tds.AppMusic.GUI.LoginWindow;
import tds.AppMusic.GUI.MainWindow;

import javax.swing.*;

public class AppMusic {
    public static void main(String[] args) {
        System.out.println("No functionality yet!");

        LoginWindow lw = new LoginWindow();
        lw.pack();
        lw.setLocationRelativeTo(null);
        lw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lw.setVisible(true);
    }
}
