package tds.AppMusic.model.music;

import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

public class SongTest {

    private static final String SONG_NAME = "Song name";
    private static final String SONG_GENRE = "Genre";
    private static final String SONG_SINGER = "Song singer";
    private static final URI SONG_PATH = null;
    private Song song;

    @Before
    public void setUp() {
        song = new Song(SONG_NAME, SONG_SINGER, SONG_GENRE, SONG_PATH);
    }

    @Test
    public void getName() {
        // Assert that the name is set correctly
        assertSame(SONG_NAME, song.getName());
    }

    @Test
    public void getGenre() {
        // Assert that the name is set correctly
        assertSame(SONG_GENRE, song.getGenre());
    }

    @Test
    public void getSinger() {
        // Assert that the name is set correctly
        assertSame(SONG_SINGER, song.getSinger());
    }

    @Test
    public void getPath() {
        // Assert that the name is set correctly
        assertSame(SONG_PATH, song.getPath());
    }

    @Test
    public void getPlayCount() {
        // Assert that the name is set correctly
        assertSame(0, song.getPlayCount());
    }

    @Test
    public void addPlay() {
        // Assert that the play count updates correctly
        assertSame(0, song.getPlayCount());
        song.addPlay();
        assertSame(1, song.getPlayCount());
    }
}