package tds.AppMusic;

import tds.AppMusic.GUI.LoginWindow;
import tds.AppMusic.app.Controller;
import tds.driver.FactoriaServicioPersistencia;
import umu.tds.ISongFinder;
import umu.tds.LoaderSong;

import javax.swing.*;
import java.util.stream.Collectors;

public class AppMusic {

    public static void main(String[] args) {
        ISongFinder finder = new LoaderSong();
        Controller.INSTANCE.setLoader(finder);

//        clearDB();

        // Clean L&F
        try {
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            LoginWindow lw = new LoginWindow();
            lw.pack();
            lw.setLocationRelativeTo(null);
            lw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            lw.setVisible(true);
        }
    }

    public static void clearDB() {
        FactoriaServicioPersistencia.getInstance().getServicioPersistencia().recuperarEntidades().forEach(e -> {
            System.out.println("Borrando " + e.getNombre() + e.getPropiedades().stream().map(p -> p.getNombre() + ": " + p.getValor()).collect(Collectors.toList()));
            FactoriaServicioPersistencia.getInstance().getServicioPersistencia().borrarEntidad(e);
        });
    }
}
