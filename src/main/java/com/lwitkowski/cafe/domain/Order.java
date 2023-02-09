package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public BigDecimal totalPrice() {
        return items
                .stream()
                .map(OrderItem::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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
