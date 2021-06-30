/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.music;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class PlaylistTest {

    private static final String PLAYLIST_NAME = "test";
    private static final Song TEST_SONG = new Song("Test name", "Singer", "Genre", null);
    private static final Song TEST_SONG_2 = new Song("Test name 2", "Singer 2", "Genre 2", null);
    private Playlist playlist;

    @Before
    public void setUp() {
        playlist = new Playlist(PLAYLIST_NAME);
    }

    @Test
    public void getterTest() {
        assertEquals(PLAYLIST_NAME, playlist.getName());
    }

    @Test
    public void songsTest() {
        // Assert that inserting and retrieving a song works properly
        playlist.addSong(TEST_SONG);
        Song result = playlist.getSongs().get(0);
        assertSame(TEST_SONG, result);

        // Assert that playlist size increases correctly
        playlist.addSong(TEST_SONG_2);
        assertEquals(2, playlist.getSongs().size());

        // Assert that removing songs works properly
        playlist.removeSong(TEST_SONG);
        assertEquals(1, playlist.getSongs().size());
        assertEquals(TEST_SONG_2, playlist.getSongs().get(0));
    }
}