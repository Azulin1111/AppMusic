package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

public class FixedDiscount implements Discount {

    @Override
    public double calcDescuento() {
        return User.PREMIUM_PRIZE * 0.70;
    }

    @Override
    public boolean isApplicable(User user) {
        return true;
    }
}
