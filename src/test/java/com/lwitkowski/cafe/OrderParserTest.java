package com.lwitkowski.cafe;

import com.lwitkowski.cafe.domain.Menu;
import com.lwitkowski.cafe.domain.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderParserTest {

    @Test
    void shouldParseSimpleOrder() {
        var input = "large coffee";

        Order.Builder order = OrderParser.parse(input);

        assertTrue(order.contains(Menu.Coffee.large()));
        assertEquals(order.stampCard().available(), 0);
    }

    @Test
    void shouldParseCoffeeExtras() {
        var input = "medium coffee with foamed milk";

        Order.Builder order = OrderParser.parse(input);

        assertTrue(order.contains(Menu.Coffee.medium().withFoamedMilk()));
    }

    @Test
    void shouldParseStamps() {
        var input = "large coffee, small coffee, 17 stamps";

        Order.Builder order = OrderParser.parse(input);

        assertTrue(order.contains(Menu.Coffee.large()));
        assertTrue(order.contains(Menu.Coffee.small()));
        assertFalse(order.contains(Menu.Coffee.medium()));
        assertEquals(order.stampCard().available(), 17);
    }

    @Test
    void shouldParseComplexOrder() {
        var input = "large coffee with extra milk, 2 stamps, small coffee with special roast, bacon roll, orange juice";

        Order.Builder order = OrderParser.parse(input);

        assertTrue(order.contains(Menu.Coffee.large().withExtraMilk()));
        assertTrue(order.contains(Menu.Coffee.small().withSpecialRoast()));
        assertTrue(order.contains(Menu.baconRoll()));
        assertTrue(order.contains(Menu.orangeJuice()));
        assertEquals(order.stampCard().available(), 2);
    }

    @Test
    void shouldRejectInvalidOrder() {
        assertThrows(IllegalArgumentException.class,
                () -> OrderParser.parse("bacon roll with extra milk"));

        assertThrows(IllegalArgumentException.class,
                () -> OrderParser.parse("large coffee with tee"));

        assertThrows(IllegalArgumentException.class,
                () -> OrderParser.parse("pork chop"));
    }
}
