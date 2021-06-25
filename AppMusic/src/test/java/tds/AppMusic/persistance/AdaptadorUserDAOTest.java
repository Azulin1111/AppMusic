package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.users.User;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class AdaptadorUserDAOTest {

    private static final ServicioPersistencia PERSISTENCIA = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

    private static final String TYPE_USER = "User";
    private static final String TYPE_USER_USERNAME = "Username";
    private static final String TYPE_USER_PASSWORD = "Password";
    private static final String TYPE_USER_NAME = "Name";
    private static final String TYPE_USER_EMAIL = "Email";
    private static final String TYPE_USER_BIRTHDAY = "Birthday";
    private static final String TYPE_USER_PREMIUM = "Premium";
    private static final String TYPE_USER_PLAYLISTS = "Playlists";
    private static final String TYPE_USER_RECENTSONGS = "Recents";

    private static final String TYPE_PLAYLIST = "Playlist";
    private static final String TYPE_PLAYLIST_IS_RECENT = "IsRecent";
    private static final String TYPE_PLAYLIST_NAME = "Name";
    private static final String TYPE_PLAYLIST_SONGS = "Songs";

    private static final IAdaptadorUserDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getUserDAO();
    private static final String NOMBRE = "nombrePrueba";
    private static final String USERNAME = "userPrueba";
    private static final boolean PREMIUM = false;
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email@prueba.com";
    private static final Date DATE = Date.from(Instant.now());

    private static final User USER = new User(NOMBRE, USERNAME, PREMIUM, PASSWORD, EMAIL, DATE);

    private static final String ID_FORMAT = "([0-9]+) ([0-9]+)";

    private static final DateFormat formatter = new SimpleDateFormat();

    private String parse(Date d) {
        return d.toInstant().toString();
    }

    private Date parse(String s) {
        return Date.from(Instant.parse(s));
    }

    @Before
    public void setUp() {
        // Store user
        Entidad e = new Entidad();
        e.setPropiedades(Arrays.asList(
                new Propiedad(TYPE_USER_NAME, NOMBRE),
                new Propiedad(TYPE_USER_USERNAME, USERNAME),
                new Propiedad(TYPE_USER_PREMIUM, Boolean.toString(PREMIUM)),
                new Propiedad(TYPE_USER_PASSWORD, PASSWORD),
                new Propiedad(TYPE_USER_EMAIL, EMAIL),
                new Propiedad(TYPE_USER_BIRTHDAY, parse(DATE)),
                new Propiedad(TYPE_USER_PLAYLISTS, ""),
                new Propiedad(TYPE_USER_RECENTSONGS, Integer.toString(USER.getCodeRecentSongs()))
        ));
        e.setNombre(TYPE_USER);

        // Store recent playlist
        Entidad p = new Entidad();
        p.setPropiedades(Arrays.asList(
                new Propiedad(TYPE_PLAYLIST_IS_RECENT, Boolean.toString(true)),
                new Propiedad(TYPE_PLAYLIST_NAME, "a"),
                new Propiedad(TYPE_PLAYLIST_SONGS, "")
        ));
        p.setNombre(TYPE_PLAYLIST);

        // Set reference
        USER.setCodeRecent(p.getId());

        // Store all
        USER.setCode(e.getId());
        PERSISTENCIA.registrarEntidad(e);
        PERSISTENCIA.registrarEntidad(p);

    }

    @After
    public void tearDown() {
        PERSISTENCIA.recuperarEntidades().forEach(PERSISTENCIA::borrarEntidad);
    }

    @Test
    public void storeUser() {
        User user = new User(NOMBRE, USERNAME, PREMIUM, PASSWORD, EMAIL, DATE);

        // Assert that the user has been stored
        int before = PERSISTENCIA.recuperarEntidades().size();
        DAO.storeUser(user);
        int after = PERSISTENCIA.recuperarEntidades().size();
        int extras = 1 + user.getPlaylists().size(); // Recent playlist + all others
        assertEquals(before, after - 1 - extras); // User + extras

        // Assert that the usercode has been set
        int code = user.getCode();
        assertNotEquals(code, 0);

        // Assert that it has been stored correctly
        Entidad e = FactoriaServicioPersistencia.getInstance().getServicioPersistencia().recuperarEntidad(code);
        int c = user.getCodeRecentSongs();
        e.getPropiedades().forEach(p -> {
            switch (p.getNombre()) {
                case TYPE_USER_NAME:
                    assertEquals(p.getValor(), NOMBRE);
                    break;
                case TYPE_USER_USERNAME:
                    assertEquals(p.getValor(), USERNAME);
                    break;
                case TYPE_USER_EMAIL:
                    assertEquals(p.getValor(), EMAIL);
                    break;
                case TYPE_USER_PASSWORD:
                    assertEquals(p.getValor(), PASSWORD);
                    break;
                case TYPE_USER_PREMIUM:
                    assertEquals(Boolean.valueOf(p.getValor()), PREMIUM);
                    break;
                case TYPE_USER_BIRTHDAY:
                    assertEquals(0, DATE.compareTo(parse(p.getValor())));
                    break;
                case TYPE_USER_PLAYLISTS:
                    assertEquals(p.getValor(), "");
                    break;
                case TYPE_USER_RECENTSONGS:
                    assertEquals(p.getValor(), Integer.toString(c));
            }
        });
    }

    @Test
    public void setUser() {
        Playlist playlistExpected1 = new Playlist("PlaylistPrueba1");
        Playlist playlistExpected2 = new Playlist("PlaylistPrueba2");

        // Assert that the playlists are stored
        USER.addPlaylist(playlistExpected1);
        USER.addPlaylist(playlistExpected2);
        int before1 = PERSISTENCIA.recuperarEntidades().size();
        DAO.setUser(USER);
        int after1 = PERSISTENCIA.recuperarEntidades().size();
        assertEquals(before1, after1 - 2);

        // Assert that the user stores references to the playlists
        Entidad e = FactoriaServicioPersistencia.getInstance().getServicioPersistencia().recuperarEntidad(USER.getCode());
        int c = USER.getCodeRecentSongs();
        e.getPropiedades().forEach(p -> {
            if (p.getNombre().equals(TYPE_USER_PLAYLISTS)) assertTrue(p.getValor().matches(ID_FORMAT));
        });

        // Assert that the playlists are deleted
        USER.removePlaylist(playlistExpected1);
        USER.removePlaylist(playlistExpected2);
        int before2 = PERSISTENCIA.recuperarEntidades().size();
        DAO.setUser(USER);
        int after2 = PERSISTENCIA.recuperarEntidades().size();
        assertEquals(before2, after2 - 2);
    }

    @Test
    public void getUser() {
        // Assert that the recovered user is the expected one
        User userRecovered = DAO.getUser(USER.getCode());
        assertEquals(USER, userRecovered);

        // Assert that the method returns null for a nonexistant user
        assertNull(DAO.getUser(Integer.MAX_VALUE));
    }

    @Test
    public void deleteUser1() {
        // Assert that deleting a user deletes the associated playlists
        DAO.deleteUser(USER);
        assertEquals(0, PERSISTENCIA.recuperarEntidades().size());
    }

    @Test
    public void deleteUser2() {
        // Assert that deleting a user makes it non recoverable
        DAO.deleteUser(USER);
        assertNull(DAO.getUser(USER.getCode()));
    }

    @Test
    public void getAllUsers() {
        // Assert that it recovers all users
        assertEquals(1, DAO.getAllUsers().size());

        // Assert that it returns an empty list when there are no users
        PERSISTENCIA.recuperarEntidades().forEach(PERSISTENCIA::borrarEntidad);
        assertEquals(0, DAO.getAllUsers().size());
    }


}