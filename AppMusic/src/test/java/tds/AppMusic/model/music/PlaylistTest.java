package tds.AppMusic.model.music;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaylistTest {

    private static final String PLAYLIST_NAME = "test";
    private Playlist playlist;

    @Before
    public void setUp() {
        playlist = new Playlist(PLAYLIST_NAME);
    }

    @Test
    public void getNameTest() {
        // Assert that the playlist name is correctly set
        assertEquals(PLAYLIST_NAME, playlist.getName());
    }

    @Test
    public void SongsTest() {
        // Assert that inserting and retrieving a song works properly
        Song expected = new Song("Test name", "Singer", Genre.POP, "Test path");
        playlist.addSong(expected);
        Song result = playlist.getSongs().get(0);
        assertSame(expected, result);

        // Assert that playlist size increases correctly
        playlist.addSong(new Song("Test song 2", "Singer",Genre.POP, "Test path 2"));
        assertEquals(2, playlist.getSongs().size());

        // Assert that duplicate songs cannot be added
        assertFalse(playlist.addSong(expected));

    }
}