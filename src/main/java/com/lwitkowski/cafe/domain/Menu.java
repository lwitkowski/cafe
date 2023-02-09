package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;
import java.util.Set;

import static com.lwitkowski.cafe.domain.Tag.BEVERAGE;
import static com.lwitkowski.cafe.domain.Tag.SNACK;
import static com.lwitkowski.cafe.domain.Tag.WITH_EXTRA;

public class Menu {
    public static OrderItem orangeJuice() {
        return new OrderItem("Orange juice", new BigDecimal("3.95"), BigDecimal.ZERO, Set.of(BEVERAGE));
    }

    public static OrderItem baconRoll() {
        return new OrderItem("Bacon roll", new BigDecimal("4.50"), BigDecimal.ZERO, Set.of(SNACK));
    }

    public static final class Coffee extends OrderItem {

        private Coffee(String name, BigDecimal price) {
            super(name, price, BigDecimal.ZERO, Set.of(BEVERAGE));
        }

        public static Coffee small() {
            return new Coffee("Small coffee", new BigDecimal("2.50"));
        }

        public static Coffee medium() {
            return new Coffee("Medium coffee", new BigDecimal("3.00"));
        }

        public static Coffee large() {
            return new Coffee("Large coffee", new BigDecimal("3.50"));
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
