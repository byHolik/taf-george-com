package com.george.taf.objects;

import java.util.Objects;

public class BasketItem {
    String id;
    int quantity;
    int priceValue;

    public BasketItem(String id, int quantity, int priceValue) {
        this.id = id;
        this.quantity = quantity;
        this.priceValue = priceValue;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", priceValue=" + priceValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return quantity == that.quantity && priceValue == that.priceValue && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, priceValue);
    }
}
