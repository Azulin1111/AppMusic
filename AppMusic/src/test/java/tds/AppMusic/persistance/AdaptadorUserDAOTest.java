package tds.AppMusic.persistance;

import org.junit.BeforeClass;
import org.junit.Test;
import tds.AppMusic.model.music.Genre;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;

import java.util.Date;

import static org.junit.Assert.*;

public class AdaptadorUserDAOTest {
    IAdaptadorUserDAO pdao = FactoryDAO.getInstance(DAOFactories.TDS).getUserDAO();
    String name = "nombrePrueba";
    String nickname = "userPrueba";
    boolean premium = false;
    String password = "password";
    String email = "email@prueba.com";
    Date date = new Date();
    User expected = new User(name, nickname, premium, password, email, date);

    @Test
    public void storeUser() {
        pdao.storeUser(expected);

        int code = expected.getCode();
        assertNotEquals(code, 0);

        User userRecovered = pdao.getUser(code);
        assertEquals(expected, userRecovered);
    }

    @Test
    public void setUser() {
/*
        Playlist playlistExpected1 = new Playlist("PlaylistPrueba1");
        Song s = new Song("songPrueba", "cantantePrueba", Genre.TECHNO, )
        Playlist playlistExpected2 = new Playlist("PlaylistPrueba2");

        expected.addPlaylist(playlistExpected1);
        expected.addPlaylist(playlistExpected2);


*/

    }

    @Test
    public void getUser() {
    }

    @Test
    public void deleteUser() {
        // Si se borra el usuario, se borrará también la playlist asociada

    }

    @Test
    public void getAllUsers() {
    }


}