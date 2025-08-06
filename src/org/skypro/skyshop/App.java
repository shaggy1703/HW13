package org.skypro.skyshop;

import org.skypro.skyshop.SearchEngine.SearchEngine;
import org.skypro.skyshop.SearchEngine.BestResultNotFound;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class App {
    public static void main(String[] args) {

        try {
            Product invalid1 = new SimpleProduct("Шоколад", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            Product invalid2 = new DiscountedProduct("Шоколад", 100, 101);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            Product invalid3 = new SimpleProduct("   ", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

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
        Article article4 = new Article("АБВ", "Короткая статья");
        Article article5 = new Article("АБВГДЕ", "Длинная статья");

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


        Product apple2 = new SimpleProduct("Яблоко", 55);
        basket.addProduct(apple2);

        System.out.println("\n=== ДЕМОНСТРАЦИЯ УДАЛЕНИЯ ПРОДУКТОВ ===");
        System.out.println("Содержимое корзины перед удалением:");
        basket.printContents();

        List<Product> removedProducts = basket.removeProductByName("Яблоко");

        System.out.println("\nУдаленные продукты (Яблоко):");
        if (removedProducts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product product : removedProducts) {
                System.out.println(product);
            }
        }

        System.out.println("\nСодержимое корзины после удаления Яблока:");
        basket.printContents();

        List<Product> removedNonExisting = basket.removeProductByName("Груша");

        System.out.println("\nУдаленные продукты (Груша):");
        if (removedNonExisting.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product product : removedNonExisting) {
                System.out.println(product);
            }
        }

        System.out.println("\nСодержимое корзины после попытки удаления Груши:");
        basket.printContents();

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
        engine.add(article4);
        engine.add(article5);

        engine.add(new SimpleProduct("Яблоко", 60));
        engine.add(new Article("Как выбрать фрукты", "Другое содержание"));

        System.out.println("\nПроверка дубликатов - размер должен быть 7: " +
                engine.getSize());

        testBestSearch(engine, "шоколад");
        testBestSearch(engine, "виноград");

        System.out.println("\n\n🔍 Результаты поиска 'шоколад' (отсортированы по длине названия):");
        Set<Searchable> chocolateResults = engine.search("шоколад");
        for (Searchable item : chocolateResults) {
            System.out.println(item);
        }

        System.out.println("\n🔍 Результаты поиска 'фрукты' (отсортированы по длине названия):");
        Set<Searchable> fruitResults = engine.search("фрукты");
        for (Searchable item : fruitResults) {
            System.out.println(item);
        }

        System.out.println("\n🔍 Результаты поиска 'АБВ' (демонстрация сортировки по длине):");
        Set<Searchable> abvResults = engine.search("АБВ");
        for (Searchable item : abvResults) {
            System.out.println(item);
        }
    }

    private static void testBestSearch(SearchEngine engine, String query) {
        try {
            System.out.println("\n🔍 Поиск лучшего результата для запроса \"" + query + "\":");
            System.out.println(engine.findBestMatch(query));
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}