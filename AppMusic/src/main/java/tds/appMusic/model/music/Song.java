/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.music;

import java.net.URI;
import java.util.Objects;

/**
 * Representa una canción.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class Song {

    /**
     * El nombre de la canción.
     */
    private final String name;
    /**
     * El género de la canción.
     */
    private final String genre;
    /**
     * La ruta al fichero que contiene la canción.
     */
    private final URI path;
    /**
     * El intérprete de la canción.
     */
    private final String singer;
    /**
     * El número de reproducciones de la canción.
     */
    private int playCount;
    /**
     * El código único de la canción.
     */
    private int code;

    /**
     * Crea una canción nueva, es decir, sin reproducciones.
     * @param name El nombre de la canción.
     * @param singer El intérprete de la canción.
     * @param genre El género de la canción.
     * @param path La ruta al fichero de la canción.
     */
    public Song(String name, String singer, String genre, URI path) {
        this(name, singer, genre, path, 0);
    }

    /**
     * Crea una canción.
     * @param name El nombre de la canción.
     * @param singer El intérprete de la canción.
     * @param genre El género de la canción.
     * @param path La ruta al fichero de la canción.
     * @param playCount El número de reproducciones de la canción.
     */
    public Song(String name, String singer, String genre, URI path, int playCount) {
        this.name = name;
        this.genre = genre;
        this.singer = singer;
        this.path = path;
        this.playCount = playCount;
        code = 0;
    }

    /**
     * Devuelve el nombre de la canción.
     * @return El nombre de la canción.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve el género de la canción.
     * @return El género de la canción.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Devuelve el intérprete de la canción.
     * @return El intérprete de la canción.
     */
    public String getSinger(){
        return singer;
    }

    /**
     * Devuelve la ruta al fichero de la canción.
     * @return La ruta al fichero de la canción.
     */
    public URI getPath() {
        return path;
    }

    /**
     * Devuelve el número de reproducciones de la canción.
     * @return el número de reproducciones de la canción.
     */
    public int getPlayCount() {
        return playCount;
    }

    /**
     * Incrementa el número de reproducciones de la canción en 1.
     */
    public void addPlay() {
        playCount++;
    }

    /**
     * Devuelve el código de la canción.
     * @return El código de la canción.
     */
    public int getCode() {
        return code;
    }

    /**
     * Modifica el código de la canción.
     * @param code Código de la canción.
     */
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(name, song.name) &&
                Objects.equals(genre, song.genre) &&
                Objects.equals(path, song.path) &&
                Objects.equals(singer, song.singer);
    }
}
