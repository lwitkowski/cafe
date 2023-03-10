package com.lwitkowski.cafe.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void emptyOrderShouldCostZero() {
        assertEquals(BigDecimal.ZERO, Menu.order().thatWillBeAll().totalPrice());
    }

    @Test
    void totalPriceShouldBeSumOfAllItemsPrices() {
        var fullMenuPlease = Menu.order(Menu.coffee().small())
                .and(Menu.orangeJuice())
                .and(Menu.coffee().large())
                .and(Menu.baconRoll())
                .and(Menu.coffee().medium())
                .thatWillBeAll();

        assertEquals(new BigDecimal("17.45"), fullMenuPlease.totalPrice());
    }

    @Test
    void totalPriceShouldIncludeExtras() {
        var twoSmallCoffeesPlease = Menu.order(Menu.coffee().large().withExtraMilk())
                .and(Menu.coffee().small().withFoamedMilk().times(3))
                .thatWillBeAll();

        assertEquals(new BigDecimal("12.80"), twoSmallCoffeesPlease.totalPrice());
    }

    @Test
    void shouldPrintReceipt() {
        var receipt = Menu.order(Menu.coffee().small().twice())
                .and(Menu.coffee().large())
                .and(Menu.coffee().medium().withFoamedMilk())
                .and(Menu.orangeJuice().times(3))
                .and(Menu.baconRoll())
                .use(StampCard.fromPreviousPurchase(9))
                .thatWillBeAll()
                .receipt();

        assertEquals("""
                        ****************************************************
                        Order
                         Small coffee                                   2.50
                         Small coffee                                   2.50
                         Large coffee                                   3.50
                         Medium coffee (+foamed milk)                   3.50
                         Orange juice                                   3.95
                         Orange juice                                   3.95
                         Orange juice                                   3.95
                         Bacon roll                                     4.50
                         5th beverage for free                         -3.95
                         5th beverage for free                         -3.95
                         Snack+beverage discount                       -0.50
                        -----
                        Total CHF:                                     19.95
                        -----
                        Stamps: 6 (8 used, 5 added)
                        ****************************************************""",
                receipt);

    }
}
