package org.skypro.skyshop.basket;


import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products;

    public ProductBasket() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        String name = product.getName();
        products.computeIfAbsent(name, k -> new ArrayList<>()).add(product);
    }

    public List<Product> removeProductByName(String name) {
        String normalizedName = name.toLowerCase().trim();
        List<Product> removed = new ArrayList<>();

        Iterator<Map.Entry<String, List<Product>>> iterator = products.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, List<Product>> entry = iterator.next();
            if (entry.getKey().equalsIgnoreCase(normalizedName)) {
                removed.addAll(entry.getValue());
                iterator.remove();
            }
        }

        return removed;
    }

    public int getTotalPrice() {
        int total = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }
        return total;
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                System.out.println(product);
            }
        }

        System.out.printf("Итого: %d%n", getTotalPrice());

        int specialCount = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }

        System.out.printf("Специальных товаров: %d%n", specialCount);
    }

    public boolean contains(String productName) {
        String normalizedName = productName.toLowerCase().trim();
        for (String name : products.keySet()) {
            if (name.equalsIgnoreCase(normalizedName)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        products.clear();
    }
}