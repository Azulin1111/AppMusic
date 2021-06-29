package tds.AppMusic.app;

import com.sun.javafx.application.PlatformImpl;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tds.AppMusic.GUI.GenreComboBoxModel;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.PlaylistRepository;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.music.SongRepository;
import tds.AppMusic.model.users.User;
import tds.AppMusic.model.users.UserRepository;
import umu.tds.ISongFinder;
import umu.tds.ISongsListener;
import umu.tds.LoaderSong;
import umu.tds.SongsEvent;
import umu.tds.componente.Cancion;
import umu.tds.componente.Canciones;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public enum Controller implements ISongsListener {
    INSTANCE;

    static {
        PlatformImpl.startup(()->{});
    }

    private static User currentUser;
    private static MediaPlayer player;

    private static Playlist currentPlaylist;
    private static Song currentSong;

    private static ISongFinder loader;

    /**
     * Crea una playlist, o actualiza una existente.
     * @param name El nombre de la playlist.
     * @param songs Las canciones que componen la playlist.
     * @return {@code true} si se ha creado una playlist, {@code false} si no.
     */
    public boolean createOrUpdatePlaylist(String name, List<Song> songs) {
        boolean exists = currentUser.hasPlaylist(name);

        if (exists) {
            PlaylistRepository.INSTANCE.setPlaylist(currentUser.updatePlaylist(name, songs));
        } else {
            PlaylistRepository.INSTANCE.storePlaylist(currentUser.createPlaylist(name, songs));
            UserRepository.INSTANCE.setUser(currentUser);
        }

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
    public Playlist getSongsFiltered(String title, String interprete, String genre) {
        Playlist p = new Playlist("");
        SongRepository.INSTANCE.getAllSongs().stream()
                .filter(s -> s.getName().contains(title))
                .filter(s -> s.getSinger().contains(interprete))
                .filter(s -> genre.equals(GenreComboBoxModel.ALL_GENRES) || s.getGenre().equals(genre))
                .forEach(p::addSong);
        return p;
    }

    /**
     * Obtiene una playlist específica.
     * @param name El nombre de la playlist.
     * @return La playlist, o {@code null} si no existe playlist con el nombre indicado.
     */
    public Playlist getPlaylist(String name) {
        return currentUser.getPlaylist(name);
    }

    /**
     * Obtiene las últimas canciones que ha escuchado el usuario actual.
     * @return La playlist de canciones recientes del usuario.
     */
    public Playlist getRecentPlaylist() {
        return currentUser.getRecentPlaylist();
    }

    /**
     * Cambia la canción que reproducir. En caso de ser {@code null}, detiene el reproductor de música.
     * @param playlist La nueva playlist a almacenar, o {@code null}.
     * @param song La nueva canción a reproducir, o {@code null}.
     * @param addToRecent Si la canción debería añadirse a la lista de recientes.
     */
    public void switchTrack(Playlist playlist, Song song, boolean addToRecent) {
        Controller.currentPlaylist = playlist;
        Controller.currentSong = song;

        if (song != null) {
            Media s = new Media(song.getPath().toString());
            if (player != null) player.stop();
            player = new MediaPlayer(s);
            player.play();
            if (addToRecent) currentUser.addRecentSong(song);
        } else if (player != null) player.stop();
    }

    public void nextTrack(boolean addToRecent) {
        if (currentPlaylist != null && currentSong != null) {
           int index = currentPlaylist.getSongs().indexOf(currentSong);
           if (index != -1) {
               index = (index + 1) % currentPlaylist.getSongs().size();
               switchTrack(currentPlaylist, currentPlaylist.getSongs().get(index), addToRecent);
           }
        }
    }

    public void previousTrack(boolean addToRecent) {
        if (currentPlaylist != null && currentSong != null) {
            int index = currentPlaylist.getSongs().indexOf(currentSong);
            if (index != -1) {
                index--;
                if (index == -1) index = currentPlaylist.getSongs().size() - 1;
                switchTrack(currentPlaylist, currentPlaylist.getSongs().get(index), addToRecent);
            }
        }
    }

    /**
     * Pausa la canción actualmente en reproducción. En caso de estar pausada, el método no hace nada.
     */
    public void pauseTrack() {
        if (player != null) player.pause();
    }

    /**
     * Continúa la reproducción de la canción actual. En caso de ya estar reproduciéndose, el método no hace nada.
     */
    public void resumeTrack() {
        if (player != null) player.play();
    }

    public int currentTrack() {
        if (currentPlaylist == null) return -1;
        if (currentSong == null) return -1;
        return currentPlaylist.getSongs().indexOf(currentSong);
    }

    /**
     * Inicia sesión en la aplicación.
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return {@code true} si se ha iniciado sesión con éxito, {@code false} si no.
     */
    public boolean login(String username, String password) {
        Optional<User> login = UserRepository.INSTANCE.getAllUsers().stream()
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
        if (UserRepository.INSTANCE.getAllUsers().stream().anyMatch(u -> u.getNickname().equals(username)))
            return false;
        UserRepository.INSTANCE.storeUser(new User(name, surnames, username, false, password, email, birthday));
        return true;
    }

    public void loadSongs(URI fileXML) {
        loader.setFileSongs(fileXML.getRawPath());
    }

    public void setLoader(ISongFinder loader) {
        Controller.loader = loader;
        ((LoaderSong)loader).addSongsListener(this);
    }

    public List<String> getGenres() {
        Set<String> set = new HashSet<>();
        SongRepository.INSTANCE.getAllSongs().forEach(s -> set.add(s.getGenre()));
        return set.stream().sorted().collect(Collectors.toList());
    }

    /**
     * Convierte un objeto tipo Canciones a una lista de Songs.
     * @param canciones Canciones en formato de CargadorCanciones.
     * @return Una lista de canciones que han sido cargadas.
     */
    private List<Song> convertCancionesToSongs(Canciones canciones){
        List<Song> songs = new LinkedList<>();

        String nameSong;
        String singer;
        String genre;
        URI path;
        Song song;
        for (Cancion cancion : canciones.getCancion()) {
            nameSong = cancion.getTitulo();
            singer = cancion.getInterprete();
            genre = cancion.getEstilo();
            path = URI.create(cancion.getURL());
            song = new Song(nameSong, singer, genre, path);
            songs.add(song);
        }

        return songs;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    @Override
    public void newSongs(SongsEvent songsEvent) {
        Canciones c = songsEvent.getCanciones();
        List<Song> songs = convertCancionesToSongs(c);
        songs.forEach(s -> SongRepository.INSTANCE.storeSong(s));

    }
}
