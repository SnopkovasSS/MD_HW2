package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private Product[] products = new Product[5];  // Массив на 5 слотов, private — нет прямого доступа
    private int productCount = 0;  // Счётчик заполненных слотов (для удобства)

    // Добавление продукта (void, ничего не возвращает)
    public void addProduct(Product product) {
        if (productCount < 5) {
            products[productCount] = product;
            productCount++;
            System.out.println("Продукт добавлен!");  // Опционально, для обратной связи
        } else {
            System.out.println("Невозможно добавить продукт");  // Если места нет
        }
    }

    // Общая стоимость (int, ничего не принимает)
    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null) {
                total += products[i].getPrice();
            }
        }
        return total;
    }

    // Печать содержимого (void) — обновлённый для шага 5
    public void printBasket() {
        if (productCount == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        int specialCount = 0;  // Счётчик специальных товаров (скидка + фикс)
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null) {
                System.out.println(products[i].toString());  // Полиморфный вывод по формату toString()
                if (products[i].isSpecial()) {  // Проверяем через метод (без instanceof)
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    // Проверка по имени (boolean)
    public boolean isProductInBasket(String name) {
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && products[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Очистка (void) — проставляем null
    public void clearBasket() {
        for (int i = 0; i < 5; i++) {
            products[i] = null;
        }
        productCount = 0;
        System.out.println("Корзина очищена!");  // Опционально
    }
}