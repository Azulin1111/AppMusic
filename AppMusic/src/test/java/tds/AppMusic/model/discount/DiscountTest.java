package tds.AppMusic.model.discount;

import org.junit.Before;
import org.junit.Test;
import tds.AppMusic.model.users.User;

import static org.junit.Assert.*;

public class DiscountTest {
    double testPrize;
    Discount discount1 = new FixedDiscount();
    Discount discount2 = new YoungDiscount();

    @Before
    public void setUp() {
        testPrize = 10.0;
    }

    @Test
    public void fixedDiscountTest() {
        double expected = User.PREMIUM_PRIZE * 0.7;
        assertEquals(expected, discount1.calcDescuento(), 0.01);
    }

    @Test
    public void youngDiscountTest() {
        double expected = User.PREMIUM_PRIZE * 0.85;
        assertEquals(expected, discount2.calcDescuento(), 0.01);
    }
}