package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;

public class App {

    public static void main(String[] args) {

        try {
            Product wrong = new SimpleProduct("", -10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Product wrongProduct2 = new SimpleProduct("Хлеб", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Product wrongProduct3 = new DiscountedProduct("Молоко", -10, 20);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Product wrongProduct4 = new DiscountedProduct("Чай", 100, 150);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Product bread = new SimpleProduct("Хлеб", 50);
        Product milk = new DiscountedProduct("Молоко", 80, 20);
        Product cheese = new FixPriceProduct("Сыр");
        Product tea = new DiscountedProduct("Чай", 120, 10);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(cheese);
        basket.addProduct(tea);

        System.out.println("Содержимое корзины:");
        basket.printBasket();

        System.out.println("\nУдаляем продукт: Хлеб");

        List<Product> removedProducts = basket.removeByName("Хлеб");

        for (Product product : removedProducts) {
            System.out.println("Удален: " + product);
        }

        System.out.println("\nКорзина после удаления:");
        basket.printBasket();

        System.out.println("\nУдаляем продукт: Телевизор");

        List<Product> removedProducts2 = basket.removeByName("Телевизор");

        if (removedProducts2.isEmpty()) {
            System.out.println("Список пуст");
        }

        basket.printBasket();

        SearchEngine searchEngine = new SearchEngine();

        searchEngine.add(bread);
        searchEngine.add(milk);
        searchEngine.add(cheese);
        searchEngine.add(tea);

        Article article1 = new Article("Как выбрать чай", "Чай бывает черный, зеленый и травяной");
        Article article2 = new Article("Все о молоке", "Молоко бывает коровье и растительное");

        searchEngine.add(article1);
        searchEngine.add(article2);

        try {
            Searchable best = searchEngine.findBestMatch("чай");
            System.out.println("\nЛучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            Searchable best = searchEngine.findBestMatch("телевизор");
            System.out.println(best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        System.out.println("\nПоиск: чай");
        printSearchResults(searchEngine.search("чай"));

        System.out.println("\nПоиск: молоко");
        printSearchResults(searchEngine.search("молоко"));

        System.out.println("\nПоиск: сыр");
        printSearchResults(searchEngine.search("сыр"));
    }

    private static void printSearchResults(List<Searchable> results) {

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
            return;
        }

        for (Searchable item : results) {
            System.out.println(item.getStringRepresentation());
        }
    }
}


