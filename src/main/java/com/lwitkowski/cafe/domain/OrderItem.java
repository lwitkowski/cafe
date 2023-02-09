package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

public class OrderItem {
    protected final String name;
    protected final BigDecimal price;

    public OrderItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal price() {
        return this.price;
    }
    
    public List<OrderItem> times(int n) {
        OrderItem item = new OrderItem(name, price);
        return IntStream.range(0, n).mapToObj(i -> item).toList();
    }

    public List<OrderItem> twice() {
        return times(2);
    }

    public String print() {
        return "%-40s %10.2f".formatted(name, price());
    }

    @Override
    public String toString() {
        return print();
    }
}
