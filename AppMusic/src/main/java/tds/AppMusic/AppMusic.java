package tds.AppMusic;

import beans.Entidad;
import tds.AppMusic.GUI.LoginWindow;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import javax.swing.*;
import java.util.List;

public class AppMusic {
    public static void main(String[] args) {
        JFrame frame = new LoginWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
