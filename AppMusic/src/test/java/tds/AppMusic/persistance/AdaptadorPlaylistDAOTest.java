package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tds.AppMusic.model.music.Playlist;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AdaptadorPlaylistDAOTest {

    private static final ServicioPersistencia PERSISTENCIA = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    private static final IAdaptadorPlaylistDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getPlaylistDAO();

    private static final String TYPE_PLAYLIST = "Playlist";
    private static final String TYPE_PLAYLIST_IS_RECENT = "IsRecent";
    private static final String TYPE_PLAYLIST_NAME = "Name";
    private static final String TYPE_PLAYLIST_SONGS = "Songs";

    private static final String NAME = "Playlist";

    private static final Playlist PLAYLIST = new Playlist(NAME);

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

        PLAYLIST.setCode(p.getId());

        PERSISTENCIA.registrarEntidad(p);
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
        int c = user.getCodeRecentSongs();
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


    }

    @Test
    public void deletePlaylist() {
    }

    @Test
    public void getPlaylist() {
    }

    @Test
    public void getAllPlaylists() {
    }
}