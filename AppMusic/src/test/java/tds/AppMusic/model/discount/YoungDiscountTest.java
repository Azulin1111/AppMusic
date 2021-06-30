package tds.AppMusic.model.discount;

import org.junit.Test;
import tds.AppMusic.model.users.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class YoungDiscountTest {

    private static final User VALID_USER = new User("", "", "", false, "", "", Date.valueOf(LocalDate.now().minus(15, ChronoUnit.YEARS)));
    private static final User INVALID_USER = new User("", "", "", false, "", "", Date.valueOf(LocalDate.now().minus(25, ChronoUnit.YEARS)));

    @Test
    public void discountTest() {
        // Assert that the young discount works as intended (Ages 20 or less get the discount)
        Discount discount = new YoungDiscount();
        assertTrue(discount.isApplicable(VALID_USER));
        assertFalse(discount.isApplicable(INVALID_USER));
    }
}