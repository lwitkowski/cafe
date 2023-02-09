package com.lwitkowski.cafe.domain;

import java.util.List;

import static com.lwitkowski.cafe.domain.Tag.BEVERAGE;

public final class StampCard {

    private final int available;
    private final int used;
    private final int added;

    private StampCard(int available, int used, int added) {
        this.available = available;
        this.used = used;
        this.added = added;
    }
    
    public static StampCard empty() {
        return fromPreviousPurchase(0);
    }

    public static StampCard fromPreviousPurchase(int stamps) {
        return new StampCard(stamps, 0, 0);
    }

    public int available() {
        return available;
    }

    public int used() {
        return used;
    }

    public int added() {
        return added;
    }

    // every 5th beverage is for free
    public StampCard apply(Order.Builder builder) {
        List<OrderItem> beverages = builder.itemsWithTagMostExpensiveFirst(BEVERAGE);
        if (beverages.isEmpty()) {
            return this;
        }

        int availableLeft = available;
        int used = 0;
        int added = 0;
        for (OrderItem beverage : beverages) {
            if (availableLeft >= 4) {
                availableLeft -= 4;
                used += 4;
                builder.and(new OrderItem("5th beverage for free", beverage.basePrice().negate()));
            } else {
                added += 1;
            }
        }
        return new StampCard(availableLeft + added, used, added);
    }

}
