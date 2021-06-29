package tds.AppMusic.model.music;

import tds.AppMusic.persistence.DAOFactories;
import tds.AppMusic.persistence.FactoryDAO;
import tds.AppMusic.persistence.IAdaptadorPlaylistDAO;

import java.util.*;

public enum PlaylistRepository {
    INSTANCE;

    private static final Map<Integer, Playlist> PLAYLISTS = new HashMap<Integer, Playlist>();
    private static final IAdaptadorPlaylistDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getPlaylistDAO();

    static {
        List<Playlist> listsPlaylists = DAO.getAllPlaylists();
        listsPlaylists.forEach(p -> PLAYLISTS.put(p.getCode(), p));
    }


    public void storePlaylist(Playlist playlist){
        DAO.storePlaylist(playlist);
        PLAYLISTS.put(playlist.getCode(), playlist);
    }

    public void deletePlaylist(Playlist playlist){
        DAO.deletePlaylist(playlist);
        PLAYLISTS.remove(playlist.getCode());
    }

    public void setPlaylist(Playlist playlist){
        DAO.setPlaylist(playlist);
        PLAYLISTS.put(playlist.getCode(), playlist);
    }

    public Playlist getPlaylist(int code){
        Playlist playlist = PLAYLISTS.get(code);
        if (playlist == null) playlist = DAO.getPlaylist(code);
        return playlist;
    };

    public List<Playlist> getAllPlaylists(){
        return new LinkedList<>(PLAYLISTS.values());
    }

}
