package tds.AppMusic.model.users;

import tds.AppMusic.model.discount.Discount;
import tds.AppMusic.model.discount.NullDiscount;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.PlaylistRecentSongs;
import tds.AppMusic.model.music.Song;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.lang.Class;

import static java.lang.Class.forName;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

// TODO add documentation
public class User {

    /**
     * Default prize for Premium upgrade. Subject to change (TODO)
     */
    public static final double PREMIUM_PRIZE = 20;

    private final String name;
    private boolean premium;

    private final String nickname;
    private final String password;
    private final Date birthday;
    private final String email;
    private final List<Playlist> playlists;
    private final Playlist recentSongs;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User(String name, String nickname, boolean premium, String password, String email, Date birthday) {
        this.name = name;
        this.nickname = nickname;
        this.premium = premium;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        playlists = new LinkedList<>();
        recentSongs = new PlaylistRecentSongs("Recent Songs");  // Structure FIFO TODO hay que hacer que cada vez que suene una canción se ajuste
        code = 0;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isPremium() {
        return premium;
    }

    public static double getPremiumPrize() {
        return PREMIUM_PRIZE;
    }

    public String getName() {
        return name;
    }

    public List<Playlist> getPlaylists(){
        return new LinkedList<>(playlists);
    }

    public List<Song> getPlaylistSongs(String name) {
        for (Playlist p : playlists)
            if (p.getName().equals(name))
                return p.getSongs();
        return null;
    }

    public Playlist getRecentPlaylist(){  //TODO habría que hacer una copia (?): no si se capa la opción de borrar desde el controlador
        return recentSongs;
    }

    public List<Song> getRecentSongs() {
        return recentSongs.getSongs();
    }

    public int getCodeRecentSongs(){
        return recentSongs.getCode();
    }

    public boolean addRecentSong(Song song){
        return recentSongs.addSong(song);
    }

    public List<Song> getMostPlayedSongs(){ //TODO: hay que delegar esta función al controlador
        // Requisito: "Reproducir las 10 canciones más escuchadas en toda la aplicación".
        /*
        List<Song> mostPlayedSongs = playlists.stream()
                .flatMap(p -> p.getSongs().stream())
                .sorted(comparing(Song::getPlayCount).reversed())
                .limit(10)
                .collect(toList());

         */
        return null;
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    public Playlist createPlaylist(String name, List<Song> songs) {
        Playlist newPlayList = new Playlist(name);
        for (Song s : songs) newPlayList.addSong(s);
        playlists.add(newPlayList);
        return newPlayList;
    }

    public Playlist createPlaylist(String name) {
        return createPlaylist(name, new LinkedList<>());
    }

    public Playlist updatePlaylist(String name, List<Song> songs) {
        for (Playlist p : playlists)
            if (p.getName().equals(name)) {
                p.updateSongs(songs);
                return p;
            }
        return null;
    }

    public boolean hasPlaylist(String name) {
        return playlists.stream().anyMatch(p -> p.getName().equals(name));
    }

    public void removePlaylist(Playlist playlist){
        playlists.remove(playlist);
    }


    private Discount createDiscount(String typeDiscount) {  // Factory method: create a type of discount
        try {
            return (Discount) Class.forName("tds.AppMusic.model.discount."+typeDiscount).getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            return new NullDiscount();
        }
    }

    public double premiumPayment(String typeDiscount) {
        premium = true;
        return createDiscount(typeDiscount).calcDescuento();
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
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, premium, nickname, password, birthday, email);
    }
}
