package com.lwitkowski.cafe.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeverageAndSnackBonusTest {

    @Test
    void beverageAndSnackShouldMakeExtraFree() {
        var priceWithSpecialRoastForFree = new BigDecimal("7.50");

        var twoCoffeesAndBaconRoll = Menu.order(Menu.coffee().medium().withSpecialRoast())
                .and(Menu.baconRoll())
                .thatWillBeAll();

        assertEquals(priceWithSpecialRoastForFree, twoCoffeesAndBaconRoll.totalPrice());
    }


    @Test
    void multipleBeverageAndSnackShouldMakeMultipleExtrasFree() {
        var priceWithAllExtrasForFree = new BigDecimal("15.50");

        var twoExtrasForFree = Menu.order(Menu.coffee().medium().withSpecialRoast())
                .and(Menu.coffee().large().withFoamedMilk())
                .and(Menu.baconRoll())
                .and(Menu.baconRoll())
                .thatWillBeAll();

        assertEquals(priceWithAllExtrasForFree, twoExtrasForFree.totalPrice());
    }

}
