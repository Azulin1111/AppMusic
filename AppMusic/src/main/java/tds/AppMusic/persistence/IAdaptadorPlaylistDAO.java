/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

import tds.AppMusic.model.music.Playlist;

import java.util.List;

public interface IAdaptadorPlaylistDAO {

    void storePlaylist(Playlist playlist);

    void deletePlaylist(Playlist playlist);

    void setPlaylist(Playlist playlist);

    Playlist getPlaylist(int code);

    List<Playlist> getAllPlaylists();
}
