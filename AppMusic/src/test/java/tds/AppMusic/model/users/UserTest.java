package tds.AppMusic.model.users;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    User user;
    Date date = Date.from(Instant.now());
    private static Song s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12;

    private static final String TEST_NAME = "Test name";
    private static final String TEST_SURNAMES = "Test surnames";
    private static final boolean TEST_PREMIUM = false;
    private static final String TEST_NICKNAME = "Test nickname";
    private static final String TEST_PASSWORD = "Test password";
    private static final String TEST_EMAIL = "Test email";

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
    }

    @Before
    public void setUp() {
        user = new User(TEST_NAME, TEST_SURNAMES, TEST_NICKNAME, TEST_PREMIUM, TEST_PASSWORD, TEST_EMAIL, date);
    }


    @Test
    public void parameterTests() {
        assertEquals(TEST_NAME, user.getName());
        assertEquals(TEST_SURNAMES, user.getSurnames());
        assertEquals(TEST_NICKNAME, user.getNickname());
        assertFalse(user.isPremium() ^ TEST_PREMIUM);
        assertEquals(TEST_PASSWORD, user.getPassword());
        assertEquals(TEST_EMAIL, user.getEmail());
        assertSame(date, user.getBirthday());
    }

    @Test
    public void fixedDiscountTest() {
        double payment = user.premiumPayment("FixedDiscount");

        // Expected payment
        double expectedPayment = User.PREMIUM_PRIZE * 0.70;

        // Test
        assertEquals(payment, expectedPayment, 0.001);

    }

    @Test
    public void youngDiscountTest() {
        double payment = user.premiumPayment("YoungDiscount");

        // Expected payment
        double expectedPayment = User.PREMIUM_PRIZE * 0.85;

        // Test
        assertEquals(payment, expectedPayment, 0.001);
    }

    @Test
    public void nullDiscountTest1() { // Explicit form
        double payment1 = user.premiumPayment("ninguno");

        // Expected payment
        double expectedPayment = User.PREMIUM_PRIZE;

        // Test
        assertEquals(payment1, expectedPayment, 0.001);
    }

    @Test
    public void nullDiscountTest2() { // Implicit form
        double payment2 = user.premiumPayment("NullDiscount");

        // Expected payment
        double expectedPayment = User.PREMIUM_PRIZE;

        // Test
        assertEquals(payment2, expectedPayment, 0.001);
    }

    @Test
    public void mostPlayedSongsTest1() {
        List<Song> songs = new LinkedList<>();
        Collections.addAll(songs, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12);
        Playlist test = user.createPlaylist("test", songs);
        List<Song> mostPlayedSongs = user.getMostPlayedSongs();

        // Expected
        List<Song> expected = new LinkedList<>();
        Collections.addAll(expected, s12, s11, s10, s9, s8, s7, s6, s5, s4, s3);

        // Test
        assertEquals(mostPlayedSongs, expected);
    }

    @Test
    public void mostPlayedSongsTest2() {
        Playlist p1 = user.createPlaylist("p1");
        Playlist p2 = user.createPlaylist("p2");

        p1.addSong(s1); p2.addSong(s2); p2.addSong(s3); p1.addSong(s4); p2.addSong(s5); p1.addSong(s6);
        p1.addSong(s7); p2.addSong(s8); p1.addSong(s9); p2.addSong(s10); p1.addSong(s11); p2.addSong(s12);

        List<Song> mostPlayedSongs = user.getMostPlayedSongs();

        // Expected
        List<Song> expected = new LinkedList<>();
        expected.add(s12); expected.add(s11); expected.add(s10); expected.add(s9); expected.add(s8);
        expected.add(s7); expected.add(s6); expected.add(s5); expected.add(s4); expected.add(s3);

        // Test
        assertEquals(mostPlayedSongs, expected);
    }

    @Test
    public void addRecentSongTest() {
        user.addRecentSong(s1); user.addRecentSong(s2); user.addRecentSong(s3); user.addRecentSong(s4);
        user.addRecentSong(s5); user.addRecentSong(s6); user.addRecentSong(s7); user.addRecentSong(s8);
        user.addRecentSong(s9); user.addRecentSong(s10); user.addRecentSong(s11); user.addRecentSong(s12);

        List<Song> recentSongs = user.getRecentSongs();

        // Expected
        List<Song> expected = new LinkedList<>();
        expected.add(s12); expected.add(s11); expected.add(s10); expected.add(s9); expected.add(s8);
        expected.add(s7);  expected.add(s6);  expected.add(s5);  expected.add(s4); expected.add(s3);

        // Test
        assertEquals(recentSongs, expected);
    }
}