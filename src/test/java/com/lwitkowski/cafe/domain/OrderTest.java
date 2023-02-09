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
    
    @Test
    void shouldPrintReceipt() {
        var receipt = new Order()
                .and(Coffee.small().twice())
                .and(Coffee.large())
                .and(Coffee.medium())
                .and(Menu.orangeJuice().times(3))
                .and(Menu.baconRoll())
                .receipt();
        
        assertEquals("""
                    Order
                     Small coffee                                   2.50
                     Small coffee                                   2.50
                     Large coffee                                   3.50
                     Medium coffee                                  3.00
                     Orange juice                                   3.95
                     Orange juice                                   3.95
                     Orange juice                                   3.95
                     Bacon roll                                     4.50
                    -----
                    Total CHF:                                     27.85""", 
                receipt);

    }
}
