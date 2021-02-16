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

    @Test  //TODO debería salir una excepción
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
    public void mostPlayedSongsTest(){

    }


}