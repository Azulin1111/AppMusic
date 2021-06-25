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
    }

    @Test
    public void deletePlaylist() {
    }

    @Test
    public void setPlaylist() {
    }

    @Test
    public void getPlaylist() {
    }

    @Test
    public void getAllPlaylists() {
    }
}