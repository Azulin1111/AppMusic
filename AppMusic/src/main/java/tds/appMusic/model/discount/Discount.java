/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.discount;

import tds.appMusic.model.users.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa un descuento aplicable a la hora de comprar premium en la aplicación.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public interface Discount {

    /**
     * Calcula el precio final del premium una vez aplicado el descuento.
     * @return El precio final.
     */
    double finalPrize();

    /**
     * Calcula la eligibilidad de un usuario respecto al descuento en cuestión.
     * @param user El usuario.
     * @return {@code true} si el descuento es aplicable al usuario, {@code false} si no.
     */
    boolean isApplicable(User user);

    /**
     * Devuelve la representación "user-friendly" del descuento en cuestión.
     * @return Una cadena con información sobre el descuento: nombre y cantidad.
     */
    String asString();

    /**
     * Devuelve la colección de descuentos disponibles del sistema.
     * @return Un set con una instancia de cada descuento, excepto el descuento nulo.
     */
    static Set<Discount> descuentos() {
        Set<Discount> d = new HashSet<>();
        Collections.addAll(d, new YoungDiscount(), new FixedDiscount());
        return d;
    }
}
