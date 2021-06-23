package tds.AppMusic.model.music;

import java.net.URI;

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
    private final Genre genre;
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
    public Song(String name, String singer, Genre genre, URI path) {
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
    public Song(String name, String singer, Genre genre, URI path, int playCount) {
        this.name = name;
        this.genre = genre;
        this.singer = singer;
        this.path = path;
        this.playCount = playCount;
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
    public Genre getGenre() {
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
}
