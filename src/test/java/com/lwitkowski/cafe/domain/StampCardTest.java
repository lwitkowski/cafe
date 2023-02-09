package com.lwitkowski.cafe.domain;

import com.lwitkowski.cafe.domain.Menu.Coffee;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StampCardTest {

    @Test
    void beverageShouldGenerateNewStamps() {
        var order = Menu.order(Menu.baconRoll())
                .and(Menu.orangeJuice().twice())
                .and(Coffee.large().withExtraMilk())
                .use(StampCard.empty())
                .thatWillBeAll();

        var newStamps = order.newStampCard();

        assertEquals(3, newStamps.available());
        assertEquals(0, newStamps.used());
        assertEquals(3, newStamps.added());
    }


    @Test
    void fourStampsShouldDeductBasePriceOfMostExpensiveCaffee() {
        var order = Menu.order(Coffee.small()) // generates 1 new stamp
                .and(Coffee.large().withExtraMilk())
                .use(StampCard.fromPreviousPurchase(7)) // 3 stamps left for next Card
                .thatWillBeAll(); // consumes 4 stamps

        var newStamps = order.newStampCard();

        assertEquals(new BigDecimal("2.80"), order.totalPrice()); // large coffee is for free, but still needs to pay for extra milk
        assertEquals(4, newStamps.available());
        assertEquals(4, newStamps.used());
        assertEquals(1, newStamps.added());
    }

    @Test
    void shouldUseStampsMultipleTimes() {
        var order = Menu.order(Coffee.small())
                .and(Coffee.large())
                .use(StampCard.fromPreviousPurchase(8))
                .thatWillBeAll();

        var newStamps = order.newStampCard();

        assertEquals(0, newStamps.available());
        assertEquals(8, newStamps.used());
        assertEquals(0, newStamps.added());
        assertEquals(new BigDecimal("0.00"), order.totalPrice());
    }

    @Test
    void shouldNotUseStampsAddedForNewBeverages() {
        var order = Menu.order(Coffee.small().times(10))
                .and(Coffee.large())
                .thatWillBeAll();

        var newStamps = order.newStampCard();

        assertEquals(11, newStamps.available());
        assertEquals(0, newStamps.used());
        assertEquals(11, newStamps.added());
    }
}
