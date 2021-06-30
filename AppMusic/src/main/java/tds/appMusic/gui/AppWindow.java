/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Abstracción de una ventana de la aplicación.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public abstract class AppWindow extends JFrame {

    protected static final Color ERROR_COLOR = Color.RED;
    protected static final Color SUCCESS_COLOR = Color.GREEN;

    /**
     * Muestra una alerta al usuario.
     * @param windowTitle El título de la ventana.
     * @param windowText El contenido de la ventana.
     */
    protected void say(String windowTitle, String windowText) {
        JOptionPane.showMessageDialog(this, windowText, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Realiza una pregunta al usuario.
     * @param windowTitle El título de la ventana.
     * @param windowText El contenido de la ventana.
     * @return {@code true} si el usuario ha hecho clic en "Sí", {@code false} en cualquier otro caso.
     */
    protected boolean ask(String windowTitle, String windowText) {
        return JOptionPane.showConfirmDialog(this, windowText, windowTitle, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
