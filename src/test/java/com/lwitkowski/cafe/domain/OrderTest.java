package com.lwitkowski.cafe.domain;

import com.lwitkowski.cafe.domain.Menu.Coffee;
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
        var fullMenuPlease = new Order()
                .and(Coffee.small())
                .and(Menu.orangeJuice())
                .and(Coffee.large())
                .and(Menu.baconRoll())
                .and(Coffee.medium());

        assertEquals(new BigDecimal("17.45"), fullMenuPlease.totalPrice());
    }

}
