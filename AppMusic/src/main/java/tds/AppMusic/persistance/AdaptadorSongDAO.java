package tds.AppMusic.persistance;

import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;

import java.util.List;

public enum AdaptadorSongDAO implements IAdaptadorSongDAO{
    INSTANCE;

    @Override
    public void storeSong(Song song) {};

    @Override
    public void deleteSong(Song song){};

    @Override
    public void setSong(Song song){};

    @Override
    public User getSong(int code){return null;};

    @Override
    public List<Song> getAllSongs(){return null;};

}
