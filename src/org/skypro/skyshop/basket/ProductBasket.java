package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products;
    private int count;

    public ProductBasket() {
        this.products = new Product[10]; // Расширим для удобства
        this.count = 0;
    }

    public void addProduct(Product product) {
        if (count >= products.length) {
            System.out.println("Невозможно добавить продукт");
            return;
        }
        products[count++] = product;
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    public void printContents() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(products[i]);
        }

        System.out.printf("Итого: %d%n", getTotalPrice());

        int specialCount = 0;
        for (int i = 0; i < count; i++) {
            if (products[i].isSpecial()) {
                specialCount++;
            }
        }

        System.out.printf("Специальных товаров: %d%n", specialCount);
    }

    public boolean contains(String productName) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }
}