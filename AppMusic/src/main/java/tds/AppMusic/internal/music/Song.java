package tds.AppMusic.internal.music;

import java.net.URI;

public class Song {

    private final String name;
    private final Genre genre;
    private final String path;
    private int playCount = 0;

    public Song(String name, Genre genre, String path) {
        this.name = name;
        this.genre = genre;
        this.path = path;
    }

    public Song(String name, Genre genre, String path, int playCount) {
        this.name = name;
        this.genre = genre;
        this.path = path;
        this.playCount = playCount;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getPath() {
        return path;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void addPlay() {
        playCount++;
    }


}
