package tds.AppMusic.model.discount;

import tds.AppMusic.model.users.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public interface Discount {

    /**
     * <p>Method to calculate the discount when a certain set of conditions are fulfilled.</p>
     * @return The updated upgrade cost, after applying the specific discount.
     */
    double finalPrize();

    /**
     * <p>Method to calculate a user's eligibility for a certain discount.</p>
     * @param user The user in question.
     * @return {@code true} if the user is eligible for this discount, {@code false} otherwise.
     */
    boolean isApplicable(User user);

    static Set<Discount> descuentos() {
        Set<Discount> d = new HashSet<>();
        Collections.addAll(d, new YoungDiscount(), new FixedDiscount());
        return d;
    }

    String asString();
}
