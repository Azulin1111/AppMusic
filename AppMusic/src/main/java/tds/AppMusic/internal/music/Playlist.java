package tds.AppMusic.internal.music;

import java.util.List;

public class Playlist {

    private final String name;

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return null; // TODO
    }

    public boolean addSong(Song song) {
        return false; // TODO
    }
}
