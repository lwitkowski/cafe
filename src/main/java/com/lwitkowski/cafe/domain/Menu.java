package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;

public class Menu {
    public static OrderItem orangeJuice() {
        return new OrderItem("Orange juice", BigDecimal.valueOf(3.95));
    }

    public static OrderItem baconRoll() {
        return new OrderItem("Bacon roll", BigDecimal.valueOf(4.50));
    }

    public static final class Coffee {
        public static OrderItem small() {
            return new OrderItem("Small coffee", new BigDecimal("2.50"));
        }

        public static OrderItem medium() {
            return new OrderItem("Medium coffee", new BigDecimal("3.00"));
        }

        public static OrderItem large() {
            return new OrderItem("Large coffee", new BigDecimal("3.50"));
        }
    }

}
