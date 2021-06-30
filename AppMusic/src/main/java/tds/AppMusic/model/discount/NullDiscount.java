package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

public class NullDiscount implements Discount{ // Patron Null Object

    @Override
    public double calcDescuento() {
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
