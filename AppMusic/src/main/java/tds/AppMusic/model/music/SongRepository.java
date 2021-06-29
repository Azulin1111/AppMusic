package tds.AppMusic.model.music;

import tds.AppMusic.persistance.DAOFactories;
import tds.AppMusic.persistance.FactoryDAO;
import tds.AppMusic.persistance.IAdaptadorSongDAO;

import java.net.URI;
import java.util.*;

public enum SongRepository {
    INSTANCE;

    private static final Map<Integer, Song> SONGS = new HashMap<Integer, Song>();
    private static final IAdaptadorSongDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getSongDAO();

    static {
        List<Song> listsSongs = DAO.getAllSongs();
        listsSongs.forEach(s -> SONGS.put(s.getCode(), s));
    }

    void storeSong(Song song){
        SONGS.put(song.getCode(), song);
        DAO.storeSong(song);
    };

    void deleteSong(Song song){
        SONGS.remove(song.getCode());
        DAO.deleteSong(song);
    };

    void setSong(Song song){
        SONGS.put(song.getCode(), song);
        DAO.setSong(song);
    };

    Song getSong(int code){
        return SONGS.get(code);
    };

    List<Song> getAllSongs(){
        return (List<Song>) SONGS.values();
    };
}
