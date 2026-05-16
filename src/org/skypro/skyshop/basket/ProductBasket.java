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
        int sum = 0;

        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                sum += product.getPrice();
            }
        }

        return sum;
    }

    public void printBasket() {
        boolean isEmpty = true;
        int specialCount = 0;

        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                System.out.println(product);
                isEmpty = false;

                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }

        if (isEmpty) {
            System.out.println("В корзине пусто");
        } else {
            System.out.println("Итого: " + totalPrice());
            System.out.println("Специальных товаров: " + specialCount);
        }
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

