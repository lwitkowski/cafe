package com.lwitkowski.cafe.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Order {
    private final List<OrderItem> items;
    private final StampCard newStampCard;

    private Order(List<OrderItem> items, StampCard newStampCard) {
        this.items = Collections.unmodifiableList(items);
        this.newStampCard = newStampCard;
    }

    public BigDecimal totalPrice() {
        return items
                .stream()
                .map(OrderItem::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public StampCard newStampCard() {
        return newStampCard;
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

    public static final class Builder {
        private final List<OrderItem> items = new ArrayList<>();
        private final List<Discount> activeDiscounts;
        private StampCard stampCard = StampCard.empty();

        public Builder(List<Discount> activeDiscounts) {
            this.activeDiscounts = Collections.unmodifiableList(activeDiscounts);
        }

        public Builder and(OrderItem item) {
            this.items.add(item);
            return this;
        }

        public Builder and(List<OrderItem> items) {
            this.items.addAll(items);
            return this;
        }

        public Builder use(StampCard stampCard) {
            this.stampCard = stampCard;
            return this;
        }

        public List<OrderItem> itemsWithTagMostExpensiveFirst(Tag tag) {
            return items.stream()
                    .filter(item -> item.isTaggedWith(tag))
                    .sorted(Comparator.comparing(OrderItem::basePrice).reversed())
                    .toList();
        }

        public Order thatWillBeAll() {
            StampCard newStampCard = stampCard.apply(this);
            activeDiscounts.forEach(discount -> discount.apply(this));
            return new Order(items, newStampCard);
        }
    }
}
