package tds.AppMusic.model.music;

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
        songs.add(0, song);
        if(songs.size() == 11) songs.remove(10);
        return true;
    }
}
