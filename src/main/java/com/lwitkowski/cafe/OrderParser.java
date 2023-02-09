package com.lwitkowski.cafe;

import com.lwitkowski.cafe.domain.Menu;
import com.lwitkowski.cafe.domain.Order;
import com.lwitkowski.cafe.domain.OrderItem;
import com.lwitkowski.cafe.domain.StampCard;

import java.util.Locale;
import java.util.regex.Pattern;

final class OrderParser {

    private static final Pattern STAMPS_PATTERN = Pattern.compile("(\\d+) stamp");

    private OrderParser() {
        // static util
    }

    /**
     * Validates and parses input string into complete Order.
     *
     * @param input Example: `large coffee with extra milk, orange juice, small coffee with special roast, bacon roll`
     * @throws IllegalArgumentException when input contains unknown or incorrect orders
     */
    public static Order.Builder parse(String input) {
        var builder = Menu.order();
        for (String item : input.toLowerCase(Locale.ROOT).split(",", -1)) {
            parseSingleItem(builder, item.trim());
        }
        return builder;
    }

    private static void parseSingleItem(Order.Builder builder, String item) {
        var stampsMatch = STAMPS_PATTERN.matcher(item);
        if (stampsMatch.find()) {
            int stamps = Integer.parseInt(stampsMatch.group(1));
            builder.use(StampCard.fromPreviousPurchase(stamps));
            return;
        }

        var itemWith = item.split(" with ", -1);
        var itemName = itemWith[0].trim();
        var extras = itemWith.length > 1 ? itemWith[1] : null;

        var orderItem = switch (itemName) {
            case "small coffee" -> finalizeCoffee(Menu.coffee().small(), extras);
            case "medium coffee" -> finalizeCoffee(Menu.coffee().medium(), extras);
            case "large coffee" -> finalizeCoffee(Menu.coffee().large(), extras);
            case "bacon roll" -> ifExtrasNull(extras, Menu.baconRoll());
            case "orange juice" -> ifExtrasNull(extras, Menu.orangeJuice());
            default -> throw new IllegalArgumentException("Unknown item: " + item);
        };
        builder.and(orderItem);
    }

    private static OrderItem ifExtrasNull(String extras, OrderItem item) {
        if (extras != null) {
            throw new IllegalArgumentException("Bacon Roll cannot have extras");
        }
        return item;
    }

    private static OrderItem finalizeCoffee(Menu.Coffee coffee, String extras) {
        if (extras == null) {
            return coffee;
        }
        return switch (extras.trim().toLowerCase(Locale.ROOT)) {
            case "foamed milk" -> coffee.withFoamedMilk();
            case "special roast" -> coffee.withSpecialRoast();
            case "extra milk" -> coffee.withExtraMilk();
            default -> throw new IllegalArgumentException("unknown extras: " + extras);
        };
    }
}
