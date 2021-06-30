/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.persistence;

import tds.appMusic.model.music.Playlist;

import java.util.List;

/**
 * Adaptador de almacenamiento de playlists en persistencia.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public interface IAdaptadorPlaylistDAO {

    /**
     * Almacena una playlist en persistencia. En caso de existir ya en persistencia, el método no modifica nada.
     * @param playlist La playlist a almacenar.
     */
    void storePlaylist(Playlist playlist);

    /**
     * Elimina una playlist de persistencia. En caso de no existir, el método no modifica nada.
     * @param playlist La playlist a eliminar.
     */
    void deletePlaylist(Playlist playlist);

    /**
     * Modifica una playlist existente en persistencia. En caso de no existir, el método no modifica nada.
     * @param playlist La playlist a modificar, con los cambios ya realizados.
     */
    void setPlaylist(Playlist playlist);

    /**
     * Obtiene una playlist a partir de su código.
     * @param code El código de la playlist.
     * @return La playlist de persistencia, o {@code null} si no existe la playlist.
     */
    Playlist getPlaylist(int code);

    /**
     * Devuelve todas las playlist existentes en persistencia.
     * @return Una lista con todas las playlists.
     */
    List<Playlist> getAllPlaylists();
}
