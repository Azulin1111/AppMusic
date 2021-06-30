/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.pdfs;

import com.itextpdf.text.DocumentException;
import tds.appMusic.model.music.Song;

import java.io.File;
import java.io.IOException;

/**
 * Interfaz de construcción de ficheros PDF.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public interface BuilderPDFfromUser {

    /**
     * Inicia la construcción del fichero PDF.
     * @param filePDF La ruta al fichero de salida.
     */
    void buildPDF(File filePDF) throws DocumentException, IOException;

    /**
     * Construye la sección del usuario.
     * @param nameUser El nombre del usuario.
     */
    void buildUser(String nameUser) throws DocumentException;

    /**
     * Construye la sección de una playlist.
     * @param namePlaylist El nombre de la playlist.
     */
    void buildPlaylist(String namePlaylist) throws DocumentException;

    /**
     * Construye la sección de una canción.
     * @param s La canción.
     */
    void buildSong(Song s) throws DocumentException;

    /**
     * Genera el fichero PDF.
     */
    void getPDF() throws DocumentException;
}
