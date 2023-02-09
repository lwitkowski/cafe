package com.lwitkowski.cafe.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void emptyOrderShouldCostZero() {
        assertEquals(BigDecimal.ZERO, new Order().totalPrice());
    }
    
    @Test
    void totalPriceShouldBeSumOfAllItemsPrices() {
        var twoCoffeesPlease = new Order()
                .add(Coffee.small())
                .add(Coffee.large());

        assertEquals(new BigDecimal("6.00"), twoCoffeesPlease.totalPrice());
    }

}
