package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {

        Product apple = new SimpleProduct("Яблоко", 50);
        Product banana = new SimpleProduct("Банан", 70);
        Product orange = new SimpleProduct("Апельсин", 80);
        Product milk = new SimpleProduct("Молоко", 90);
        Product bread = new SimpleProduct("Хлеб", 40);
        Product chocolate = new DiscountedProduct("Шоколад", 120, 20); // скидка 20%
        Product salt = new FixPriceProduct("Соль");

        ProductBasket basket = new ProductBasket();

        basket.addProduct(apple);
        basket.addProduct(banana);
        basket.addProduct(orange);
        basket.addProduct(milk);
        basket.addProduct(bread);
        basket.addProduct(chocolate);
        basket.addProduct(salt);

        System.out.println("Содержимое корзины:");
        basket.printContents();

        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        System.out.println("Есть ли Банан в корзине? " + basket.contains("Банан"));
        System.out.println("Есть ли Виноград в корзине? " + basket.contains("Виноград"));

        basket.clear();

        System.out.println("\nСодержимое после очистки:");
        basket.printContents();

        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());
        System.out.println("Есть ли Яблоко в пустой корзине? " + basket.contains("Яблоко"));
    }
}