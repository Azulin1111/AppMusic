/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.discount;

import org.junit.Test;
import tds.appMusic.model.users.User;

import static org.junit.Assert.*;

/**
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class DiscountTest {

    private static final Discount NULL_DISCOUNT = new NullDiscount();
    private static final double DELTA = 0.001;
    private static final double PRIZE = User.PREMIUM_PRIZE;

    @Test
    public void discountTest() {
        // Assert that there's discounts to be had
        assertFalse(Discount.descuentos().isEmpty());

        // Assert that all discounts reduce the premium prize
        Discount.descuentos().forEach(d -> assertTrue(PRIZE > d.finalPrize()));
    }

    @Test
    public void nullDiscountTest() {
        // Assert that the null discount does not modify the premium prize
        assertEquals(PRIZE, NULL_DISCOUNT.finalPrize(), DELTA);
    }
}