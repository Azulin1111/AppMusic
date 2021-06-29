package tds.AppMusic.model.music;

import tds.AppMusic.persistance.DAOFactories;
import tds.AppMusic.persistance.FactoryDAO;
import tds.AppMusic.persistance.IAdaptadorPlaylistDAO;

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
        PLAYLISTS.put(playlist.getCode(), playlist);
        DAO.storePlaylist(playlist);
    };

    public void deletePlaylist(Playlist playlist){
        PLAYLISTS.remove(playlist.getCode());
        DAO.deletePlaylist(playlist);
    };

    public void setPlaylist(Playlist playlist){
        PLAYLISTS.put(playlist.getCode(), playlist);
        DAO.setPlaylist(playlist);
    };

    public Playlist getPlaylist(int code){
        return PLAYLISTS.get(code);
    };

    public List<Playlist> getAllPlaylists(){
        return (List<Playlist>) PLAYLISTS.values();
    };

}
