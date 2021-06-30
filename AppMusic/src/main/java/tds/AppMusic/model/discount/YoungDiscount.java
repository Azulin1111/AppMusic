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

/**
 * Representa un descuento del 15% aplicable a los menores de 20 años.
 */
public class YoungDiscount implements Discount {

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
