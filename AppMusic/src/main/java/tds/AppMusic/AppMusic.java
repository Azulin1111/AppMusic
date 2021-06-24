package tds.AppMusic;

import tds.AppMusic.GUI.LoginWindow;
import tds.AppMusic.GUI.MainWindow;
import tds.driver.FactoriaServicioPersistencia;

import javax.swing.*;

public class AppMusic {
    public static void main(String[] args) {
        System.out.println("No functionality yet!");

//        clearDB();

        LoginWindow lw = new LoginWindow();
        lw.pack();
        lw.setLocationRelativeTo(null);
        lw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lw.setVisible(true);
    }

    public static void clearDB() {
        FactoriaServicioPersistencia.getInstance().getServicioPersistencia().recuperarEntidades().forEach(e -> {
            System.out.println("Borrando " + e.getNombre() + e.getPropiedades());
            FactoriaServicioPersistencia.getInstance().getServicioPersistencia().borrarEntidad(e);
        });
    }
}
