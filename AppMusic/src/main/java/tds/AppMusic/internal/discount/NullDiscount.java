package tds.AppMusic.internal.discount;

import tds.AppMusic.internal.users.User;

public class NullDiscount implements Discount{ // Patron Null Object

    @Override
    public double calcDescuento() {
        return User.PREMIUM_PRIZE;
    }
}
