package com.lwitkowski.cafe;

import com.lwitkowski.cafe.domain.Menu;

public class App {
    public static void main(String[] args) {
        var order = Menu.order(Menu.Coffee.small().twice())
                .and(Menu.Coffee.large())
                .and(Menu.Coffee.medium().withFoamedMilk())
                .and(Menu.orangeJuice().times(3))
                .and(Menu.baconRoll())
                .thatWillBeAll();
        
        System.out.println(order.receipt());
    }
}
