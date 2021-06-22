package tds.AppMusic.persistance;

import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;

import java.util.List;

public interface IAdaptadorSongDAO {

    public void storeSong(Song song) {}

    public void deleteSong(Song song){}

    public void setSong(Song song){}

    public User getSong(int code){}

    public List<Song> getAllSongs{}

}
