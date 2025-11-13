package org.skypro.skyshop;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main(String[] args) {
        // Создаём продукты разных типов для теста (всего 6: 5 разных + 1 для отказа)
        SimpleProduct mouse = new SimpleProduct("Мышь", 2000);  // Обычный
        DiscountedProduct phone = new DiscountedProduct("Смартфон", 50000, 20);  // Скидка 20% -> 40000
        FixPriceProduct book = new FixPriceProduct("Книга");  // Фикс: 1000
        SimpleProduct keyboard = new SimpleProduct("Клавиатура", 3000);  // Обычный
        DiscountedProduct laptop = new DiscountedProduct("Ноутбук", 100000, 10);  // Скидка 10% -> 90000
        SimpleProduct extra = new SimpleProduct("Доп. товар", 1000);  // 6-й, для отказа

        // Создаём корзину
        ProductBasket basket = new ProductBasket();

        System.out.println("=== Демонстрация работы корзины после шагов 1-5 ===");

        // 1. Добавление продукта в корзину (пустая, добавляем первый)
        System.out.println("\n1. Добавление продукта в пустую корзину:");
        basket.addProduct(mouse);

        // 2. Добавление продуктов до заполнения (5 слотов) + попытка 6-го
        System.out.println("\n2. Добавление продуктов до заполнения (смешанные типы):");
        basket.addProduct(phone);
        basket.addProduct(book);
        basket.addProduct(keyboard);
        basket.addProduct(laptop);
        basket.addProduct(extra);  // "Невозможно добавить продукт" (лимит 5)

        // 3. Печать содержимого корзины с несколькими товарами (новый формат!)
        System.out.println("\n3. Печать содержимого корзины:");
        basket.printBasket();  // Выводит с toString(): форматы по типам + "Специальных: 3"

        // 4. Получение стоимости корзины с несколькими товарами
        System.out.println("\n4. Общая стоимость корзины: " + basket.getTotalPrice());  // 137000

        // 5. Поиск товара, который есть в корзине
        System.out.println("\n5. Поиск товара, который есть (Смартфон): " + basket.isProductInBasket("Смартфон"));  // true

        // 6. Поиск товара, которого нет в корзине
        System.out.println("\n6. Поиск товара, которого нет (Доп. товар): " + basket.isProductInBasket("Доп. товар"));  // false (не добавился)

        // 7. Очистка корзины
        System.out.println("\n7. Очистка корзины:");
        basket.clearBasket();

        // 8. Печать содержимого пустой корзины
        System.out.println("\n8. Печать пустой корзины:");
        basket.printBasket();  // "в корзине пусто"

        // 9. Получение стоимости пустой корзины
        System.out.println("\n9. Стоимость пустой корзины: " + basket.getTotalPrice());  // 0

        // 10. Поиск товара по имени в пустой корзине
        System.out.println("\n10. Поиск в пустой корзине (Мышь): " + basket.isProductInBasket("Мышь"));  // false


    }
}
