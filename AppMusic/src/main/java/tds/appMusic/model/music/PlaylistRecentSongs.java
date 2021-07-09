/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.music;

import java.util.Optional;

/**
 * Representa la playlist de canciones recientes de un usuario.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class PlaylistRecentSongs extends Playlist {

    /**
     * Crea una playlist de canciones recientes.
     * @param name El nombre de la playlist.
     */
    public PlaylistRecentSongs(String name) {
        super(name);
    }

    /**
     * <p>Añade una canción a la playlist de canciones recientes.</p>
     * En caso de estar llena, la playlist eliminará la canción más antigua.
     * @param song La canción a añadir.
     * @return {@code true} por defecto.
     */
    @Override
    public boolean addSong(Song song) {
        // Update position if it already exists
        Optional<Song> duplicate = songs.stream().filter(s -> s.getCode() == song.getCode()).findAny();
        if (duplicate.isPresent()) {
            songs.remove(duplicate.get());
            songs.add(0, song);
            return true;
        }

        // Add normally otherwise
        songs.add(0, song);
        while (songs.size() > 10) songs.remove(songs.size() - 1);
        return true;
    }
}
