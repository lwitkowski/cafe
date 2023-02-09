package com.lwitkowski.cafe.domain;

import com.lwitkowski.cafe.domain.Menu.Coffee;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeverageAndSnackBonusTest {

    @Test
    void beverageAndSnackShouldMakeExtraFree() {
        var priceWithSpecialRoastForFree = new BigDecimal("7.50");

        var twoCoffeesAndBaconRoll = new Order()
                .and(Coffee.medium().withSpecialRoast())
                .and(Menu.baconRoll())
                .apply(Discount.beverageAndSnackGivesFreeExtra());
        
        assertEquals(priceWithSpecialRoastForFree, twoCoffeesAndBaconRoll.totalPrice());
    }
    
    
    @Test
    void multipleBeverageAndSnackShouldMakeMultipleExtrasFree() {
        var priceWithAllExtrasForFree = new BigDecimal("15.50");
        
        var twoExtrasForFree = new Order()
                .and(Coffee.medium().withSpecialRoast())
                .and(Coffee.large().withFoamedMilk())
                .and(Menu.baconRoll())
                .and(Menu.baconRoll())
                .apply(Discount.beverageAndSnackGivesFreeExtra());

        assertEquals(priceWithAllExtrasForFree, twoExtrasForFree.totalPrice());
    }
    
}