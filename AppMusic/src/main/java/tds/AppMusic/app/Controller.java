package tds.AppMusic.app;

import tds.AppMusic.model.music.Genre;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;
import tds.AppMusic.persistance.DAOFactories;
import tds.AppMusic.persistance.FactoryDAO;
import tds.AppMusic.persistance.IAdaptadorPlaylistDAO;
import tds.AppMusic.persistance.PersistenceManager;

import java.util.Date;
import java.util.List;

public enum Controller { //TODO whole class
    INSTANCE;
    private PersistenceManager persistenceManager = new PersistenceManager();
    private User currentUser;

    /**
     * Crea una playlist, o actualiza una existente.
     * @param name El nombre de la playlist.
     * @param songs Las canciones que componen la playlist.
     * @return {@code true} si se ha creado una playlist, {@code false} si no.
     */
    public boolean createOrUpdatePlaylist(String name, List<Song> songs) {
        IAdaptadorPlaylistDAO pdao = FactoryDAO.getInstance(DAOFactories.TDS).getPlaylistDAO();
        boolean exists = currentUser.hasPlaylist(name);

        if (exists) pdao.setPlaylist(currentUser.updatePlaylist(name, songs));
        else pdao.storePlaylist(currentUser.createPlaylist(name, songs));

        return exists;
    }

    /**
     * Comprueba si un nombre de playlist está libre.
     * @param name El nombre de la playlist.
     * @return {@code true} si el nombre no está disponible, {@code false} se
     */
    public boolean playlistExists(String name) {
         return currentUser.hasPlaylist(name);
    }

    public List<Playlist> getPlaylists() {
        return currentUser.getPlaylists();
    }

    public List<Song> getSongsFiltered(String title, String interprete, Genre genre) {
        return null;
    }

    public List<Song> getSongsPlaylist(String name) {
        return currentUser.getPlaylistSongs(name);
    }

    public List<Song> getSongsRecientes() {
        return currentUser.getRecentSongs();
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
