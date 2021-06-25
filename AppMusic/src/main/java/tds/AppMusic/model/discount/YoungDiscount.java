package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class YoungDiscount implements Discount {

    /**
     * <p>Returns the discounted upgrade prize</p>
     * @return 85% of the default premium upgrade cost.
     */
    @Override
    public double calcDescuento() {
        return User.PREMIUM_PRIZE * 0.85;
    }

    @Override
    public boolean isApplicable(User user) {
        return Instant.now().minus(65, ChronoUnit.YEARS).isBefore(user.getBirthday().toInstant());
    }
}
