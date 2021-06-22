package tds.AppMusic;

import tds.AppMusic.GUI.MainWindow;

import javax.swing.*;

public class AppMusic {
    public static void main(String[] args) {
        System.out.println("No functionality yet!");

        MainWindow mw = new MainWindow("Min");
        mw.pack();
        mw.setLocationRelativeTo(null);
        mw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mw.setVisible(true);
    }
}
