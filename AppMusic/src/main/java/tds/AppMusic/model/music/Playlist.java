package tds.AppMusic.model.music;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Representa una playlist del sistema, creada por un usuario.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class Playlist {

    /**
     * El código único de la playlist.
     */
    private int code;
    /**
     * El nombre de la playlist.
     */
    private final String name;
    /**
     * La lista de canciones de las que se compone la playlist.
     */
    protected List<Song> songs;

    /**
     * Crea una playlist vacía.
     * @param name El nombre de la playlist.
     */
    public Playlist(String name) {
        this.name = name;
        songs = new LinkedList<>();
    }

    /**
     * Devuelve el código de la playlist. Este código se utiliza en la base de datos.
     * @return Un entero representando el código.
     */
    public int getCode(){
        return code;
    }

    /**
     * Modifica el código de la playlist. Este método solo debería ser utilizado por el DAO.
     * @param code El nuevo código de la playlist.
     */
    public void setCode(int code){
        this.code = code;
    }

    /**
     * Devuelve el nombre de la playlist.
     * @return El nombre de la playlist.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve las canciones de la playlist.
     * @return Una lista no modificable con las canciones que pertenecen a la playlist.
     */
    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    /**
     * Añade una canción a la playlist.
     * @param song La canción a añadir.
     * @return {@code true} si la canción se ha añadido con éxito, {@code false} si no.
     */
    public boolean addSong(Song song) {
        return songs.add(song);
    }

    /**
     * Elimina una canción de la playlist.
     * @param song La canción a eliminar.
     * @return {@code true} si la canción se ha eliminado con éxito, {@code false} si no.
     */
    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    /**
     * <p>Reemplaza todas las canciones de la playlist.</p>
     * Es equivalente a eliminar todas las canciones individualmente utilizando {@link Playlist#getSongs()} y
     * {@link Playlist#removeSong(Song)}, y luego añadir todas las nuevas canciones con {@link Playlist#addSong(Song)}.
     * @param songs Una lista con las nuevas canciones de la playlist.
     */
    public void updateSongs(List<Song> songs) {
        this.songs.clear();
        this.songs.addAll(songs);
    }

    @Override
    public String toString() {
        return name + " (" + songs.size() + " canciones)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return code == playlist.code &&
                Objects.equals(name, playlist.name) &&
                Objects.equals(songs, playlist.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, songs);
    }
}
