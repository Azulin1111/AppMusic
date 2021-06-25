package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tds.AppMusic.model.music.Genre;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AdaptadorPlaylistDAOTest {

    private static final ServicioPersistencia PERSISTENCIA = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    private static final IAdaptadorPlaylistDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getPlaylistDAO();

    private static final String TYPE_PLAYLIST = "Playlist";
    private static final String TYPE_PLAYLIST_IS_RECENT = "IsRecent";
    private static final String TYPE_PLAYLIST_NAME = "Name";
    private static final String TYPE_PLAYLIST_SONGS = "Songs";
    private static final String TYPE_SONG = "Song";
    private static final String TYPE_SONG_NAME = "Name";
    private static final String TYPE_SONG_GENRE = "Genre";
    private static final String TYPE_SONG_PATH = "Path";
    private static final String TYPE_SONG_SINGER = "Singer";
    private static final String TYPE_SONG_PLAYCOUNT = "Playcount";
    private static final String NAME = "Playlist";

    private static final Song SONG = new Song("Song", "Singer", Genre.CHIPTUNE, URI.create(""));
    private static final Playlist PLAYLIST = new Playlist(NAME);

    private static final String ID_FORMAT = "[0-9]+";

    @Before
    public void setUp() {
        // Store playlist
        Entidad p = new Entidad();
        p.setPropiedades(Arrays.asList(
                new Propiedad(TYPE_PLAYLIST_IS_RECENT, Boolean.toString(false)),
                new Propiedad(TYPE_PLAYLIST_NAME, NAME),
                new Propiedad(TYPE_PLAYLIST_SONGS, "")
        ));
        p.setNombre(TYPE_PLAYLIST);

        // Store song
        Entidad s = new Entidad();
        s.setPropiedades(Arrays.asList(
                new Propiedad(TYPE_SONG_NAME, SONG.getName()),
                new Propiedad(TYPE_SONG_GENRE, SONG.getGenre().name()),
                new Propiedad(TYPE_SONG_PATH, SONG.getPath().toString()),
                new Propiedad(TYPE_SONG_SINGER, SONG.getSinger()),
                new Propiedad(TYPE_SONG_PLAYCOUNT, Integer.toString(SONG.getPlayCount()))
        ));
        s.setNombre(TYPE_SONG);

        PLAYLIST.setCode(p.getId());
        SONG.setCode(s.getId());

        PERSISTENCIA.registrarEntidad(p);
        PERSISTENCIA.registrarEntidad(s);
    }

    @After
    public void tearDown()  {
        PERSISTENCIA.recuperarEntidades().forEach(PERSISTENCIA::borrarEntidad);
    }

    @Test
    public void storePlaylist() {
        // Assert that the playlist is stored in memory
        int before = PERSISTENCIA.recuperarEntidades().size();
        DAO.storePlaylist(PLAYLIST);
        int after = PERSISTENCIA.recuperarEntidades().size();
        assertEquals(before, after - 1);

        // Assert that the playlistCode has been set
        int code = PLAYLIST.getCode();
        assertNotEquals(code, 0);

        // Assert that it has been stored correctly
        Entidad e = PERSISTENCIA.recuperarEntidad(code);
        e.getPropiedades().forEach(p -> {
            switch (p.getNombre()) {
                case TYPE_PLAYLIST_IS_RECENT:
                    assertEquals(Boolean.valueOf(p.getValor()), false);
                    break;
                case TYPE_PLAYLIST_NAME:
                    assertEquals(p.getValor(), NAME);
                    break;
                case TYPE_PLAYLIST_SONGS:
                    assertEquals(p.getValor(), "");
            }
        });
    }

    @Test
    public void setPlaylist() {
        // Assert that setting the song to the playlist changes the corresponding field
        int before = PERSISTENCIA.recuperarEntidades().size();
        PLAYLIST.addSong(SONG);
        DAO.setPlaylist(PLAYLIST);
        int after = PERSISTENCIA.recuperarEntidades().size();
        Entidad e = PERSISTENCIA.recuperarEntidad(PLAYLIST.getCode());
        e.getPropiedades().forEach(p -> {
            if (p.getNombre().equals(TYPE_PLAYLIST_SONGS)) assertTrue(p.getValor().matches(ID_FORMAT));
        });

        // Assert that nothing has changed in the database
        assertEquals(before, after);

        // Assert that removing the song updates the playlist in the database
        before = PERSISTENCIA.recuperarEntidades().size();
        PLAYLIST.removeSong(SONG);
        DAO.setPlaylist(PLAYLIST);
        after = PERSISTENCIA.recuperarEntidades().size();
        e = PERSISTENCIA.recuperarEntidad(PLAYLIST.getCode());
        e.getPropiedades().forEach(p -> {
            if (p.getNombre().equals(TYPE_PLAYLIST_SONGS)) assertEquals("", p.getValor());
        });

        // Assert that the song hasn't been deleted
        assertEquals(before, after);
    }

    @Test
    public void getPlaylist() {
        // Assert that the recovered playlist is the expected one
        Playlist recovered = DAO.getPlaylist(PLAYLIST.getCode());
        assertEquals(PLAYLIST, recovered);

        // Assert that the method returns null for a nonexistant user
        assertNull(DAO.getPlaylist(Integer.MAX_VALUE));
    }

    @Test
    public void deletePlaylist1() {
            // Assert that deleting a playlist does not delete the songs it's made out of
            PLAYLIST.addSong(SONG);
            DAO.setPlaylist(PLAYLIST);
            int before = PERSISTENCIA.recuperarEntidades().size();
            DAO.deletePlaylist(PLAYLIST);
            int after = PERSISTENCIA.recuperarEntidades().size();
            assertEquals(before, after);
    }

    @Test
    public void deletePlaylist2() {
        // Assert that deleting a playlist makes it unrecoverable
        DAO.deletePlaylist(PLAYLIST);
        assertNull(DAO.getPlaylist(PLAYLIST.getCode()));
    }

    @Test
    public void getAllPlaylists() {
        // Assert that it recovers all users
        assertEquals(1, DAO.getAllPlaylists().size());

        // Assert that it returns an empty list when there are no users
        PERSISTENCIA.recuperarEntidades().forEach(PERSISTENCIA::borrarEntidad);
        assertEquals(0, DAO.getAllPlaylists().size());
    }
}