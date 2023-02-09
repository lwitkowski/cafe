package com.lwitkowski.cafe.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {

    @Test
    void printShouldHaveFixedLength() {
        assertEquals("Bacon roll                                     4.50", Menu.baconRoll().print());
        assertEquals("Orange juice                                   3.95", Menu.orangeJuice().print());
        assertEquals("Medium coffee                                  3.00", Menu.Coffee.medium().print());
    }
}