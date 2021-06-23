package tds.AppMusic.persistance;

import tds.AppMusic.model.music.Playlist;

import java.util.List;


public enum AdaptadorPlaylistDAO implements IAdaptadorPlaylistDAO {
    INSTANCE;

    @Override
    public void storePlaylist(Playlist playlist) {};

    @Override
    public void deletePlaylist(Playlist playlist){};

    @Override
    public void setPlaylist(Playlist playlist){};

    @Override
    public Playlist getPlaylist(int code){return null;};

    @Override
    public List<Playlist> getAllPlaylists(){
        return null;
    };
}
