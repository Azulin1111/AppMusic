/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.music;

import tds.AppMusic.persistence.DAOFactories;
import tds.AppMusic.persistence.FactoryDAO;
import tds.AppMusic.persistence.IAdaptadorPlaylistDAO;

import java.util.*;

/**
 * Repositorio de playlists.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum PlaylistRepository {
    INSTANCE;

    private static final Map<Integer, Playlist> PLAYLISTS = new HashMap<>();
    private static final IAdaptadorPlaylistDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getPlaylistDAO();

    static {
        DAO.getAllPlaylists().forEach(p -> PLAYLISTS.put(p.getCode(), p));
    }

    /**
     * Almacena una playlist en memoria y persistencia. Si la playlist ya existe, el método no modifica nada.
     * @param playlist La playlist a añadir.
     */
    public void storePlaylist(Playlist playlist) {
        DAO.storePlaylist(playlist);
        PLAYLISTS.putIfAbsent(playlist.getCode(), playlist);
    }

    /**
     * Elimina una playlist de memoria y persistencia. Si la playlist no existe, el método no modifica nada.
     * @param playlist La playlist a eliminar.
     */
    public void deletePlaylist(Playlist playlist) {
        DAO.deletePlaylist(playlist);
        PLAYLISTS.remove(playlist.getCode());
    }

    /**
     * Modifica una playlist en memoria y persistencia. Si la playlist no existe, el método no modifica nada.
     * @param playlist La playlist a modificar.
     */
    public void setPlaylist(Playlist playlist) {
        DAO.setPlaylist(playlist);
        if (PLAYLISTS.containsKey(playlist.getCode())) PLAYLISTS.put(playlist.getCode(), playlist);
    }

    /**
     * Devuelve una playlist de memoria, o en su defecto, de persistencia.
     * @param code El código de la playlist.
     * @return La playlist solicitada, o {@code null} si no existe.
     */
    public Playlist getPlaylist(int code) {
        Playlist playlist = PLAYLISTS.get(code);
        return playlist != null ? playlist : DAO.getPlaylist(code);
    };

    /**
     * Devuelve todas las playlists disponibles en memoria.
     * @return Una lista con todas las playlists almacenadas en memoria.
     */
    public List<Playlist> getAllPlaylists() {
        return DAO.getAllPlaylists();
    }
}
