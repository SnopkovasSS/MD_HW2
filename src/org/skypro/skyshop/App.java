package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.BestResultNotFound;  // Для исключения (шаг 4)

import java.util.Arrays;  // Для Arrays.toString() в search()

public class App {
    // Вспомогательный метод для теста в main(): подсчёт вхождений подстроки (как в SearchEngine, но static для App)
    private static int countOccurrencesForTest(String text, String sub) {
        if (sub == null || sub.trim().isEmpty()) {  // Совместимо с Java 8+
            return 0;
        }
        String lowerText = text.toLowerCase();
        String lowerSub = sub.toLowerCase();
        int count = 0;
        int index = 0;
        while ((index = lowerText.indexOf(lowerSub, index)) != -1) {
            count++;
            index += lowerSub.length();
        }
        return count;
    }

    public static void main(String[] args) {
        // === Часть 1: Демонстрация корзины (из предыдущих шагов 1-6) ===
        System.out.println("=== Демонстрация работы корзины (шаги 1-6) ===");

        // Создаём продукты разных типов (смешанные: 2 Simple, 2 Discounted, 1 Fix + 1 для отказа)
        SimpleProduct mouse = new SimpleProduct("Мышь", 2000);  // Обычный
        DiscountedProduct phone = new DiscountedProduct("Смартфон", 50000, 20);  // Скидка 20% -> 40000
        FixPriceProduct book = new FixPriceProduct("Книга");  // Фикс: 1000
        SimpleProduct keyboard = new SimpleProduct("Клавиатура", 3000);  // Обычный
        DiscountedProduct laptop = new DiscountedProduct("Ноутбук", 100000, 10);  // Скидка 10% -> 90000
        SimpleProduct extra = new SimpleProduct("Доп. товар", 1000);  // 6-й, для отказа

        // Создаём корзину
        ProductBasket basket = new ProductBasket();

        // 1. Добавление в пустую корзину
        System.out.println("\n1. Добавление в пустую корзину:");
        basket.addProduct(mouse);

        // 2. Добавление до заполнения + отказ
        System.out.println("\n2. Добавление смешанных продуктов (5 слотов):");
        basket.addProduct(phone);
        basket.addProduct(book);
        basket.addProduct(keyboard);
        basket.addProduct(laptop);
        basket.addProduct(extra);  // "Невозможно добавить"

        // 3. Печать с новым форматом (toString() + isSpecial())
        System.out.println("\n3. Печать корзины:");
        basket.printBasket();  // Мышь: 2000 \n Смартфон: 40000 (20%) \n ... \n Итого: 127000 \n Специальных: 3

        // 4. Стоимость и поиск
        System.out.println("\n4. Общая стоимость: " + basket.getTotalPrice());  // 127000
        System.out.println("5. Есть смартфон? " + basket.isProductInBasket("Смартфон"));  // true
        System.out.println("6. Есть доп. товар? " + basket.isProductInBasket("Доп.товар"));  // false
        // 7. Очистка и тест пустой
        System.out.println("\n7. Очистка корзины:");
        basket.clearBasket();
        System.out.println("8. Пустая корзина:");
        basket.printBasket();  // "в корзине пусто"
        System.out.println("9. Стоимость пустой: " + basket.getTotalPrice());  // 0
        System.out.println("10. Поиск в пустой: " + basket.isProductInBasket("Мышь"));  // false

        System.out.println("\n=== Корзина протестирована! ===");

        // === Часть 2: Тест Article (шаг 1 нового модуля) ===
        System.out.println("\n=== Тест Article (шаг 1) ===");
        Article articleTest = new Article("Смартфон: Обзор", "Это крутой гаджет с камерой 108 МП и процессором Snapdragon.");
        System.out.println("Article toString():");
        System.out.println(articleTest.toString());  // "Смартфон: Обзор\nЭто крутой гаджет..."
        System.out.println("Article title: " + articleTest.getTitle());  // "Смартфон: Обзор"
        System.out.println("Article text: " + articleTest.getText());  // "Это крутой гаджет..."

        // === Часть 3: Тест Searchable для Article (шаг 2 нового модуля) ===
        System.out.println("\n=== Тест Searchable для Article ===");
        System.out.println("Article search term: " + articleTest.getSearchTerm());  // "Смартфон: Обзор\nЭто крутой..."
        System.out.println("Article content type: " + articleTest.getContentType());  // "ARTICLE"
        System.out.println("Article name: " + articleTest.getName());  // "Смартфон: Обзор"
        System.out.println("Article representation: " + articleTest.getStringRepresentation());  // "Смартфон: Обзор — ARTICLE"

        // === Часть 4: Тест Searchable для наследников Product (Simple, Discounted, Fix) ===
        System.out.println("\n=== Тест Searchable для наследников Product ===");
        SimpleProduct simple = new SimpleProduct("Simple Товар", 100);
        DiscountedProduct disc = new DiscountedProduct("Discounted Товар", 200, 10);  // 180 после скидки
        FixPriceProduct fix = new FixPriceProduct("Fix Товар");  // 1000

        // Тест SimpleProduct
        System.out.println("\n--- SimpleProduct ---");
        System.out.println("Simple search term: " + simple.getSearchTerm());  // "Simple Товар"
        System.out.println("Simple content type: " + simple.getContentType());  // "PRODUCT"
        System.out.println("Simple name: " + simple.getName());  // "Simple Товар"
        System.out.println("Simple representation: " + simple.getStringRepresentation());  // "Simple Товар — PRODUCT"
        System.out.println("Simple toString(): " + simple.toString());  // "Simple Товар: 100"

        // Тест DiscountedProduct
        System.out.println("\n--- DiscountedProduct ---");
        System.out.println("Discounted search term: " + disc.getSearchTerm());  // "Discounted Товар"
        System.out.println("Discounted content type: " + disc.getContentType());  // "PRODUCT"
        System.out.println("Discounted name: " + disc.getName());  // "Discounted Товар"
        System.out.println("Discounted representation: " + disc.getStringRepresentation());  // "Discounted Товар — PRODUCT"
        System.out.println("Discounted toString(): " + disc.toString());  // "Discounted Товар: 180 (10%)"

        // Тест FixPriceProduct
        System.out.println("\n--- FixPriceProduct ---");
        System.out.println("Fix search term: " + fix.getSearchTerm());  // "Fix Товар"
        System.out.println("Fix content type: " + fix.getContentType());  // "PRODUCT"
        System.out.println("Fix name: " + fix.getName());  // "Fix Товар"
        System.out.println("Fix representation: " + fix.getStringRepresentation());  // "Fix Товар — PRODUCT"
        System.out.println("Fix toString(): " + fix.toString());  // "Fix Товар: Фиксированная цена 1000"

        // Полиморфный тест: все как Searchable
        System.out.println("\n--- Полиморфизм: Общий тест Searchable ---");
        Searchable[] searchables = {simple, disc, fix, articleTest};  // Массив разных типов
        for (Searchable s : searchables) {
            System.out.println(s.getStringRepresentation() + " | Search term length: " + s.getSearchTerm().length());
        }

        // === Часть 5: Тест SearchEngine (шаг 3+4: + findMostRelevant с throw BestResultNotFound) ===
        System.out.println("\n=== Тест SearchEngine (шаг 4: + исключение BestResultNotFound) ===");
        SearchEngine engine = new SearchEngine(20);  // Один engine, размер 20

        // Добавляем товары (полиморфно, с 1 вхождением для теста)
        SimpleProduct mousePart5 = new SimpleProduct("Мышь для офиса", 2000);  // "мышь" 1 раз
        DiscountedProduct phonePart5 = new DiscountedProduct("Смартфон", 50000, 20);  // "смартфон" 1 раз
        FixPriceProduct bookPart5 = new FixPriceProduct("Книга по Java");  // "книга" 1 раз
        SimpleProduct keyboardPart5 = new SimpleProduct("Клавиатура", 3000);  // 0 для "книга"/"смартфон"
        SimpleProduct noMatchProduct = new SimpleProduct("Другой товар без совпадений", 1000);  // 0 вхождений

        engine.add(mousePart5);
        engine.add(phonePart5);
        engine.add(bookPart5);
        engine.add(keyboardPart5);
        engine.add(noMatchProduct);

        // Добавляем статьи с повторениями (2+ вхождений для демонстрации max)
        Article article1 = new Article("Обзор книг по Java", "Книга по Java — отличный выбор. Ещё одна книга по Java идеальна для новичков в программировании.");  // "книга" 3 раза
        engine.add(article1);
        Article article2 = new Article("Смартфон: Полный обзор", "Смартфон с отличной камерой и процессором. Этот смартфон подойдёт для игр и фото.");  // "смартфон" 3 раза
        engine.add(article2);
        Article article3 = new Article("Товары без совпадений", "Это статья о товарах, но без 'книги' или 'смартфона'.");  // 0 вхождений
        engine.add(article3);

        System.out.println("Добавлено элементов: " + engine.getCurrentSize());  // 8

        // Тест search() (кратко: первые 5 с contains, для контекста)
        Searchable[] resultsBook = engine.search("книга");
        System.out.println("\n--- Поиск 'книга' (Arrays.toString, contains): " + Arrays.toString(resultsBook));  // [book, article1, null...]

        Searchable[] resultsPhone = engine.search("смартфон");
        System.out.println("\n--- Поиск 'смартфон' (Arrays.toString): " + Arrays.toString(resultsPhone));  // [phone, article2, null...]

        // Тест findMostRelevant (несколько: с max>0 — return, с max=0 — throw + catch)
        System.out.println("\n--- Тест findMostRelevant (max вхождений + исключение) ---");

        // 1. "книга": article1 (3 > book 1) — return ARTICLE, без throw
        Searchable bestBook = null;
        try {
            bestBook = engine.findMostRelevant("книга");
            int occBook = countOccurrencesForTest(bestBook.getSearchTerm(), "книга");
            System.out.println("Лучший для 'книга' (" + occBook + " вхожд.): " + bestBook.getStringRepresentation());
        } catch (BestResultNotFound searchExcBook) {  // Уникальное имя
            System.out.println("Поймано BestResultNotFound: " + searchExcBook.getMessage());  // Не сработает (найдено)
        }

        // 2. "смартфон": article2 (3 > phone 1) — return ARTICLE
        Searchable bestPhone = null;
        try {
            bestPhone = engine.findMostRelevant("смартфон");
            int occPhone = countOccurrencesForTest(bestPhone.getSearchTerm(), "смартфон");
            System.out.println("Лучший для 'смартфон' (" + occPhone + " вхожд.): " + bestPhone.getStringRepresentation());
        } catch (BestResultNotFound searchExcPhone) {  // Уникальное имя
            System.out.println("Поймано BestResultNotFound: " + searchExcPhone.getMessage());  // Не сработает
            // 3."мышь": mouse (1) — return PRODUCT
            Searchable bestMouse = null;
            try {
                bestMouse = engine.findMostRelevant("мышь");
                int occMouse = countOccurrencesForTest(bestMouse.getSearchTerm(), "мышь");
                System.out.println("Лучший для 'мышь' (" + occMouse + " вхожд.): " + bestMouse.getStringRepresentation());
            } catch (BestResultNotFound searchExcMouse) {  // Уникальное имя
                System.out.println("Поймано BestResultNotFound: " + searchExcMouse.getMessage());  // Не сработает
            }

            // 4. "экзотика" (max=0) — throw BestResultNotFound
            try {
                Searchable bestNone = engine.findMostRelevant("экзотика");
                // Не дойдёт
            } catch (BestResultNotFound bnfExcNone) {  // Уникальное имя
                System.out.println("Поймано BestResultNotFound: " + bnfExcNone.getMessage());  // "Не найден... для запроса 'экзотика'"
            }

            // 5. Пустой запрос — throw BestResultNotFound (blank)
            try {
                Searchable bestEmpty = engine.findMostRelevant("");
                // Не дойдёт
            } catch (BestResultNotFound bnfExcEmpty) {  // Уникальное имя
                System.out.println("Поймано BestResultNotFound: " + bnfExcEmpty.getMessage());  // "Не найден... для запроса ''"
            }

            System.out.println("\n=== SearchEngine с BestResultNotFound протестирован! (Throw для max=0/blank, полиморфно) ===");

            // === Часть 6: Демонстрация проверки данных (шаг 2: нарочно неверные + try-catch) ===
            System.out.println("\n=== Демонстрация проверки данных (шаг 2) ===");

            // 1. Нарочно неверный SimpleProduct: null name
            System.out.println("\n--- 1. SimpleProduct с null name ---");
            try {
                SimpleProduct invalid1 = new SimpleProduct(null, 1000);
            } catch (IllegalArgumentException e1) {  // Уникальное имя
                System.out.println("Поймано исключение: " + e1.getMessage());  // "Название продукта не может быть null"
            }

            // 2. Нарочно неверный SimpleProduct: blank name (пробелы)
            System.out.println("\n--- 2. SimpleProduct с blank name ---");
            try {
                SimpleProduct invalid2 = new SimpleProduct("   ", 500);
            } catch (IllegalArgumentException e2) {  // Уникальное имя
                System.out.println("Поймано исключение: " + e2.getMessage());  // "Название... пробелов"
            }

            // 3. Нарочно неверный SimpleProduct: price <=0
            System.out.println("\n--- 3. SimpleProduct с price=0 ---");
            try {
                SimpleProduct invalid3 = new SimpleProduct("Товар", 0);
            } catch (IllegalArgumentException e3) {  // Уникальное имя
                System.out.println("Поймано исключение: " + e3.getMessage());  // "Цена... >0"
            }

            // 4. Нарочно неверный DiscountedProduct: basePrice <=0
            System.out.println("\n--- 4. DiscountedProduct с basePrice=0 ---");
            try {
                DiscountedProduct invalid4 = new DiscountedProduct("Скидочный Товар", 0, 10);
            } catch (IllegalArgumentException e4) {  // Уникальное имя
                System.out.println("Поймано исключение: " + e4.getMessage());  // "Базовая цена... >0"
            }

            // 5. Нарочно неверный DiscountedProduct: discount <0
            System.out.println("\n--- 5. DiscountedProduct с discount=-5 ---");
            try {
                DiscountedProduct invalid5 = new DiscountedProduct("Скидка Отрицательная", 1000, -5);
            } catch (IllegalArgumentException e5) {  // Уникальное имя
                System.out.println("Поймано исключение: " + e5.getMessage());  // "Процент... 0-100"
            }

            // 6. Нарочно неверный DiscountedProduct: discount >100
            System.out.println("\n--- 6. DiscountedProduct с discount=150 ---");
            try {
                DiscountedProduct invalid6 = new DiscountedProduct("Скидка Сверх", 1000, 150);
            } catch (IllegalArgumentException e6) {  // Уникальное имя
                System.out.println("Поймано исключение: " + e6.getMessage());  // "Процент... 0-100"
                // 7. Нарочно неверный FixPriceProduct: blank name
                System.out.println("\n--- 7. FixPriceProduct с blank name ---");
                try {
                    FixPriceProduct invalid7 = new FixPriceProduct("");
                } catch (IllegalArgumentException e7) {  // Уникальное имя
                    System.out.println("Поймано исключение: " + e7.getMessage());  // "Название... пустой"
                }

                // 8. Для контраста: один валидный (чтобы показать, что нормальные работают)
                System.out.println("\n--- 8. Валидный SimpleProduct (для сравнения) ---");
                try {
                    SimpleProduct valid = new SimpleProduct("Валидный Товар", 1500);
                    System.out.println("✓ Успешно создан: " + valid.toString());  // "Валидный Товар: 1500"
                } catch (IllegalArgumentException e8) {  // Уникальное имя
                    System.out.println("Поймано исключение: " + e8.getMessage());  // Не должно сработать
                }

                System.out.println("\n=== Демонстрация завершена! (Неверные ловятся в catch с выводом сообщений) ===");
                System.out.println("\n=== Все тесты завершены! ===");
            }
        }
    }
}




