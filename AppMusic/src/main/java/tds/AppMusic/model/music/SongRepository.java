package tds.AppMusic.model.music;

import tds.AppMusic.persistance.DAOFactories;
import tds.AppMusic.persistance.FactoryDAO;
import tds.AppMusic.persistance.IAdaptadorSongDAO;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum SongRepository {
    INSTANCE;

    private static final List<Song> SONGS = new LinkedList<>();
    private static final IAdaptadorSongDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getSongDAO();

    static {
        SONGS.addAll(DAO.getAllSongs());
    }

    public List<Song> getSongs(){
        return Collections.unmodifiableList(SONGS);
    }

    public void addSong(String name, String singer, Genre genre, URI path){
        addSong(name, singer, genre, path, 0);
    }

    public void addSong(String name, String singer, Genre genre, URI path, int playCount) {
        Song newSong = new Song(name, singer, genre, path);
        SONGS.add(newSong);
        DAO.storeSong(newSong);
    }

    public void removeSong(Song song) {
        SONGS.remove(song);
        DAO.deleteSong(song);
    }

}
