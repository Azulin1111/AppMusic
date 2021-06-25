package tds.AppMusic.model.discount;

import org.junit.Before;
import org.junit.Test;
import tds.AppMusic.model.users.User;

import static org.junit.Assert.*;

public class DiscountTest {
    private static double testPrize;
    private static final Discount DISCOUNT_1 = new FixedDiscount();
    private static final Discount DISCOUNT_2 = new YoungDiscount();

    @Before
    public void setUp() {
        testPrize = 10.0;
    }

    @Test
    public void fixedDiscountTest() {
        double expected = User.PREMIUM_PRIZE * 0.7;
        assertEquals(expected, DISCOUNT_1.calcDescuento(), 0.01);
    }

    @Test
    public void youngDiscountTest() {
        double expected = User.PREMIUM_PRIZE * 0.85;
        assertEquals(expected, DISCOUNT_2.calcDescuento(), 0.01);
    }
}