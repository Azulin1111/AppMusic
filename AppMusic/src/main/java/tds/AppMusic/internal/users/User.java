package tds.AppMusic.internal.users;

import tds.AppMusic.internal.discount.Discount;
import tds.AppMusic.internal.discount.NullDiscount;
import tds.AppMusic.internal.music.Playlist;
import tds.AppMusic.internal.music.Song;
import java.time.LocalDate;
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
    private final LocalDate birthday;
    private final String email;
    private final List<Playlist> playlists;
    private final List<Song> recientSongs;

    public User(String name, String nickname, boolean premium, String password, String email, LocalDate birthday) {
        this.name = name;
        this.nickname = nickname;
        this.premium = premium;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        playlists = new LinkedList<>();
        recientSongs = new LinkedList<>();  // Structure FIFO TODO hay que hacer que cada vez que suene una canción se ajuste
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
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

    public List<Song> getRecentSongs(){  //TODO tratamiento de la lista: cuando se escuche una cancion habrá que incluirla
        return new LinkedList<>(recientSongs);
    }


    public List<Song> getMostPlayedSongs(){
        List<Song> mostPlayedSongs = playlists.stream()
                .flatMap(p -> p.getSongs().stream())
                .sorted(comparing(Song::getPlayCount).reversed())
                .limit(10)
                .collect(toList());

        return mostPlayedSongs;
    }


    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist){
        playlists.remove(playlist);
    }


    private Discount createDiscount(String typeDiscount) {  // Factory method: create a type of discount
        try {
            return (Discount) Class.forName("tds.AppMusic.internal.discount."+typeDiscount).getDeclaredConstructor().newInstance();
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
