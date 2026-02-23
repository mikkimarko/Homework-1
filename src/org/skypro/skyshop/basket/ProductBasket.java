package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private Product[] products = new Product[5];

    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return;
            }
        }
        System.out.println("Невозможно добавить продукт");
    }


    public int totalPrice() {
        int sum = 0;
        for (Product product : products) {
            if (product != null) {
                sum += product.getPrice();
            }
        }
        return sum;
    }


    public void printBasket() {
        boolean isEmpty = true;
        int specialCount = 0;

        for (Product product : products) {
            if (product != null) {
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
        for (Product product : products) {
            if (product != null && product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    public void clear() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
    }


}
