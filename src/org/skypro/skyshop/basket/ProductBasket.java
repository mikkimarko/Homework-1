package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ProductBasket {

    private Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public int totalPrice() {
        return products.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void printBasket() {

        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
        } else {

            products.values().stream()
                    .flatMap(List::stream)
                    .forEach(System.out::println);

            System.out.println("Итого: " + totalPrice());
            System.out.println("Специальных товаров: " + getSpecialCount());
        }
    }

    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public boolean containsProduct(String name) {
        return products.containsKey(name);
    }

    public void clear() {
        products.clear();
    }

    public List<Product> removeByName(String name) {
        List<Product> removed = products.remove(name);

        if (removed == null) {
            return new ArrayList<>();
        }

        return removed;
    }
}

