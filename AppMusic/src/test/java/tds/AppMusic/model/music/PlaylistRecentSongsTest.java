/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.music;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaylistRecentSongsTest {


    private PlaylistRecentSongs playlistRecentSongs;
    @Before
    public void setUp() {
        playlistRecentSongs = new PlaylistRecentSongs("");
    }

    @Test
    public void addSong() {
        // Assert that old songs get deleted
        Song[] songs = new Song[11];
        for (int i = 0; i < 11; i++) {
            songs[i] = new Song("Song " + i, null, null, null);
            playlistRecentSongs.addSong(songs[i]);
        }
        assertFalse(playlistRecentSongs.getSongs().contains(songs[0]));
        assertTrue(playlistRecentSongs.getSongs().contains(songs[1]));
    }
}