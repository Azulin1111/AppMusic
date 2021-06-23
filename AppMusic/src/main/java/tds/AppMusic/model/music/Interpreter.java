package tds.AppMusic.model.music;

import java.util.*;

/**
 * <p>Representa el intérprete de una canción.</p>
 * El intérprete puede haber sido intérprete de varias canciones. Para esto, el objeto almacena una lista de canciones
 * las cuales han sido interpretadas por él.<br>
 * Los intérpretes no se almacenan en memoria - se definen por las canciones almacenadas.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class Interpreter {

    /**
     * El nombre del intérprete.
     */
    private final String name;
    /**
     * La lista de canciones cuyo intérprete es el representado por este objeto.
     */
    private final Set<Song> songs;

    /**
     * Crea un intérprete.
     * @param name El nombre del intérprete.
     */
    public Interpreter(String name) {
        this.name = name;
        this.songs = new HashSet<>();
    }

    /**
     * Obtiene las canciones interpretadas por el intérprete.
     * @return Una lista no modificable con las canciones en cuestión.
     * @see Interpreter#addSong(Song)
     */
    public Collection<Song> getSongs() {
        return Collections.unmodifiableCollection(songs);
    }

    /**
     * Obtiene el nombre del intérprete.
     * @return El nombre del intérprete.
     */
    public String getName() {
        return name;
    }

    /**
     * Añade una canción a la lista de canciones interpretadas por el intérprete.
     * @param song La canción a añadir.
     * @return {@code true} si la canción se ha añadido, {@code false} en caso contrario (por ejemplo, debido a ser una
     * canción duplicada).
     */
    public boolean addSong(Song song) {
        return songs.add(song);
    }
}
