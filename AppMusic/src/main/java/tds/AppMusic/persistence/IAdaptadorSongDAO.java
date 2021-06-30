/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

import tds.AppMusic.model.music.Song;

import java.util.List;

public interface IAdaptadorSongDAO {

    void storeSong(Song song);

    void deleteSong(Song song);

    void setSong(Song song);

    Song getSong(int code);

    List<Song> getAllSongs();

}
