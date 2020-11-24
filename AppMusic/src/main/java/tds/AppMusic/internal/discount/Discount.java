package tds.AppMusic.internal.discount;

public interface Discount {

    /**
     * <p>Method to calculate the discount when a certain set of conditions are fulfilled.</p>
     * <p>
     *     When a user decides to upgrade to premium, a series of aplicable discounts are available for use. These
     *     discounts can only be used when different conditions are active, and the user can choose one (and only one)
     *     that works for him. However, due to the simplicity of this software, there is currently no check to ensure
     *     a user doesn't get an invalid discount. This is because the interface cannot access any information that
     *     would be necessary to calculate this. Instead, their purpose will be to offer varying levels of discount.
     * </p>
     * @return The updated upgrade cost, after applying the specific discount.
     */
    double calcDescuento();
}
