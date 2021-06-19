package tds.AppMusic.internal.music;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum SongCatalog { // Singleton
    INSTANCE;
    private List<Song> songs;

    SongCatalog(){
        this.songs = new LinkedList<>();
    }

    public List<Song> getSongs(){
        return Collections.unmodifiableList(this.songs);
    }

    public void addSong(String name, String singer, Genre genre, String path){
        Song newSong = new Song(name, singer, genre, path);
        this.songs.add(newSong);
    }

    public void addSong(String name, String singer, Genre genre, String path, int playCount){
        Song newSong = new Song(name, singer, genre, path, playCount);
        this.songs.add(newSong);
    }


    public void removeSong(Song song){ //TODO no se sabe si es hay que poner esta función
        this.songs.remove(song);
    }






}
