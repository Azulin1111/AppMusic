package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

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
