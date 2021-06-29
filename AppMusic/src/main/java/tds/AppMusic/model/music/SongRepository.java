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

    public void storeSong(Song song){
        SONGS.put(song.getCode(), song);
        DAO.storeSong(song);
    }

    public void deleteSong(Song song){
        SONGS.remove(song.getCode());
        DAO.deleteSong(song);
    }

    public void setSong(Song song){
        SONGS.put(song.getCode(), song);
        DAO.setSong(song);
    }

    public Song getSong(int code){
        return SONGS.get(code);
    };

    public List<Song> getAllSongs(){
        List<Song> listSongs = new LinkedList<>();
        SONGS.values().forEach(s -> listSongs.add(s));
        return listSongs;

    }
}
