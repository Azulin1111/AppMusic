package tds.AppMusic.internal.music;

import java.util.LinkedList;
import java.util.List;

// TODO add documentation
public class Playlist {

    private final String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        songs = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return new LinkedList<Song>(songs);
    }

    protected List<Song> songs(){
        return songs;
    }

    public boolean addSong(Song song) {
        return songs.add(song);
    }

    public boolean removeSong(Song song) {
        return songs.remove(song);
    }
}
