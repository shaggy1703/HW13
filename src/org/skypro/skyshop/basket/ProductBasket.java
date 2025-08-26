package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.Product;
import java.util.*;
import java.util.stream.Collectors;

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
        return products.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        products.values().stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);

        System.out.printf("Итого: %d%n", getTotalPrice());
        System.out.printf("Специальных товаров: %d%n", getSpecialCount());
    }

    public boolean contains(String productName) {
        String normalizedName = productName.toLowerCase().trim();
        return products.keySet().stream()
                .anyMatch(name -> name.equalsIgnoreCase(normalizedName));
    }

    public void clear() {
        products.clear();
    }
}