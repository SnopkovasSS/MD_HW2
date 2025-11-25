package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    private Map<String, List<Product>> productMap;  // Новая структура: Map<имя, List<Product>> для дубликатов

    //Конструктор: инициализирует пустую Map.

    public ProductBasket() {
        this.productMap = new HashMap<>();
    }

    //Добавляет продукт в Map: по ключу getName() — в список (создаёт, если нет).
    //Null не добавляет. Выводит "Продукт добавлен!".

    public void addProduct(Product product) {
        if (product != null) {
            String name = product.getName();
            if (name != null && !name.trim().isEmpty()) {  // Проверка на valid name (как в конструкторах)
                productMap.computeIfAbsent(name, k -> new ArrayList<>()).add(product);
                System.out.println("Продукт добавлен!");  // Для демонстрации (как раньше)
            }
        }
    }

    //Удаляет все продукты по имени (ключ). Возвращает List удалённых (или empty).
    //Удаляет ключ из Map. Выводит "Список пуст", если ничего не удалено.

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = new ArrayList<>();
        if (name != null && !name.trim().isEmpty() && productMap.containsKey(name)) {
            removed = new ArrayList<>(productMap.get(name));  // Копируем список
            productMap.remove(name);  // Удаляем ключ
        } else if (name != null && !name.trim().isEmpty()) {
            System.out.println("Список пуст");  // По сценарию для несуществующего
        }
        return removed;
    }

    //Выводит содержимое корзины: вложенный перебор (все продукты по именам).
    //Формат: имя: цена (спец. если isSpecial).
    //Итого: сумма цен, кол-во специальных.
    //пустая: "в корзине пусто".

    public void printBasket() {
        if (productMap.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        double total = 0.0;
        int specialCount = 0;
        for (Map.Entry<String, List<Product>> entry : productMap.entrySet()) {
            String name = entry.getKey();
            List<Product> products = entry.getValue();
            for (Product p : products) {
                String special = p.isSpecial() ? " (спец.)" : "";
                System.out.println(name + ": " + p.getPrice() + special);
                total += p.getPrice();
                if (p.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + total);
        System.out.println("Специальных товаров: " + specialCount);
    }

    //Общая стоимость: сумма цен всех продуктов (вложенный перебор).

    public double getTotalPrice() {
        double total = 0.0;
        for (List<Product> products : productMap.values()) {
            for (Product p : products) {
                total += p.getPrice();
            }
        }
        return total;
    }

    //Проверяет наличие продукта по имени (ключ в Map).

    public boolean isProductInBasket(String name) {
        return name != null && !name.trim().isEmpty() && productMap.containsKey(name);
    }

    //Очищает корзину: clear Map. Выводит "Корзина очищена!".

    public void clearBasket() {
        productMap.clear();
        System.out.println("Корзина очищена!");
    }

    //Сортирует все продукты в корзине по возрастанию цены (глобально).
    //Собирает все в временный List, сортирует, перестраивает Map (по именам, с сортировкой внутри списков по цене).
    //Для пустой: ничего не делает. Выводит сообщение.

    public void sortByPrice() {
        if (productMap.isEmpty()) {
            return;
        }
        // Собираем все продукты в один List для глобальной сортировки
        List<Product> allProducts = new ArrayList<>();
        for (List<Product> list : productMap.values()) {
            allProducts.addAll(list);
        }
        // Сортируем по цене (возрастание)
        allProducts.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        // Перестраиваем Map: по именам, добавляем отсортированные в списки
        productMap.clear();
        for (Product p : allProducts) {
            String name = p.getName();
            productMap.computeIfAbsent(name, k -> new ArrayList<>()).add(p);
        }
        // Сортируем внутри каждого списка (уже отсортировано глобально, но на всякий)
        for (List<Product> list : productMap.values()) {
            list.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        }
        System.out.println("Корзина отсортирована по цене!");
    }
}




