package org.skypro.skyshop.product;


public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть null или пустой строкой.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public abstract int getPrice();

    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getSearchTerm() {
        return getName();
    }

    @Override
    public String getType() {
        return "PRODUCT";
    }

    @Override
    public String toString() {
        return name + ": " + getPrice();
    }
}