package org.skypro.skyshop;


import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;


public class App {
    public static void main(String[] args) {

        Product bread = new Product("Хлеб", 50);
        Product milk = new Product("Молоко", 80);
        Product cheese = new Product("Сыр", 200);
        Product apple = new Product("Яблоко", 30);
        Product tea = new Product("Чай", 120);
        Product coffee = new Product("Кофе", 300);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(cheese);
        basket.addProduct(apple);
        basket.addProduct(tea);

        basket.addProduct(coffee);

        basket.printBasket();

        System.out.println("Общая стоимость: " + basket.TotalPrice());

        System.out.println("Есть ли Хлеб? " + basket.containsProduct("Хлеб"));

        System.out.println("Есть ли Кофе? " + basket.containsProduct("Кофе"));

        basket.clear();

        basket.printBasket();

        System.out.println("Стоимость пустой корзины: " + basket.TotalPrice());

        System.out.println("Есть ли Хлеб в пустой корзине? "
                + basket.containsProduct("Хлеб"));
    }
}