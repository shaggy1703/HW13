package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 99;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s: Фиксированная цена %d", getName(), FIXED_PRICE);
    }
}