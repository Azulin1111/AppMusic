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
