package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductBasket {
    private List<Product> products = new ArrayList<>();  // Динамический список (без лимита)

    //Добавляет продукт в корзину (если не null).
    //Выводит "Продукт добавлен!" для демонстрации.

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
            System.out.println("Продукт добавлен!");  // Для теста в main
        }
    }

    //Выводит содержимое корзины: toString() каждого продукта,
     //стоимость, количество специальных (isSpecial()).
    //Для пустой: "в корзине пусто".

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        for (Product p : products) {
            System.out.println(p.toString());  // Полиморфно: toString() наследника
        }
        double total = getTotalPrice();
        System.out.println("Итого: " + total);
        int specialCount = 0;
        for (Product p : products) {
            if (p.isSpecial()) {
                specialCount++;
            }
        }
        System.out.println("Специальных товаров: " + specialCount);
    }

    //Возвращает общую стоимость всех продуктов (сумма getPrice()).
    //Для пустой: 0.

    public double getTotalPrice() {
        double total = 0.0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    //Проверяет наличие продукта по точному имени (getName().equals(name)).
    //Игнорирует регистр? Нет (по задаче equals, case-sensitive).
    //Для пустой: false.

    public boolean isProductInBasket(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        for (Product p : products) {
            if (name.equals(p.getName())) {  // Точное совпадение
                return true;
            }
        }
        return false;
    }

    //Очищает корзину.
    //Выводит "Корзина очищена!" для теста.

    public void clearBasket() {
        products.clear();
        System.out.println("Корзина очищена!");  // Для теста в main
    }

    //Удаляет все продукты с заданным именем (итератор для безопасного удаления).
    //Возвращает List удалённых продуктов (пустой, если ничего нет).

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            return removed;  // Пустой для invalid name
        }
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p != null && name.equals(p.getName())) {  // Точное совпадение
                removed.add(p);
                iterator.remove();  // Безопасное удаление
            }
        }
        return removed;
    }
}
