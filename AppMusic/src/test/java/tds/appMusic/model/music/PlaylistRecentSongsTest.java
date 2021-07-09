/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.music;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class PlaylistRecentSongsTest {

    private final PlaylistRecentSongs playlistRecentSongs = new PlaylistRecentSongs("");

    @Test
    public void addSong() {
        // Assert that old songs get deleted
        Song[] songs = new Song[11];
        for (int i = 0; i < 11; i++) {
            songs[i] = new Song("Song " + i, null, null, null);
            songs[i].setCode(i);
            playlistRecentSongs.addSong(songs[i]);
        }
        assertFalse(playlistRecentSongs.getSongs().contains(songs[0]));
        assertTrue(playlistRecentSongs.getSongs().contains(songs[1]));
    }
}