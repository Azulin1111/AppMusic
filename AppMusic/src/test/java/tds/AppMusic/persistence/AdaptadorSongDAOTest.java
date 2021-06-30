/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

import beans.Entidad;
import beans.Propiedad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tds.AppMusic.model.music.Song;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AdaptadorSongDAOTest {

    private static final ServicioPersistencia PERSISTENCIA = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    private static final IAdaptadorSongDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getSongDAO();

    private static final String TYPE_SONG = "Song";
    private static final String TYPE_SONG_NAME = "Name";
    private static final String TYPE_SONG_GENRE = "Genre";
    private static final String TYPE_SONG_PATH = "Path";
    private static final String TYPE_SONG_SINGER = "Singer";
    private static final String TYPE_SONG_PLAYCOUNT = "Playcount";

    private static final Song SONG = new Song("song", "singer", "Genre", URI.create(""));

    @Before
    public void setUp() {
        // Store song
        Entidad s = new Entidad();
        s.setPropiedades(Arrays.asList(
                new Propiedad(TYPE_SONG_NAME, SONG.getName()),
                new Propiedad(TYPE_SONG_GENRE, SONG.getGenre()),
                new Propiedad(TYPE_SONG_PATH, SONG.getPath().toString()),
                new Propiedad(TYPE_SONG_SINGER, SONG.getSinger()),
                new Propiedad(TYPE_SONG_PLAYCOUNT, Integer.toString(SONG.getPlayCount()))
        ));
        s.setNombre(TYPE_SONG);

        s = PERSISTENCIA.registrarEntidad(s);
        SONG.setCode(s.getId());
    }

    @After
    public void tearDown() {
        PERSISTENCIA.recuperarEntidades().forEach(PERSISTENCIA::borrarEntidad);
    }

    @Test
    public void storeSong() {
        Song s = new Song("Name", "Singer", "Genre", URI.create(""));
        // Assert that the song is stored in memory
        int before = PERSISTENCIA.recuperarEntidades().size();
        DAO.storeSong(s);
        int after = PERSISTENCIA.recuperarEntidades().size();
        assertEquals(before, after - 1);

        // Assert that the song code has been set
        int code = s.getCode();
        assertNotEquals(code, 0);

        // Assert that it has been stored correctly
        Entidad e = PERSISTENCIA.recuperarEntidad(code);
        e.getPropiedades().forEach(p -> {
            switch (p.getNombre()) {
                case TYPE_SONG_NAME:
                    assertEquals("Name", p.getValor());
                    break;
                case TYPE_SONG_GENRE:
                    assertEquals("Genre", p.getValor());
                    break;
                case TYPE_SONG_PATH:
                    assertEquals(URI.create(""), URI.create(p.getValor()));
                    break;
                case TYPE_SONG_SINGER:
                    assertEquals("Singer", p.getValor());
                    break;
                case TYPE_SONG_PLAYCOUNT:
                    assertSame(0, Integer.parseInt(p.getValor()));
                    break;
            }
        });
    }

    @Test
    public void setSong() {
        // Assert that changing parameters changes the song in the database
        // Nothing to change!
    }

    @Test
    public void getSong() {
        // Assert that the recovered song is the expected one
        Song recovered = DAO.getSong(SONG.getCode());
        assertEquals(SONG, recovered);

        // Assert that the method returns null for a nonexistant user
        assertNull(DAO.getSong(Integer.MAX_VALUE));
    }

    @Test
    public void deleteSong() {
        // Assert that deleting a song makes it unrecoverable
        DAO.deleteSong(SONG);
        assertNull(DAO.getSong(SONG.getCode()));
    }

    @Test
    public void getAllSongs() {
        // Assert that it recovers all songs
        assertEquals(1, DAO.getAllSongs().size());

        // Assert that it returns an empty list when there are no songs
        PERSISTENCIA.recuperarEntidades().forEach(PERSISTENCIA::borrarEntidad);
        assertEquals(0, DAO.getAllSongs().size());
    }
}