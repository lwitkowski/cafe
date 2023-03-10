package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class OrderItem {
    protected final String name;
    protected final BigDecimal basePrice;
    protected final BigDecimal extraPrice;
    protected final Set<Tag> tags;

    OrderItem(String name, BigDecimal basePrice, BigDecimal extraPrice, Set<Tag> tags) {
        this.name = name;
        this.basePrice = basePrice;
        this.extraPrice = extraPrice;
        this.tags = Set.copyOf(tags);
    }

    OrderItem(String name, BigDecimal price) {
        this(name, price, BigDecimal.ZERO, Set.of());
    }

    public BigDecimal basePrice() {
        return this.basePrice;
    }

    public BigDecimal extraPrice() {
        return this.extraPrice;
    }

    public BigDecimal totalPrice() {
        return basePrice.add(extraPrice);
    }

    public boolean isTaggedWith(Tag tag) {
        return tags.contains(tag);
    }

    public List<OrderItem> times(int n) {
        var item = new OrderItem(name, basePrice, extraPrice, tags);
        return IntStream.range(0, n)
                .mapToObj(i -> item)
                .toList();
    }

    public List<OrderItem> twice() {
        return times(2);
    }

    public String print() {
        return "%-40s %10.2f".formatted(name, totalPrice());
    }

    @Override
    public String toString() {
        return print();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return name.equals(orderItem.name) && basePrice.equals(orderItem.basePrice) && extraPrice.equals(orderItem.extraPrice) && tags.equals(orderItem.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePrice, extraPrice, tags);
    }
}
