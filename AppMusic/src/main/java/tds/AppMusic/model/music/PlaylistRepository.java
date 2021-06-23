package tds.AppMusic.model.music;

import tds.AppMusic.persistance.DAOFactories;
import tds.AppMusic.persistance.FactoryDAO;
import tds.AppMusic.persistance.IAdaptadorPlaylistDAO;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum PlaylistRepository {
    INSTANCE;

    private static final List<Playlist> PLAYLISTS = new LinkedList<>();
    private static final IAdaptadorPlaylistDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getPlaylistDAO();

    static {
        PLAYLISTS.addAll(DAO.getAllPlaylists());
    }

    public List<Playlist> getPlaylists(){
        return Collections.unmodifiableList(PLAYLISTS);
    }

    public void addPlaylist(String name){
        addPlaylist(name, Collections.emptyList());
    }

    public void addPlaylist(String name, List<Song> songs) {
        Playlist playlist = new Playlist(name);
        for (Song s : songs) playlist.addSong(s);
        PLAYLISTS.add(playlist);
        DAO.storePlaylist(playlist);
    }

    public void removePlaylist(Playlist playlist) {
        PLAYLISTS.remove(playlist);
        DAO.deletePlaylist(playlist);
    }
}
