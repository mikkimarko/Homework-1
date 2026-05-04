package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductBasket {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }


    public int totalPrice() {
        int sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }


        public void printBasket () {
            boolean isEmpty = true;
            int specialCount = 0;

            for (Product product : products) {
                System.out.println(product);
                isEmpty = false;

                if (product.isSpecial()) {
                    specialCount++;
                }
            }

            if (isEmpty) {
                System.out.println("В корзине пусто");
            } else {
                System.out.println("Итого: " + totalPrice());
                System.out.println("Специальных товаров: " + specialCount);
            }
        }


        public boolean containsProduct (String name){
            for (Product product : products) {
                if (product.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }


        public void clear () {
            products.clear();
        }


        public List<Product> removeByName(String name) {

            List<Product> removedProducts = new ArrayList<>();

            java.util.Iterator<Product> iterator = products.iterator();

            while (iterator.hasNext()) {
                Product product = iterator.next();

                if (product.getName().equals(name)) {
                    removedProducts.add(product);
                    iterator.remove();
                }
            }

            return removedProducts;
        }
    }

