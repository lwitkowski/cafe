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

    public BigDecimal totalPrice() {
        return items
                .stream()
                .map(OrderItem::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
