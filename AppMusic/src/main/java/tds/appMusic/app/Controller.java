/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.app;

import com.sun.javafx.application.PlatformImpl;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tds.appMusic.gui.GenreComboBoxModel;
import tds.appMusic.model.discount.Discount;
import tds.appMusic.model.music.Playlist;
import tds.appMusic.model.music.PlaylistRepository;
import tds.appMusic.model.music.Song;
import tds.appMusic.model.music.SongRepository;
import tds.appMusic.model.pdfs.Builders;
import tds.appMusic.model.pdfs.PdfGenerator;
import tds.appMusic.model.scan.SongScanner;
import tds.appMusic.model.users.User;
import tds.appMusic.model.users.UserRepository;
import umu.tds.ISongFinder;
import umu.tds.ISongsListener;
import umu.tds.LoaderSong;
import umu.tds.SongsEvent;
import umu.tds.componente.Cancion;
import umu.tds.componente.Canciones;

import java.io.File;
import java.net.URI;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controlador de la aplicación.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum Controller implements ISongsListener {
    INSTANCE;

    private static User currentUser;
    private static MediaPlayer player;

    private static Playlist currentPlaylist;
    private static int currentTrack;

    private static ISongFinder loader;

    private static final Set<Song> topSongs = new HashSet<>();

    static {
        // Inicia la plataforma JavaFX.
        PlatformImpl.startup(()->{});
        updateTop();
    }

    public static void updateTop() {
        topSongs.clear();
        topSongs.addAll(SongRepository.INSTANCE.getAllSongs().stream()
                .filter(s -> s.getPlayCount() != 0)
                .sorted(Comparator.comparing(Song::getPlayCount).reversed())
                .limit(10)
                .collect(Collectors.toList()));
    }

    /**
     * Crea una playlist, o actualiza una existente.
     * @param name El nombre de la playlist.
     * @param songs Las canciones que componen la playlist.
     */
    public void createOrUpdatePlaylist(String name, List<Song> songs) {
        if (currentUser.hasPlaylist(name)) {
            PlaylistRepository.INSTANCE.setPlaylist(currentUser.updatePlaylist(name, songs));
        } else {
            PlaylistRepository.INSTANCE.storePlaylist(currentUser.createPlaylist(name, songs));
            UserRepository.INSTANCE.setUser(currentUser);
        }
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
        Playlist p = new Playlist("Search-" + Instant.now().toString());
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
        Playlist recent = new Playlist("Recent-" + Instant.now().toString());
        currentUser.getRecentPlaylist().getSongs().forEach(recent::addSong);
        return recent;
    }

    /**
     * Cambia la canción que reproducir, o detiene el reproductor.
     * @param playlist La nueva playlist a almacenar, o {@code null} si se quiere detener.
     * @param song La nueva canción a reproducir, o {@code -1} si se quiere detener.
     * @param addToRecent Si la canción debería añadirse a la lista de recientes.
     */
    public void switchTrack(Playlist playlist, int song, boolean addToRecent) {
        Controller.currentPlaylist = playlist;
        Controller.currentTrack = song;

        // Stop if anything is null or -1
        if (currentPlaylist == null || song == -1) {
            if (player != null) player.stop();
            return;
        }
        Song s = currentPlaylist.getSongs().get(song);
        Media mp = new Media(s.getPath().toString());
        if (player != null) player.stop();
        player = new MediaPlayer(mp);
        player.play();

        playlist.addPlaySong(s);

        OptionalInt min = topSongs.stream().mapToInt(Song::getPlayCount).min();
        if (min.isPresent() && min.getAsInt() < s.getPlayCount()) addSongToTop(s);

        SongRepository.INSTANCE.setSong(s);
        if (addToRecent) {
            currentUser.addRecentSong(s);

            // Save recent playlist
            PlaylistRepository.INSTANCE.setPlaylist(currentUser.getRecentPlaylist());
        }
    }

    /**
     * Procede a la siguiente canción de la playlist actual. En caso de no estar reproduciendo canciones, el método no hace nada.
     * @param addToRecent Si la canción se debería añadir en la lista de canciones recientemente escuchadas del usuario.
     */
    public void nextTrack(boolean addToRecent) {
        if (currentPlaylist != null && currentTrack != -1) {
            currentTrack = (currentTrack + 1) % currentPlaylist.getSongs().size();
            switchTrack(currentPlaylist, currentTrack, addToRecent);
        }
    }

    /**
     * Retorna a la canción anterior de la playlist actual. En caso de no estar reproduciendo canciones, el método no hace nada.
     * @param addToRecent Si la canción se debería añadir en la lista de canciones recientemente escuchadas del usuario.
     */
    public void previousTrack(boolean addToRecent) {
        if (currentPlaylist != null && currentTrack != -1) {
            currentTrack--;
            if (currentTrack == -1) currentTrack = currentPlaylist.getSongs().size() - 1;
            switchTrack(currentPlaylist, currentTrack, addToRecent);
        }
    }

    private void addSongToTop(Song song) {
        // If song is already in top, update
        Optional<Song> duplicate = topSongs.stream().filter(s -> s.getCode() == song.getCode()).findAny();
        if (duplicate.isPresent()) {
            topSongs.remove(duplicate.get());
            topSongs.add(song);
            return;
        }

        // If top has space, add
        if (topSongs.size() < 10) {
            topSongs.add(song);
            return;
        }

        // Find least played song
        Optional<Song> min = topSongs.stream().reduce((s1, s2) -> {
            if (s1.getPlayCount() > s2.getPlayCount()) return s2;
            return s1;
        });

        // Replace if less or equal than new song
        if (min.get().getPlayCount() <= song.getPlayCount()) {
            topSongs.remove(min.get());
            topSongs.add(song);
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

    /**
     * Devuelve la canción actual.
     * @return Un entero representando la posición de la canción en la playlist actual.
     */
    public int getCurrentTrack() {
        if (currentPlaylist == null) return -1;
        return currentTrack;
    }

    /**
     * Inicia sesión en la aplicación.
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return {@code true} si se ha iniciado sesión con éxito, {@code false} si no.
     */
    public boolean login(String username, String password) {
        Optional<User> login = UserRepository.INSTANCE.getAllUsers().stream()
                .filter(u -> u.getNickname().equals(username) && u.getPassword().equals(password))
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

    /**
     * Carga canciones a partir de un fichero XML.
     * @param fileXML La dirección del fichero XML.
     */
    public void loadSongs(URI fileXML) {
        loader.setFileSongs(fileXML.getRawPath());
    }

    /**
     * Modifica el cargador de canciones XML actual.
     * @param loader El nuevo cargador de canciones.
     */
    public void setLoader(ISongFinder loader) {
        Controller.loader = loader;
        ((LoaderSong)loader).addSongsListener(this);
    }

    /**
     * Obtiene los géneros de canciones disponibles, dadas las canciones disponibles en memoria.
     * @return Una lista de géneros.
     */
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

    /**
     * @return La playlist actual.
     */
    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    /**
     * @return {@code true} si el usuario actual es premium, {@code false} si no.
     */
    public boolean isPremium() {
        return currentUser.isPremium();
    }

    /**
     * @return El mayor descuento aplicable al usuario actual.
     */
    public Discount getMaximumDiscount() {
        return currentUser.getMaximumDiscount();
    }

    /**
     * Simula la compra de premium del usuario actual.
     */
    public void buyPremium() {
        currentUser.buyPremium();
        UserRepository.INSTANCE.setUser(currentUser);
    }

    /**
     * Devuelve las canciones más escuchadas de la aplicación.
     * @return Una lista con las 10 canciones más escuchadas.
     */
    public Playlist getTopSongs() {
        Playlist top = new Playlist("Top-" + Instant.now().toString());
        topSongs.stream().sorted(Comparator.comparing(Song::getPlayCount).reversed()).forEach(top::addSong);
        return top;
    }

    /**
     * Genera un fichero PDF con las playlists del usuario.
     * @param filePDF La ruta al fichero a crear.
     * @return {@code true} en caso de éxito, {@code false} en cualquier otro caso.
     */
    public boolean generatePDF(File filePDF){
        PdfGenerator parser = new PdfGenerator(currentUser);
        parser.setBuilder(Builders.ITEXT);
        try {
            parser.parse(filePDF);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Escanea las canciones locales y añade nuevas entradas a la base de datos.
     * @param directory El directorio donde buscar canciones.
     */
    public void scanSongs(File directory) {
        SongScanner scanner = new SongScanner(directory);
        Collection<Song> songs = SongRepository.INSTANCE.getAllSongs();
        scanner.scanRoot().forEach(s -> {
            if (!songs.contains(s)) SongRepository.INSTANCE.storeSong(s);
        });
    }

    @Override
    public void newSongs(SongsEvent songsEvent) {
        Canciones c = songsEvent.getCanciones();
        List<Song> songs = convertCancionesToSongs(c);
        songs.forEach(SongRepository.INSTANCE::storeSong);
    }
}
