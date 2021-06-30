/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic;

import beans.Entidad;
import tds.AppMusic.GUI.LoginWindow;
import tds.AppMusic.app.Controller;
import tds.driver.FactoriaServicioPersistencia;
import umu.tds.ISongFinder;
import umu.tds.LoaderSong;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.Collectors;

public class AppMusic {

    public static void main(String[] args) {
        ISongFinder finder = new LoaderSong();
        Controller.INSTANCE.setLoader(finder);

//        clearDB();
        showDB();

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

    public static void showDB() {
        for (Entidad e : FactoriaServicioPersistencia.getInstance().getServicioPersistencia().recuperarEntidades())
            System.out.println(e.getNombre() + e.getPropiedades().stream().map(p -> p.getNombre() + ": " + p.getValor() + "\n\t").collect(Collectors.toList()) + "\n\n");
    }
}
