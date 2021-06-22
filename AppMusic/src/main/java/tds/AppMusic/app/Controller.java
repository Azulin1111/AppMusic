package tds.AppMusic.app;

import tds.AppMusic.model.music.Genre;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;
import tds.AppMusic.persistance.PersistenceManager;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public enum Controller { //TODO whole class
    INSTANCE;
    private PersistenceManager persistenceManager = new PersistenceManager();
    private User currentUser;

    Controller() {
        currentUser = new User("Evangeline", "Evangeline", false,
                "123345","pepitaEmail", null);
    }

    public String getCurrentUser(){
        return currentUser.getName();
    }

    public DefaultListModel filterSongs(String interpreter, String title, String genre){ //TODO
        DefaultListModel model = new DefaultListModel();
        model.add(model.size(), interpreter + ": " + title);

        for(int i =0; i<40; i++)
            model.add(model.size(), genre + ": adgilla");

        return model;
    }

    public boolean createOrUpdatePlaylist(String name, List<Song> songs) { //TODO
        this.currentUser.createPlayList(name);
        return false;
    }

    public boolean playlistExists(String name) {
        return false;
    }

    public List<Playlist> getPlaylists(String username) {
        return null;
    }

    public List<Song> getSongsFiltered(String title, String interprete, Genre genre) {
        return null;
    }

    public List<Song> getSongsPlaylist(String name) {
        return new LinkedList<>();
    }

    public List<Song> getSongsRecientes() {
        return null;
    }

    public void switchTrack(Song song) {

    }

    public void pauseTrack() {

    }

    public void resumeTrack() {

    }

    public boolean login(String username, String password){
        return persistenceManager.login(username, password);
    }

    public boolean signup(String username, String password, String name, String surnames, String email, Date birthday) {
        PersistenceManager pm = new PersistenceManager();
        if (pm.findUser(username)) return false;
        pm.storeUser(username, password, name, surnames, email, birthday);
        return true;
    }
}
