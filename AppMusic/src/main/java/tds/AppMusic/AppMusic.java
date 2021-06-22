package tds.AppMusic;

import tds.AppMusic.GUI.SignupWindow;

import javax.swing.*;

public class AppMusic {
    public static void main(String[] args) {
        System.out.println("No functionality yet!");

        SignupWindow sw = new SignupWindow();
        sw.pack();
        sw.setLocationRelativeTo(null);
        sw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        sw.setVisible(true);
    }
}
