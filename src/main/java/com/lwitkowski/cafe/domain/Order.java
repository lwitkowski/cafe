package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Order {

    private final List<OrderItem> items = new ArrayList<>();

    public Order and(OrderItem item) {
        this.items.add(item);
        return this;
    }

    public Order and(List<OrderItem> items) {
        this.items.addAll(items);
        return this;
    }
    
    public Order apply(Discount discount) {
        Order copy = new Order().and(this.items);
        discount.apply(copy);
        return copy;
    }

    public BigDecimal totalPrice() {
        return items
                .stream()
                .map(OrderItem::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public List<OrderItem> itemsWithTagMostExpensiveFirst(Tag tag) {
        return items.stream()
                .filter(item -> item.isTaggedWith(tag))
                .sorted(Comparator.comparing(OrderItem::basePrice).reversed())
                .toList();
    }

    public boolean contains(OrderItem item) {
        return items.stream()
                .anyMatch(i -> i.equals(item));
    }

    public String receipt() {
        List<String> lines = new ArrayList<>();
        lines.add("Order");
        for (OrderItem item : items) {
            lines.add(" " + item.print());
        }
        lines.add("-----");
        lines.add("%-41s %10.2f".formatted("Total CHF: ", totalPrice()));

        return String.join(System.lineSeparator(), lines);
    }

    @Override
    public String toString() {
        return receipt();
    }
}
