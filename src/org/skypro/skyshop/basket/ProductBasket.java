package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    private final List<Product> products;

    public ProductBasket() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removed = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equalsIgnoreCase(name)) {
                removed.add(product);
                iterator.remove();
            }
        }

        return removed;
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.printf("Итого: %d%n", getTotalPrice());

        int specialCount = 0;
        for (Product product : products) {
            if (product.isSpecial()) {
                specialCount++;
            }
        }

        System.out.printf("Специальных товаров: %d%n", specialCount);
    }

    public boolean contains(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        products.clear();
    }
}