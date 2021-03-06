/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.users;

import tds.appMusic.model.discount.Discount;
import tds.appMusic.model.discount.NullDiscount;
import tds.appMusic.model.music.Playlist;
import tds.appMusic.model.music.PlaylistRecentSongs;
import tds.appMusic.model.music.Song;

import java.util.*;

/**
 * Representa un usuario del sistema.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class User {

    /**
     * Coste premium por defecto.
     */
    public static final double PREMIUM_PRIZE = 20;

    /**
     * Nombre real del usuario.
     */
    private final String name;

    /**
     * Apellidos reales del usuario.
     */
    private final String surnames;

    /**
     * Indica si el usuario es premium.
     */
    private boolean premium;

    /**
     * El nombre de usuario del usuario.
     */
    private final String nickname;
    /**
     * La contraseña del usuario.
     */
    private final String password;
    /**
     * El cumpleaños del usuario.
     */
    private final Date birthday;
    /**
     * El correo electrónico del usuario
     */
    private final String email;
    /**
     * La lista de playlists creadas por el usuario.
     */
    private final List<Playlist> playlists;
    /**
     * La playlist de canciones recientes asociada al usuario.
     */
    private final Playlist recentSongs;
    /**
     * El identificador único del usuario
     */
    private int code;

    /**
     * Devuelve el identificador del usuario.
     * @return El identificador del usuario.
     */
    public int getCode() {
        return code;
    }

    /**
     * Modifica el identificador del usuario. Este método solo debería ser usado por el DAO.
     * @param code El nuevo identificador.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Crea un usuario nuevo.
     * @param name El nombre real del usuario.
     * @param surnames Los apellidos reales del usuario.
     * @param nickname El nombre de usuario del usuario.
     * @param premium Si el usuario es premium o no.
     * @param password La contraseña del usuario.
     * @param email El correo electrónico del usuario.
     * @param birthday El cumpleaños del usuario.
     */
    public User(String name, String surnames, String nickname, boolean premium, String password, String email, Date birthday) {
        this.name = name;
        this.surnames = surnames;
        this.nickname = nickname;
        this.premium = premium;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        playlists = new LinkedList<>();
        recentSongs = new PlaylistRecentSongs("Recent Songs");
        code = 0;
    }

    /**
     * Devuelve el correo electrónico del usuario.
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Devuelve el cumpleaños del usuario.
     * @return La fecha correspondiente al cumpleaños del usuario.
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Devuelve la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve el nombre de usuario del usuario.
     * @return El nombre de usuario del usuario.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Indica si el usuario es premium.
     * @return {@code true} en caso positivo, {@code false} si no.
     */
    public boolean isPremium() {
        return premium;
    }

    /**
     * Devuelve el nombre real del usuario.
     * @return El nombre real del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve los apellidos reales del usuario.
     * @return Los apellidos reales del usuario.
     */
    public String getSurnames() {
        return surnames;
    }

    /**
     * Devuelve las playlists creadas por el usuario.
     * @return Una lista no modificable con las playlists del usuario.
     */
    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(playlists);
    }

    /**
     * Devuelve una playlist.
     * @param name El nombre de la playlist.
     * @return La playlist, o {@code null} si no existe.
     */
    public Playlist getPlaylist(String name) {
        for (Playlist p : playlists)
            if (p.getName().equals(name))
                return p;
        return null;
    }

    /**
     * Devuelve la playlist de canciones recientemente escuchadas del usuario.
     * @return La playlist de canciones recientes.
     */
    public Playlist getRecentPlaylist() {
        return recentSongs;
    }

    /**
     * Devuelve una lista de canciones correspondientes a la playlist de canciones recientes.
     * @return La lista de canciones.
     */
    public List<Song> getRecentSongs() {
        return recentSongs.getSongs();
    }

    /**
     * Devuelve el código de la playlist de canciones recientes del usuario.
     * @return Un entero correspondiente al código.
     */
    public int getCodeRecentSongs() {
        return recentSongs.getCode();
    }

    /**
     * Añade una canción a la lista de canciones recientemente escuchadas por el usuario.
     * @param song La última canción escuchada.
     */
    public void addRecentSong(Song song) {
        recentSongs.addSong(song);
    }

    /**
     * <p>Relaciona una playlist con el usuario.</p>
     * La relación indicaría que el usuario ha creado la playlist en cuestión.
     * @param playlist La playlist.
     */
    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    /**
     * Crea una playlist nueva, y la relaciona con el usuario.
     * @param name El nombre de la playlist.
     * @param songs Una lista con las canciones de la playlist.
     * @return La playlist creada.
     */
    public Playlist createPlaylist(String name, List<Song> songs) {
        Playlist newPlayList = new Playlist(name);
        for (Song s : songs) newPlayList.addSong(s);
        playlists.add(newPlayList);

        return newPlayList;
    }

    /**
     * Actualiza una playlist ya existente.
     * @param name El nombre de la playlist ya existente.
     * @param songs La nueva lista de canciones de la playlist.
     * @return La playlist actualizada.
     */
    public Playlist updatePlaylist(String name, List<Song> songs) {
        for (Playlist p : playlists)
            if (p.getName().equals(name)) {
                p.updateSongs(songs);
                return p;
            }
        return null;
    }

    /**
     * Comprueba si una playlist específica está relacionada con el usuario.
     * @param name El nombre de la playlist.
     * @return {@code true} si la playlist existe, {@code false} si no.
     */
    public boolean hasPlaylist(String name) {
        return playlists.stream().anyMatch(p -> p.getName().equals(name));
    }

    /**
     * Elimina la relación entre el usuario y la playlist. Si no había relación, el método no hace nada.
     * @param playlist La playlist en cuestión.
     */
    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    /**
     * Calcula el mejor descuento aplicable.
     * @return Un descuento, o en su defecto el descuento nulo {@link NullDiscount}.
     */
    public Discount getMaximumDiscount() {
        return Discount.descuentos().stream()
                .filter(d -> d.isApplicable(this))
                .reduce((d1, d2) -> d1.finalPrize() < d2.finalPrize() ? d1 : d2)
                .orElse(new NullDiscount());
    }

    /**
     * Simula la compra de premium.
     */
    public void buyPremium() {
        premium = true;
    }

    /**
     * Cambia el código de la playlist recientes del usuario.
     * @param code El nuevo código.
     */
    public void setCodeRecent(int code) {
        recentSongs.setCode(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return premium == user.premium &&
                Objects.equals(name, user.name) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(email, user.email) &&
                Objects.equals(playlists, user.getPlaylists()) &&
                Objects.equals(recentSongs, user.getRecentPlaylist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, premium, nickname, password, birthday, email);
    }
}
