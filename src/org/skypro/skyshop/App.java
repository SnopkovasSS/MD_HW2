package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.SearchEngine;

import java.util.Arrays;  // Для Arrays.toString()

public class App {
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
        System.out.println("6. Есть доп. товар? " + basket.isProductInBasket("Доп. товар"));  // false

        // 7. Очистка и тест пустой
        System.out.println("\n7. Очистка корзины:");
        basket.clearBasket();
        System.out.println("8. Пустая корзина:");
        basket.printBasket();  // "в корзине пусто"
        System.out.println("9. Стоимость пустой: " + basket.getTotalPrice());  // 0
        System.out.println("10. Поиск в пустой: " + basket.isProductInBasket("Мышь"));  // false
        System.out.println("\n=== Тест Article (шаг 1) ===");
        Article article1 = new Article("Смартфон: Обзор", "Это крутой гаджет с камерой 108 МП и процессором Snapdragon.");
        System.out.println("Article toString():");
        System.out.println(article1.toString());  // "Смартфон: Обзор\nЭто крутой гаджет..."
        System.out.println("Article title: " + article1.getTitle());  // "Смартфон: Обзор"
        System.out.println("Article text: " + article1.getText());  // "Это крутой гаджет..."

        // === Часть 3: Тест Searchable для Article (шаг 2 нового модуля) ===
        System.out.println("\n=== Тест Searchable для Article ===");
        System.out.println("Article search term: " + article1.getSearchTerm());  // "Смартфон: Обзор\nЭто крутой..."
        System.out.println("Article content type: " + article1.getContentType());  // "ARTICLE"
        System.out.println("Article name: " + article1.getName());  // "Смартфон: Обзор"
        System.out.println("Article representation: " + article1.getStringRepresentation());  // "Смартфон: Обзор — ARTICLE"

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
        Searchable[] searchables = {simple, disc, fix, article1};  // Массив разных типов
        for (Searchable s : searchables) {
            System.out.println(s.getStringRepresentation() + " | Search term length: " + s.getSearchTerm().length());
        }

        System.out.println("\n=== Все тесты Searchable завершены! ===");

        // === Часть 5: Тест SearchEngine (шаг 4: один engine, все товары + статьи, несколько search()) ===
        System.out.println("\n=== Тест SearchEngine (шаг 4: тестирование изменений) ===");
        SearchEngine engine = new SearchEngine(20);  // Один engine, размер 20 (хватит на все)

        // Добавляем ВСЕ товары из предыдущих тестов (корзина + наследники)
        engine.add(mouse);  // "Мышь"
        engine.add(phone);  // "Смартфон"
        engine.add(book);  // "Книга"
        engine.add(keyboard);  // "Клавиатура"
        engine.add(laptop);  // "Ноутбук"
        engine.add(extra);  // "Доп. товар" (даже если не в корзину)
        engine.add(simple);  // "Simple Товар"
        engine.add(disc);  // "Discounted Товар"
        engine.add(fix);  // "Fix Товар"
        // Теперь + extra для разнообразия (не из тестов, но OK)
        engine.add(new SimpleProduct("Компьютер", 80000));  // "Компьютер"

        // Добавляем несколько Article (существующая + новые)
        engine.add(article1);  // "Смартфон: Обзор" (с текстом про гаджет)
        Article article2 = new Article("Книга по Java", "Руководство для начинающих, включая корзины и поиск.");
        engine.add(article2);  // "Книга по Java\nРуководство..."
        Article article3 = new Article("Обзор ноутбуков", "Ноутбуки с скидкой — идеальный выбор.");
        engine.add(article3);  // "Обзор ноутбуков\nНоутбуки с скидкой..."
        Article article4 = new Article("Товары для офиса", "Мышь и клавиатура — must-have для работы.");
        engine.add(article4);  // "Товары для офиса\nМышь и клавиатура..."

        System.out.println("Добавлено элементов: " + engine.getCurrentSize());  // 15 (11 товаров + 4 статьи)

        // Демонстрация search() несколько раз с разными строками
        // 1. Поиск "смартфон" — найдёт phone (PRODUCT), article1 (ARTICLE)
        Searchable[] results1 = engine.search("смартфон");
        System.out.println("\n--- Поиск 'смартфон' (ожидаемо 2 результата): ---");
        System.out.println("Массив результатов (Arrays.toString): " + Arrays.toString(results1));  // [PRODUCT, ARTICLE, null, null, null]
        int count1 = 0;
        for (int i = 0; i < results1.length; i++) {
            if (results1[i] != null) {
                count1++;
                System.out.println(count1 + ". " + results1[i].getStringRepresentation() + " | Term preview: " + results1[i].getSearchTerm().substring(0, Math.min(20, results1[i].getSearchTerm().length())) + "...");
            } else {
                System.out.println((i + 1) + ". null");
            }
        }

        // 2. Поиск "книга" — найдёт book (PRODUCT), article2 (ARTICLE)
        Searchable[] results2 = engine.search("книга");
        System.out.println("\n--- Поиск 'книга' (ожидаемо 2 результата): ---");
        System.out.println("Массив результатов (Arrays.toString): " + Arrays.toString(results2));
        int count2 = 0;
        for (int i = 0; i < results2.length; i++) {
            if (results2[i] != null) {
                count2++;
                System.out.println(count2 + ". " + results2[i].getStringRepresentation() + " | Term preview: " + results2[i].getSearchTerm().substring(0, Math.min(20, results2[i].getSearchTerm().length())) + "...");
            } else {
                System.out.println((i + 1) + ". null");
            }
        }

        // 3. Поиск "товар" — найдёт extra, simple, disc, fix (все PRODUCT, первые 4 + null)
        Searchable[] results3 = engine.search("товар");
        System.out.println("\n--- Поиск 'товар' (ожидаемо 4+ результата, первые 5 с break): ---");
        System.out.println("Массив результатов (Arrays.toString): " + Arrays.toString(results3));
        int count3 = 0;
        for (int i = 0; i < results3.length; i++) {
            if (results3[i] != null) {
                count3++;
                System.out.println(count3 + ". " + results3[i].getStringRepresentation() + " | Term preview: " + results3[i].getSearchTerm().substring(0, Math.min(20, results3[i].getSearchTerm().length())) + "...");
                if (count3 == 5) break;  // Симуляция break в выводе
            } else {
                System.out.println((i + 1) + ".null");
                // 4. Поиск "гаджет" — найдёт article1 (ARTICLE, 1 результат)
                Searchable[] results4 = engine.search("гаджет");
                System.out.println("\n--- Поиск 'гаджет' (1 результат): ---");
                System.out.println("Массив результатов (Arrays.toString): " + Arrays.toString(results4));
                int count4 = 0;
                for (Searchable r : results4) {
                    if (r != null) {
                        count4++;
                        System.out.println(count4 + ". " + r.getStringRepresentation());
                    }
                }
                System.out.println("Найдено: " + count4);

                // 5. Пустой поиск — пустой массив
                Searchable[] emptyResults = engine.search("");
                System.out.println("\n--- Пустой поиск '': ---");
                System.out.println("Массив результатов (Arrays.toString): " + Arrays.toString(emptyResults));  // []
                System.out.println("Длина: " + emptyResults.length);  // 0

                System.out.println("\n=== SearchEngine протестирован! (Полиморфизм: PRODUCT и ARTICLE в результатах) ===");
                System.out.println("\n=== Все тесты завершены! Модуль поиска готов. ===");
            }
        }


    }
        }



