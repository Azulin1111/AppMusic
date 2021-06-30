/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

public class NullDiscount implements Discount{ // Patron Null Object

    @Override
    public double finalPrize() {
        return User.PREMIUM_PRIZE;
    }

    @Override
    public boolean isApplicable(User user) {
        return true;
    }

    @Override
    public String asString() {
        return "(Ningún descuento)";
    }
}
