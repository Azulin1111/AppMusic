/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class YoungDiscount implements Discount {

    /**
     * <p>Returns the discounted upgrade prize</p>
     * @return 85% of the default premium upgrade cost.
     */
    @Override
    public double finalPrize() {
        return User.PREMIUM_PRIZE * 0.85;
    }

    @Override
    public boolean isApplicable(User user) {
        return LocalDate.now().minus(20 , ChronoUnit.YEARS).isBefore(Instant.ofEpochMilli(user.getBirthday().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }

    @Override
    public String asString() {
        return "Descuento jóvenes (15%)";
    }
}
