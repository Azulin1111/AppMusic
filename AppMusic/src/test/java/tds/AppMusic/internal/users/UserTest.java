package tds.AppMusic.internal.users;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tds.AppMusic.internal.music.Genre;
import tds.AppMusic.internal.music.Playlist;
import tds.AppMusic.internal.music.Song;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    User user;
    LocalDate date = LocalDate.now();
    private static Song s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12;

    @BeforeClass
    public static void beforeAll() {
        s1 = new Song("t1", Genre.POP, "/home", 1);
        s2 = new Song("t2", Genre.POP, "/home", 2);
        s3 = new Song("t3", Genre.POP, "/home", 3);
        s4 = new Song("t4", Genre.POP, "/home", 4);
        s5 = new Song("t5", Genre.POP, "/home", 5);
        s6 = new Song("t6", Genre.POP, "/home", 6);
        s7 = new Song("t7", Genre.POP, "/home", 7);
        s8 = new Song("t8", Genre.POP, "/home", 8);
        s9 = new Song("t9", Genre.POP, "/home", 9);
        s10 = new Song("t10", Genre.POP, "/home", 10);
        s11 = new Song("t11", Genre.POP, "/home", 11);
        s12 = new Song("t12", Genre.POP, "/home", 12);
    }

    @Before
    public void setUp() {
        user = new User("Test name", "Test nickname", false, "Test password", "Test email", date);
    }



    @Test
    public void parameterTests() {
        assertEquals("Test name", user.getName());
        assertEquals("Test nickname", user.getNickname());
        assertFalse(user.isPremium());
        assertEquals("Test password", user.getPassword());
        assertEquals("Test email", user.getEmail());
        assertSame(date, user.getBirthday());
    }

    @Test
    public void fixedDiscountTest(){ //TODO probar estos tests
        double payment = user.premiumPayment("FixedDiscount");

        // Expected payment
        double expectedPayment = User.PREMIUM_PRIZE * 0.70;

        // Test
        assertEquals(payment, expectedPayment, 0.001);

    }

    @Test
    public void youngDiscountTest(){
        double payment = user.premiumPayment("YoungDiscount");

        // Expected payment
        double expectedPayment = User.PREMIUM_PRIZE * 0.85;

        // Test
        assertEquals(payment, expectedPayment, 0.001);
    }

    @Test
    public void nullDiscountTest1(){ // Explicit form
        double payment1 = user.premiumPayment("ninguno");

        // Expected payment
        double expectedPayment = User.PREMIUM_PRIZE;

        // Test
        assertEquals(payment1, expectedPayment, 0.001);
    }

    @Test
    public void nullDiscountTest2(){ // Implicit form
        double payment2 = user.premiumPayment("NullDiscount");

        // Expected payment
        double expectedPayment = User.PREMIUM_PRIZE;

        // Test
        assertEquals(payment2, expectedPayment, 0.001);
    }

    @Test
    public void mostPlayedSongsTest1(){
        Playlist test = new Playlist("playlistTest");

        test.addSong(s1); test.addSong(s2); test.addSong(s3); test.addSong(s4); test.addSong(s5); test.addSong(s6);
        test.addSong(s7); test.addSong(s8); test.addSong(s9); test.addSong(s10); test.addSong(s11); test.addSong(s12);
        user.addPlaylist(test);
        List<Song> mostPlayedSongs = user.getMostPlayedSongs();

        // Expected
        List<Song> expected = new LinkedList<>();
        expected.add(s12); expected.add(s11); expected.add(s10); expected.add(s9); expected.add(s8);
        expected.add(s7); expected.add(s6); expected.add(s5); expected.add(s4); expected.add(s3);

        // Test
        assertEquals(mostPlayedSongs, expected);
    }

    @Test
    public void mostPlayedSongsTest2(){
        Playlist p1 = new Playlist("p1");
        Playlist p2 = new Playlist("p2");

        p1.addSong(s1); p2.addSong(s2); p2.addSong(s3); p1.addSong(s4); p2.addSong(s5); p1.addSong(s6);
        p1.addSong(s7); p2.addSong(s8); p1.addSong(s9); p2.addSong(s10); p1.addSong(s11); p2.addSong(s12);
        user.addPlaylist(p1); user.addPlaylist(p2);
        List<Song> mostPlayedSongs = user.getMostPlayedSongs();

        // Expected
        List<Song> expected = new LinkedList<>();
        expected.add(s12); expected.add(s11); expected.add(s10); expected.add(s9); expected.add(s8);
        expected.add(s7); expected.add(s6); expected.add(s5); expected.add(s4); expected.add(s3);

        // Test
        assertEquals(mostPlayedSongs, expected);
    }

    @Test
    public void addRecentSongTest(){
        user.addRecentSong(s1); user.addRecentSong(s2); user.addRecentSong(s3); user.addRecentSong(s4);
        user.addRecentSong(s5); user.addRecentSong(s6); user.addRecentSong(s7); user.addRecentSong(s8);
        user.addRecentSong(s9); user.addRecentSong(s10); user.addRecentSong(s11); user.addRecentSong(s12);

        List<Song> recentSongs = user.getRecentSongs().getSongs(); //TODO esto es un solapamiento entre clases (?)

        // Expected
        List<Song> expected = new LinkedList<>();
        expected.add(s12); expected.add(s11); expected.add(s10); expected.add(s9); expected.add(s8);
        expected.add(s7);  expected.add(s6);  expected.add(s5);  expected.add(s4); expected.add(s3);

        // Test
        assertEquals(recentSongs, expected);



    }


}