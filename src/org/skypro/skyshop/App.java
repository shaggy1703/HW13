package org.skypro.skyshop;

import org.skypro.skyshop.SearchEngine.SearchEngine;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.Arrays;


public class App {
    public static void main(String[] args) {

        Product apple = new SimpleProduct("Яблоко", 50);
        Product banana = new SimpleProduct("Банан", 70);
        Product orange = new SimpleProduct("Апельсин", 80);
        Product milk = new SimpleProduct("Молоко", 90);
        Product bread = new SimpleProduct("Хлеб", 40);
        Product chocolate = new DiscountedProduct("Шоколад", 120, 20); // скидка 20%
        Product salt = new FixPriceProduct("Соль");

        Article article1 = new Article("Как выбрать фрукты", "Выбирайте фрукты с ярким цветом и умеренной мягкостью.");
        Article article2 = new Article("Рецепт шоколадного торта", "Вам понадобится какао, мука и сахар.");
        Article article3 = new Article("Польза молока", "Молоко богато кальцием.");

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

        SearchEngine engine = new SearchEngine(20);

        engine.add(apple);
        engine.add(banana);
        engine.add(chocolate);
        engine.add(article1);
        engine.add(article2);
        engine.add(article3);

        System.out.println("\n\n🔍 Результаты поиска 'шоколад':");
        System.out.println(Arrays.toString(engine.search("шоколад")));

        System.out.println("\n🔍 Результаты поиска 'молоко':");
        System.out.println(Arrays.toString(engine.search("молоко")));

        System.out.println("\n🔍 Результаты поиска 'фрукты':");
        System.out.println(Arrays.toString(engine.search("фрукты")));
    }
}