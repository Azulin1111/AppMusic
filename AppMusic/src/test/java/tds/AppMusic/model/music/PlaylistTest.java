package tds.AppMusic.model.music;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaylistTest {

    Playlist playlist;

    @Before
    public void setUp() {
        playlist = new Playlist("Test playlist");
    }

    @Test
    public void getNameTest() {
        String expected = "Test playlist";
        assertEquals(expected, playlist.getName());
    }

    @Test
    public void SongsTest() {

        // Test 1: inserting and retrieving a song
        Song expected = new Song("Test name", "Singer", Genre.POP, "Test path");
        playlist.addSong(expected);

        Song result = playlist.getSongs().get(0);
        assertSame(expected, result);

        // Test 2: Playlist size check
        playlist.addSong(new Song("Test song 2", "Singer",Genre.POP, "Test path 2"));
        assertEquals(2, playlist.getSongs().size());

        // Test 3: Duplicate song
        assertFalse(playlist.addSong(expected));

    }
}