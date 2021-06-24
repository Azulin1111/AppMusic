package tds.AppMusic.app;

import tds.AppMusic.model.music.Genre;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;
import tds.AppMusic.persistance.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    /**
     * Recupera las playlist del usuario.
     * @return Una lista con todas las playlists pertenecientes al usuario actual.
     */
    public List<Playlist> getPlaylists() {
        return currentUser.getPlaylists();
    }

    /**
     * <p>Obtiene todas las canciones en la base de datos que cumplen los filtros.<br>
     * Más formalmente, devuelve una lista con todas las canciones que cumplen:</p>
     * - 1: El título de la canción contiene la cadena {@code title}.<br>
     * - 2: El nombre del intérprete de la canción contiene la cadena {@code interprete}.<br>
     * - 3: El género de la canción debe ser {@code genre}.<br>
     * En caso de que alguno de los tres campos sea {@code null}, su comprobación se ignora.
     * @param title Título de la canción.
     * @param interprete Intérprete de la canción.
     * @param genre Género de la canción.
     * @return Una lista con todas las canciones que pasan el filtro.
     */
    public List<Song> getSongsFiltered(String title, String interprete, Genre genre) {
        // TODO
        return null;
    }

    /**
     * Obtiene las canciones de una playlist específica.
     * @param name El nombre de la playlist.
     * @return Una lista con las canciones de la playlist, o {@code null} si no existe playlist con el nombre indicado.
     */
    public List<Song> getSongsPlaylist(String name) {
        return currentUser.getPlaylistSongs(name);
    }

    /**
     * Obtiene las últimas canciones que ha escuchado el usuario actual.
     * @return Una lista con las últimas canciones escuchadas.
     */
    public List<Song> getSongsRecientes() {
        return currentUser.getRecentSongs();
    }

    /**
     * Cambia la canción que reproducir. En caso de ser {@code null}, detiene el reproductor de música.
     * @param song La nueva canción a reproducir, o {@code null}.
     */
    public void switchTrack(Song song) {
        // TODO
    }

    /**
     * Pausa la canción actualmente en reproducción. En caso de estar pausada, el método no hace nada.
     */
    public void pauseTrack() {
        // TODO
    }

    /**
     * Continúa la reproducción de la canción actual. En caso de ya estar reproduciéndose, el método no hace nada.
     */
    public void resumeTrack() {
        // TODO
    }

    /**
     * Inicia sesión en la aplicación.
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return {@code true} si se ha iniciado sesión con éxito, {@code false} si no.
     */
    public boolean login(String username, String password) {
        IAdaptadorUserDAO udao = FactoryDAO.getInstance(DAOFactories.TDS).getUserDAO();
        Optional<User> login = udao.getAllUsers().stream()
                .filter(u -> u.compareNickname(username) && u.comparePassword(password))
                .findAny();
        login.ifPresent(user -> currentUser = user);
        return login.isPresent();
    }

    /**
     * Registra un nuevo usuario en la aplicación.
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @param name El nombre real del usuario.
     * @param surnames Los apellidos del usuario.
     * @param email El correo electrónico del usuario.
     * @param birthday La fecha de cumpleaños del usuario.
     * @return {@code true} si el usuario se ha registrado con éxito, {@code false} si el nombre de usuario ya existe.
     */
    public boolean signup(String username, String password, String name, String surnames, String email, Date birthday) {
        IAdaptadorUserDAO udao = FactoryDAO.getInstance(DAOFactories.TDS).getUserDAO();
        if (udao.getAllUsers().stream().anyMatch(u -> u.getNickname().equals(username))) return false;
        udao.storeUser(new User(name, username, false, password, email, birthday));
        return true;
    }
}
