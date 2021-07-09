/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.users;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tds.appMusic.model.music.Song;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class UserTest {

    private User user;

    private static Song s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12;

    private static final String TEST_NAME = "Test name";
    private static final String TEST_SURNAMES = "Test surnames";
    private static final boolean TEST_PREMIUM = false;
    private static final String TEST_NICKNAME = "Test nickname";
    private static final String TEST_PASSWORD = "Test password";
    private static final String TEST_EMAIL = "Test email";
    private static final Date TEST_DATE = Date.from(Instant.now());

    @BeforeClass
    public static void beforeAll() {
        s1 = new Song("t1", "Singer", "Genre", null, 1);
        s2 = new Song("t2", "Singer", "Genre", null, 2);
        s3 = new Song("t3", "Singer", "Genre", null, 3);
        s4 = new Song("t4", "Singer", "Genre", null, 4);
        s5 = new Song("t5", "Singer", "Genre", null, 5);
        s6 = new Song("t6", "Singer", "Genre", null, 6);
        s7 = new Song("t7", "Singer", "Genre", null, 7);
        s8 = new Song("t8", "Singer", "Genre", null, 8);
        s9 = new Song("t9", "Singer", "Genre", null, 9);
        s10 = new Song("t10", "Singer", "Genre", null, 10);
        s11 = new Song("t11", "Singer", "Genre", null, 11);
        s12 = new Song("t12", "Singer", "Genre", null, 12);

        s1.setCode(1);
        s2.setCode(2);
        s3.setCode(3);
        s4.setCode(4);
        s5.setCode(5);
        s6.setCode(6);
        s7.setCode(7);
        s8.setCode(8);
        s9.setCode(9);
        s10.setCode(10);
        s11.setCode(11);
        s12.setCode(12);
    }

    @Before
    public void setUp() {
        user = new User(TEST_NAME, TEST_SURNAMES, TEST_NICKNAME, TEST_PREMIUM, TEST_PASSWORD, TEST_EMAIL, TEST_DATE);
    }

    @Test
    public void getterTest() {
        assertEquals(TEST_NAME, user.getName());
        assertEquals(TEST_SURNAMES, user.getSurnames());
        assertEquals(TEST_NICKNAME, user.getNickname());
        assertFalse(user.isPremium() ^ TEST_PREMIUM);
        assertEquals(TEST_PASSWORD, user.getPassword());
        assertEquals(TEST_EMAIL, user.getEmail());
        assertSame(TEST_DATE, user.getBirthday());
    }

    @Test
    public void addRecentSongTest() {
        // Add songs 1-12 in order
        user.addRecentSong(s1); user.addRecentSong(s2); user.addRecentSong(s3); user.addRecentSong(s4);
        user.addRecentSong(s5); user.addRecentSong(s6); user.addRecentSong(s7); user.addRecentSong(s8);
        user.addRecentSong(s9); user.addRecentSong(s10); user.addRecentSong(s11); user.addRecentSong(s12);

        List<Song> recentSongs = user.getRecentSongs();

        // Expected: songs 3-12 (total size = 10, removed the oldest additions)
        List<Song> expected = new LinkedList<>();
        expected.add(s12); expected.add(s11); expected.add(s10); expected.add(s9); expected.add(s8);
        expected.add(s7);  expected.add(s6);  expected.add(s5);  expected.add(s4); expected.add(s3);

        assertEquals(expected, recentSongs);
    }
}