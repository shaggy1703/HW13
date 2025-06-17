package org.skypro.skyshop.basket;


import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products;
    private int count;

    public ProductBasket() {
        this.products = new Product[5];
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
            total += products[i].getProductPrice();
        }
        return total;
    }

    public void printContents() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.printf("%s: %d%n", products[i].getProduct(), products[i].getProductPrice());
        }
        System.out.printf("Итого: %d%n", getTotalPrice());
    }

    public boolean contains(String productName) {
        for (int i = 0; i < count; i++) {
            if (products[i].getProduct().equalsIgnoreCase(productName)) {
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