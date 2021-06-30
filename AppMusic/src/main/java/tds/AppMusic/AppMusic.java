/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic;

import tds.AppMusic.GUI.LoginWindow;
import tds.AppMusic.app.Controller;
import umu.tds.ISongFinder;
import umu.tds.LoaderSong;

import javax.swing.*;

/**
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class AppMusic {

    public static void main(String[] args) {
        ISongFinder finder = new LoaderSong();
        Controller.INSTANCE.setLoader(finder);

        // Clean L&F
        try { UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); }
        catch (Exception e) { e.printStackTrace(); }
        finally {
            LoginWindow lw = new LoginWindow();
            lw.pack();
            lw.setLocationRelativeTo(null);
            lw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            lw.setVisible(true);
        }
    }
}
