package tds.AppMusic.internal.users;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserTest {

    User user;
    LocalDate date = LocalDate.now();

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

}