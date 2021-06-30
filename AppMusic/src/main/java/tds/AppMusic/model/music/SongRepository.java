/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.music;

import tds.AppMusic.persistence.DAOFactories;
import tds.AppMusic.persistence.FactoryDAO;
import tds.AppMusic.persistence.IAdaptadorSongDAO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum SongRepository {
    INSTANCE;

    private static final Map<Integer, Song> SONGS = new HashMap<>();
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

    public Song getSong(int code) {
        Song song = SONGS.get(code);
        if (song == null) song = DAO.getSong(code);
        return song;
    }

    public List<Song> getAllSongs(){
        return new LinkedList<>(SONGS.values());
    }
}
