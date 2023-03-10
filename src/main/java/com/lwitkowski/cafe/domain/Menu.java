package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.lwitkowski.cafe.domain.Tag.BEVERAGE;
import static com.lwitkowski.cafe.domain.Tag.SNACK;
import static com.lwitkowski.cafe.domain.Tag.WITH_EXTRA;

public class Menu {

    private static final List<Discount> ACTIVE_DISCOUNTS = List.of(Discount.beverageAndSnackGivesFreeExtra());

    public static Order.Builder order(OrderItem... firstItems) {
        return order(Arrays.asList(firstItems));
    }

    public static Order.Builder order(List<OrderItem> firstItems) {
        return new Order.Builder(ACTIVE_DISCOUNTS)
                .and(firstItems);
    }

    public static OrderItem orangeJuice() {
        return new OrderItem("Orange juice", new BigDecimal("3.95"), BigDecimal.ZERO, Set.of(BEVERAGE));
    }

    public static OrderItem baconRoll() {
        return new OrderItem("Bacon roll", new BigDecimal("4.50"), BigDecimal.ZERO, Set.of(SNACK));
    }

    public static CoffeeBuilder coffee() {
        return new CoffeeBuilder();
    }

    public static final class CoffeeBuilder {

        public Coffee small() {
            return new Coffee("Small coffee", new BigDecimal("2.50"));
        }

        public Coffee medium() {
            return new Coffee("Medium coffee", new BigDecimal("3.00"));
        }

        public Coffee large() {
            return new Coffee("Large coffee", new BigDecimal("3.50"));
        }

    }

    public static final class Coffee extends OrderItem {

        private Coffee(String name, BigDecimal price) {
            super(name, price, BigDecimal.ZERO, Set.of(BEVERAGE));
        }
        
        public OrderItem withExtraMilk() {
            return new OrderItem(name + " (+extra milk)", basePrice, new BigDecimal("0.30"), Set.of(BEVERAGE, WITH_EXTRA));
        }

        public OrderItem withFoamedMilk() {
            return new OrderItem(name + " (+foamed milk)", basePrice, new BigDecimal("0.50"), Set.of(BEVERAGE, WITH_EXTRA));
        }

        public OrderItem withSpecialRoast() {
            return new OrderItem(name + " (+special roast)", basePrice, new BigDecimal("0.90"), Set.of(BEVERAGE, WITH_EXTRA));
        }
    }
}
