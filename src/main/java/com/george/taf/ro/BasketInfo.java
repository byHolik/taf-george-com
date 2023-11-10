package com.george.taf.ro;

import java.util.Objects;

public class BasketInfo {
    int totalQuantity;
    int totalCost;

    public BasketInfo(int totalQuantity, int totalCost) {
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "BasketIngormation{" +
                "totalQuantity=" + totalQuantity +
                ", totalCost=" + totalCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketInfo that = (BasketInfo) o;
        return totalQuantity == that.totalQuantity && totalCost == that.totalCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalQuantity, totalCost);
    }
}
