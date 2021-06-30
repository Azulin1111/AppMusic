/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

import tds.AppMusic.model.music.Song;

import java.util.List;

/**
 * Adaptador de almacenamiento de canciones en persistencia.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public interface IAdaptadorSongDAO {

    /**
     * Almacena una canción en persistencia. En caso de existir ya en persistencia, el método no modifica nada.
     * @param song La canción a almacenar.
     */
    void storeSong(Song song);

    /**
     * Elimina una canción de persistencia. En caso de no existir, el método no modifica nada.
     * @param song La canción a eliminar.
     */
    void deleteSong(Song song);

    /**
     * Modifica una canción existente en persistencia. En caso de no existir, el método no modifica nada.
     * @param song La canción a modificar, con los cambios ya realizados.
     */
    void setSong(Song song);

    /**
     * Obtiene una canción a partir de su código.
     * @param code El código de la canción.
     * @return La canción de persistencia, o {@code null} si no existe la canción.
     */
    Song getSong(int code);

    /**
     * Devuelve todas las canciones existentes en persistencia.
     * @return Una lista con todas las canciones.
     */
    List<Song> getAllSongs();

}
