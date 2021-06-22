package tds.AppMusic.persistance;

import tds.AppMusic.model.music.Playlist;

import java.util.List;

public interface IAdaptadorPlaylistDAO {

    public void storePlaylist(Playlist playlist) {}

    public void deletePlaylist(Playlist playlist){}

    public void setPlaylist(Playlist playlist){}

    public Playlist getPlaylist(int code){}

    public List<Playlist> getAllPlaylists{}
}
