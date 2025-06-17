package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main (String[] args) {

    Product apple = new Product("Яблоко", 50);
    Product banana = new Product("Банан", 70);
    Product orange = new Product("Апельсин", 80);
    Product milk = new Product("Молоко", 90);
    Product bread = new Product("Хлеб", 40);
    Product chocolate = new Product("Шоколад", 120);


    ProductBasket basket = new ProductBasket();


        basket.addProduct(apple);
        basket.addProduct(banana);
        basket.addProduct(orange);
        basket.addProduct(milk);
        basket.addProduct(bread);


        basket.addProduct(chocolate);


        System.out.println("Содержимое корзины:");
        basket.printContents();

        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        System.out.println("Есть ли Банан в корзине? " + basket.contains("Банан"));

        System.out.println("Есть ли Виноград в корзине? " + basket.contains("Виноград"));

        basket.clear();

        System.out.println("Содержимое после очистки:");
        basket.printContents();

        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());

        System.out.println("Есть ли Яблоко в пустой корзине? " + basket.contains("Яблоко"));
}
}