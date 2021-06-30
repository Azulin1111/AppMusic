/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

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
    public void getterTest() {
        assertSame(SONG_NAME, song.getName());
        assertSame(SONG_GENRE, song.getGenre());
        assertSame(SONG_SINGER, song.getSinger());
        assertSame(SONG_PATH, song.getPath());
        assertSame(0, song.getPlayCount());
    }

    @Test
    public void addPlay() {
        // Assert that the play count updates correctly
        assertSame(0, song.getPlayCount());

        song.addPlay();
        assertSame(1, song.getPlayCount());

        song.addPlay();
        song.addPlay();
        song.addPlay();
        song.addPlay();
        song.addPlay();
        song.addPlay();
        song.addPlay();
        song.addPlay();
        song.addPlay();
        assertSame(10, song.getPlayCount());
    }
}