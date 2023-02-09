package com.lwitkowski.cafe.domain;

import static com.lwitkowski.cafe.domain.Tag.BEVERAGE;
import static com.lwitkowski.cafe.domain.Tag.SNACK;
import static com.lwitkowski.cafe.domain.Tag.WITH_EXTRA;

public interface Discount {

    void apply(Order.Builder order);

    // Bonus discount: if a customer orders a beverage and a snack, one of the extra's is free.
    static Discount beverageAndSnackGivesFreeExtra() {
        return order -> {
            var beverages = order.itemsWithTagMostExpensiveFirst(BEVERAGE);
            var snacks = order.itemsWithTagMostExpensiveFirst(SNACK);
            var itemsWithExtras = order.itemsWithTagMostExpensiveFirst(WITH_EXTRA);
            var freeExtras = Math.min(beverages.size(), snacks.size());

            for (int i = 0; i < freeExtras && i < itemsWithExtras.size(); ++i) {
                order.and(new OrderItem("Snack+beverage discount", itemsWithExtras.get(i).extraPrice().negate()));
            }
        };
    }
}
