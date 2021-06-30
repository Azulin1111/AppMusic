/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.music;

import tds.AppMusic.persistence.DAOFactories;
import tds.AppMusic.persistence.FactoryDAO;
import tds.AppMusic.persistence.IAdaptadorSongDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio de canciones.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum SongRepository {
    INSTANCE;

    private static final Map<Integer, Song> SONGS = new HashMap<>();
    private static final IAdaptadorSongDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getSongDAO();

    static {
        DAO.getAllSongs().forEach(s -> SONGS.put(s.getCode(), s));
    }

    /**
     * Almacena una canción en memoria y persistencia. Si la canción ya existe, el método no modifica nada.
     * @param song La canciñon a añadir.
     */
    public void storeSong(Song song) {
        DAO.storeSong(song);
        SONGS.putIfAbsent(song.getCode(), song);
    }

    /**
     * Elimina una canción de memoria y persistencia. Si la canción no existe, el método no modifica nada.
     * @param song La canción a eliminar.
     */
    public void deleteSong(Song song) {
        DAO.deleteSong(song);
        SONGS.remove(song.getCode());
    }

    /**
     * Modifica una canción en memoria y persistencia. Si la canción no existe, el método no modifica nada.
     * @param song La canción a modificar.
     */
    public void setSong(Song song){
        DAO.setSong(song);
        if (SONGS.containsKey(song.getCode())) SONGS.put(song.getCode(), song);
    }

    /**
     * Devuelve una canción de memoria, o en su defecto, de persistencia.
     * @param code El código de la canción.
     * @return La canción solicitada, o {@code null} si no existe.
     */
    public Song getSong(int code) {
        Song song = SONGS.get(code);
        return song != null ? song : DAO.getSong(code);
    }

    /**
     * Devuelve todas las canciones disponibles en memoria.
     * @return Una lista con todas las canciones almacenadas en memoria.
     */
    public List<Song> getAllSongs() {
        return DAO.getAllSongs();
    }
}
