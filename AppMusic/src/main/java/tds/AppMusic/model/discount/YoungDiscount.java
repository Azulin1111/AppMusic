package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

public class YoungDiscount implements Discount {

    /**
     * <p>Returns the discounted upgrade prize</p>
     * @return 85% of the default premium upgrade cost.
     */
    @Override
    public double calcDescuento() {
        return User.PREMIUM_PRIZE * 0.85;
    }
}
