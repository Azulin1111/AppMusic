/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

/**
 * Representa un descuento fijo del 10% a cualquier cliente.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public class FixedDiscount implements Discount {

    @Override
    public double finalPrize() {
        return User.PREMIUM_PRIZE * 0.90;
    }

    @Override
    public boolean isApplicable(User user) {
        return true;
    }

    @Override
    public String asString() {
        return "Descuento fijo (10%)";
    }
}
