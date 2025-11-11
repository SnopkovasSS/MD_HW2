package org.skypro.skyshop;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main(String[] args) {
        // Создаём несколько продуктов для демонстрации
        Product phone = new Product("Смартфон", 50000);
        Product laptop = new Product("Ноутбук", 100000);
        Product headphones = new Product("Наушники", 5000);
        Product mouse = new Product("Мышь", 2000);
        Product keyboard = new Product("Клавиатура", 3000);
        Product monitor = new Product("Монитор", 20000);  // 6-й, чтобы показать отказ

        // Создаём корзину
        ProductBasket basket = new ProductBasket();

        System.out.println("=== Демонстрация работы корзины ===");

        // 1. Добавление продукта в корзину (пустая корзина)
        System.out.println("\n1. Добавление продукта в пустую корзину:");
        basket.addProduct(phone);  // "Продукт добавлен!"

        // 2. Добавление продукта в заполненную корзину (сначала заполним 5 слотов, потом 6-й)
        System.out.println("\n2. Добавление продуктов до заполнения:");
        basket.addProduct(laptop);   // Добавится
        basket.addProduct(headphones);  // Добавится
        basket.addProduct(mouse);   // Добавится
        basket.addProduct(keyboard);  // Добавится (теперь 5/5)
        basket.addProduct(monitor);  // "Невозможно добавить продукт" (нет места)

        // 3. Печать содержимого корзины с несколькими товарами
        System.out.println("\n3. Печать содержимого корзины:");
        basket.printBasket();  // Выводит все товары + "Итого: 183000"

        // 4. Получение стоимости корзины с несколькими товарами
        System.out.println("\n4. Общая стоимость корзины: " + basket.getTotalPrice());

        // 5. Поиск товара, который есть в корзине
        System.out.println("\n5. Поиск товара, который есть (Смартфон): " + basket.isProductInBasket("Смартфон"));

        // 6. Поиск товара, которого нет в корзине
        System.out.println("\n6. Поиск товара, которого нет (Монитор): " + basket.isProductInBasket("Монитор"));

        // 7. Очистка корзины
        System.out.println("\n7. Очистка корзины:");
        basket.clearBasket();  // "Корзина очищена!"

        // 8. Печать содержимого пустой корзины
        System.out.println("\n8. Печать пустой корзины:");
        basket.printBasket();  // "в корзине пусто"

        // 9. Получение стоимости пустой корзины
        System.out.println("\n9. Стоимость пустой корзины: " + basket.getTotalPrice());  // 0

        // 10. Поиск товара по имени в пустой корзине
        System.out.println("\n10. Поиск в пустой корзине (Смартфон): " + basket.isProductInBasket("Смартфон"));  // false

        System.out.println("\n=== Демонстрация завершена! ===");
    }
}
