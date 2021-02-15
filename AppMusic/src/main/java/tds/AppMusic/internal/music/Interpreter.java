package tds.AppMusic.internal.music;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// TODO add documentation
public class Interpreter {

    private final String name;
    private final List<Song> songs;

    public Interpreter(String name) {
        this.name = name;
        this.songs = new LinkedList<>();
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public String getName() {
        return name;
    }

    public boolean addSong() {
        // TODO returns true if the song was added (not a duplicate)
        return false;
    }

    // TODO generate tests
}
