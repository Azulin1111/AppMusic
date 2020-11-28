package tds.AppMusic.internal.music;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SongTest {

    Song testSong, testSong2;

    @Before
    public void setUp() {
        testSong = new Song("Test name", Genre.POP, "Test path");
        testSong2 = new Song("Test name 2", Genre.POP, "Test path", 20);
    }

    @Test
    public void parameterTests() {
        assertEquals(Genre.POP, testSong.getGenre());
        assertEquals("Test name", testSong.getName());
        assertEquals("Test path", testSong.getPath());
        assertEquals(0, testSong.getPlayCount());

        assertEquals(20, testSong2.getPlayCount());
    }

    @Test
    public void playCountTest() {
        testSong.addPlay();
        assertEquals(1, testSong.getPlayCount());

        testSong.addPlay();
        testSong.addPlay();
        testSong.addPlay();
        testSong.addPlay();

        assertEquals(5, testSong.getPlayCount());

        testSong2.addPlay();
        assertEquals(21, testSong2.getPlayCount());

        testSong2.addPlay();
        testSong2.addPlay();
        testSong2.addPlay();
        testSong2.addPlay();

        assertEquals(25, testSong2.getPlayCount());
    }

}