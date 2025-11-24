package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductBasket {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    // Новый метод: удаление всех продуктов по имени (шаг 2)
    public List<Product> removeProductsByName(String name) {
        List<Product> removed = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            return removed;
        }
        String searchName = name.trim();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getName().trim().equalsIgnoreCase(searchName)) {  // Trim для безопасности
                removed.add(p);
                iterator.remove();
            }
        }
        return removed;
    }

    public void clearBasket() {
        products.clear();
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }
        double total = 0.0;
        int specialsCount = 0;
        for (Product p : products) {
            System.out.println(p.toString());
            total += p.getPrice();
            if (p.isSpecial()) {
                specialsCount++;
            }
        }
        System.out.println("Итого: " + (int) total);
        System.out.println("Специальных товаров: " + specialsCount);
    }

    public double getTotalPrice() {
        double sum = 0.0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public boolean isProductInBasket(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        for (Product p : products) {
            if (p.getName().trim().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }
}