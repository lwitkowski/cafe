package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;

public record Coffee(
        String name, 
        BigDecimal price
) {
    public static Coffee small() {
        return new Coffee("Small coffee", new BigDecimal("2.50"));
    }

    public static Coffee medium() {
        return new Coffee("Medium coffee", new BigDecimal("3.00"));
    }

    public static Coffee large() {
        return new Coffee("Large coffee", new BigDecimal("3.50"));
    }
}
