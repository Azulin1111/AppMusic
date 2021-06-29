package tds.AppMusic.persistence;

import tds.AppMusic.model.music.Playlist;

import java.util.List;

public interface IAdaptadorPlaylistDAO {

    void storePlaylist(Playlist playlist);

    void deletePlaylist(Playlist playlist);

    void setPlaylist(Playlist playlist);

    Playlist getPlaylist(int code);

    List<Playlist> getAllPlaylists();
}
