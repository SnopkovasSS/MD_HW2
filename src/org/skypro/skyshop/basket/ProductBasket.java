package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

//Корзина: Map<String, List<Product>> (категория → товары).
//Stream API без циклов: flatMap для продуктов, forEach для print, filter/count для special, sum для total/size.
 //Вывод тот же, как до изменений.

public class ProductBasket {
    private Map<String, List<Product>> products = new HashMap<>();

    //Добавление: computeIfAbsent (без циклов).

    public void addProduct(Product product, String category) {
        products.computeIfAbsent(category, k -> new ArrayList<>()).add(product);
    }

    //Общая стоимость (задание 2): flatMap + mapToDouble + sum (без циклов).
    //Скидки учтены в getPrice().

    public double getTotalCost() {
        return products.values().stream()
                .flatMap(Collection::stream)  // Плоский Stream<Product> из всех List
                .mapToDouble(Product::getPrice)  // mapToDouble (для double price; если int — mapToInt)
                .sum();  // Sum всех цен
    }

    //Печать (задание 2): forEach на Map (по категориям) + forEach на List (по продуктам; терминальные операции).
    //Special count из приватного метода (Stream).

    public void printBasket() {
        System.out.println("=== Корзина продуктов ===");
        products.forEach((category, productList) -> {  // forEach на Map (без циклов)
            System.out.println("\nКатегория: " + category);
            productList.forEach(product ->  // forEach на List (терминальная, без Stream для простоты)
                    System.out.println("  - " + product.getName() + " (цена: " + product.getPrice() +
                            ", тип: " + product.getClass().getSimpleName() + ")"));
        });
        long specialCount = getSpecialCount();  // Приватный Stream метод
        System.out.println("\nОбщая стоимость: " + getTotalCost());
        System.out.println("Special продуктов: " + specialCount);
        System.out.println("====================\n");
    }

    //Приватный: Special count (задание 2): flatMap + filter(isSpecial) + count (без циклов).
    //isSpecial() = true для DiscountedProduct.
    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)  // Filter полиморфно
                .count();  // Count special
    }
    //Размер: mapToInt + sum (Stream, без flatMap; сумма размеров List).
    public int size() {
        return (int) products.values().stream()
                .mapToInt(List::size)  // Размер каждого List
                .sum();  // Сумма
    }
    //Очистка.

    public void clear() {
        products.clear();
    }
}
