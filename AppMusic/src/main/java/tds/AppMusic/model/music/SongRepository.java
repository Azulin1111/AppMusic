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
        DAO.storeSong(song);
        SONGS.put(song.getCode(), song);
    }

    public void deleteSong(Song song){
        DAO.deleteSong(song);
        SONGS.remove(song.getCode());
    }

    public void setSong(Song song){
        DAO.setSong(song);
        SONGS.put(song.getCode(), song);
    }

    public Song getSong(int code){
        Song song = SONGS.get(code);
        if (song == null) song = DAO.getSong(code);
        return song;
    };

    public List<Song> getAllSongs(){
        List<Song> listSongs = new LinkedList<>();
        listSongs.addAll(SONGS.values());
        return listSongs;

    }
}
