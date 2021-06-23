package tds.AppMusic.model.music;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterpreterTest {

    private static final String INTERPRETER_NAME = "getSongTest";

    private Interpreter interpreter;

    @Before
    public void setUp() {
        interpreter = new Interpreter(INTERPRETER_NAME);
    }

    @Test
    public void getSongsTest() {
        // Assert that the initial song collection is empty
        Collection<Song> songs = interpreter.getSongs();
        assertTrue(songs.isEmpty());
    }

    @Test
    public void getName() {
        // Assert that it returns the correct name
        assertEquals(interpreter.getName(), INTERPRETER_NAME);
    }

    @Test
    public void addSong() {
        // Assert that the song collection updates correctly
        Song song = new Song(null, null, null, null);
        interpreter.addSong(song);
        Collection<Song> songs = interpreter.getSongs();
        assertEquals(1, songs.size());
        assertTrue(songs.contains(song));
    }
}