package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;

public class App {
    private static int countOccurrencesForTest(String text, String sub) {
        if (sub == null || sub.trim().isEmpty()) {
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
        System.out.println("=== Демонстрация работы корзины (шаги 1-6) ===");

        SimpleProduct mouse = new SimpleProduct("Мышь", 2000);
        DiscountedProduct phone = new DiscountedProduct("Смартфон", 50000, 20);
        FixPriceProduct book = new FixPriceProduct("Книга");
        SimpleProduct keyboard = new SimpleProduct("Клавиатура", 3000);
        DiscountedProduct laptop = new DiscountedProduct("Ноутбук", 100000, 10);

        ProductBasket basket = new ProductBasket();

        System.out.println("\n1. Добавление в пустую корзину:");
        basket.addProduct(mouse);

        System.out.println("\n2. Добавление смешанных продуктов:");
        basket.addProduct(phone);
        basket.addProduct(book);
        basket.addProduct(keyboard);
        basket.addProduct(laptop);

        System.out.println("\n3. Печать корзины:");
        basket.printBasket();

        System.out.println("\n4. Общая стоимость: " + basket.getTotalPrice());
        System.out.println("5. Есть смартфон? " + basket.isProductInBasket("Смартфон"));
        System.out.println("6. Есть клавиатура? " + basket.isProductInBasket("Клавиатура"));

        // === Демонстрация удаления продуктов по имени (шаг 2) ===
        System.out.println("\n=== Демонстрация удаления продуктов по имени (шаг 2) ===");

        // Удалить существующий ("Смартфон")
        List<Product> removedPhone = basket
                .removeProductsByName("Смартфон");
        System.out.println("Удаленные продукты:");
        for (Product p : removedPhone) {
            System.out.println(p.toString());  // Смартфон: 40000 (20%)
        }
        System.out.println("Количество удаленных: " + removedPhone.size());  // 1

        // Вывести содержимое корзины
        System.out.println("\nСодержимое корзины после удаления:");
        basket.printBasket();  // 4 продукта: Итого: 87000 \n Специальных: 2
        List<Product> removedNone = basket.removeProductsByName("Неизвестный");
        System.out.println("\nУдаление несуществующего:");
        if (removedNone.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product p : removedNone) {
                System.out.println(p.toString());  // Не дойдёт
            }
        }

        // Вывести содержимое корзины (без изменений)
        System.out.println("\nСодержимое корзины после попытки удаления несуществующего:");
        basket.printBasket();  // Тот же 4 продукта

        // 7-10. Очистка и тесты пустой корзины
        System.out.println("\n7. Очистка корзины:");
        basket.clearBasket();
        System.out.println("8. Пустая корзина:");
        basket.printBasket();
        System.out.println("9. Стоимость пустой: " + basket.getTotalPrice());
        System.out.println("10. Поиск в пустой: " + basket.isProductInBasket("Мышь"));

        System.out.println("\n=== Корзина протестирована! (С удалением) ===");

        // === Тест Article (шаг 1) ===
        System.out.println("\n=== Тест Article (шаг 1) ===");
        Article articleTest = new Article("Смартфон: Обзор", "Это крутой гаджет с камерой 108 МП и процессором Snapdragon.");
        System.out.println("Article toString():");
        System.out.println(articleTest.toString());
        System.out.println("Article title: " + articleTest.getTitle());
        System.out.println("Article text: " + articleTest.getText());

        // === Тест Searchable для Article ===
        System.out.println("\n=== Тест Searchable для Article ===");
        System.out.println("Article search term: " + articleTest.getSearchTerm());
        System.out.println("Article content type: " + articleTest.getContentType());
        System.out.println("Article name: " + articleTest.getName());
        System.out.println("Article representation: " + articleTest.getStringRepresentation());

        // === Тест Searchable для наследников Product ===
        System.out.println("\n=== Тест Searchable для наследников Product ===");
        SimpleProduct simple = new SimpleProduct("Simple Товар", 100);
        DiscountedProduct disc = new DiscountedProduct("Discounted Товар", 200, 10);
        FixPriceProduct fix = new FixPriceProduct("Fix Товар");

        System.out.println("\n--- SimpleProduct ---");
        System.out.println("Search term: " + simple.getSearchTerm() + " | Type: " + simple.getContentType() + " | Name: " + simple.getName() + " | Rep: " + simple.getStringRepresentation() + " | toString: " + simple.toString());

        System.out.println("\n--- DiscountedProduct ---");
        System.out.println("Search term: " + disc.getSearchTerm() + " | Type: " + disc.getContentType() + " | Name: " + disc.getName() + " | Rep: " + disc.getStringRepresentation() + " | toString: " + disc.toString());

        System.out.println("\n--- FixPriceProduct ---");
        System.out.println("Search term: " + fix.getSearchTerm() + " | Type: " + fix.getContentType() + " | Name: " + fix.getName() + " | Rep: " + fix.getStringRepresentation() + " | toString: " + fix.toString());

        System.out.println("\n--- Полиморфизм: Общий тест Searchable ---");
        Searchable[] searchables = {simple, disc, fix, articleTest};
        for (Searchable s : searchables) {
            System.out.println(s.getStringRepresentation() + " | Search term length: " + s.getSearchTerm().length());
        }

        // === Демонстрация нового метода поиска (шаг 5: findMostRelevant) ===
        System.out.println("\n=== Демонстрация нового метода поиска (шаг 5: findMostRelevant) ===");
        SearchEngine engine = new SearchEngine();  // ФИКС: без (20), динамический

        SimpleProduct mousePart5 = new SimpleProduct("Мышь для офиса", 2000);
        DiscountedProduct phonePart5 = new DiscountedProduct("Смартфон", 50000, 20);
        FixPriceProduct bookPart5 = new FixPriceProduct("Книга по Java");
        SimpleProduct keyboardPart5 =new SimpleProduct("Клавиатура", 3000);
        SimpleProduct noMatchProduct = new SimpleProduct("Другой товар без совпадений", 1000);

        engine.add(mousePart5);
        engine.add(phonePart5);
        engine.add(bookPart5);
        engine.add(keyboardPart5);
        engine.add(noMatchProduct);

        Article article1 = new Article("Обзор книг по Java", "Книга по Java — отличный выбор. Ещё одна книга по Java идеальна для новичков в программировании.");
        engine.add(article1);
        Article article2 = new Article("Смартфон: Полный обзор", "Смартфон с отличной камерой и процессором. Этот смартфон подойдёт для игр и фото.");
        engine.add(article2);
        Article article3 = new Article("Товары без совпадений", "Это статья о товарах, но без 'книги' или 'смартфона'.");
        engine.add(article3);

        System.out.println("Добавлено элементов: " + engine.getCurrentSize());  // 8

        System.out.println("\n--- Сценарий 1: Объект существует (return) ---");
        try {
            Searchable bestBook = engine.findMostRelevant("книга");
            int occBook = countOccurrencesForTest(bestBook.getSearchTerm(), "книга");
            System.out.println("✓ Найден: " + bestBook.getStringRepresentation() + " (" + occBook + " вхождений)");
        } catch (BestResultNotFound bnfExc1) {
            System.out.println("Поймано исключение: " + bnfExc1.getMessage());
        }

        try {
            Searchable bestMouse = engine.findMostRelevant("мышь");
            int occMouse = countOccurrencesForTest(bestMouse.getSearchTerm(), "мышь");
            System.out.println("✓ Найден: " + bestMouse.getStringRepresentation() + " (" + occMouse + " вхождений)");
        } catch (BestResultNotFound bnfExc2) {
            System.out.println("Поймано исключение: " + bnfExc2.getMessage());
        }

        System.out.println("\n--- Сценарий 2: Исключение (throw BestResultNotFound) ---");
        try {
            Searchable bestNone = engine.findMostRelevant("экзотика");
            System.out.println("✓ Найден: " + bestNone.getStringRepresentation());
        } catch (BestResultNotFound bnfExc3) {
            System.out.println("Поймано исключение: " + bnfExc3.getMessage());
        }

        try {
            Searchable bestEmpty = engine.findMostRelevant("");
            System.out.println("✓ Найден: " + bestEmpty.getStringRepresentation());
        } catch (BestResultNotFound bnfExc4) {
            System.out.println("Поймано исключение: " + bnfExc4.getMessage());
        }

        System.out.println("\n=== Демонстрация findMostRelevant завершена! (Сценарии: return vs throw+catch) ===");

        // === Демонстрация проверки данных (валидация) ===
        System.out.println("\n=== Демонстрация проверки данных (шаг 2: валидация) ===");

        System.out.println("\n--- 1. Simple: null name ---");
        try {
            new SimpleProduct(null, 1000);
        } catch (IllegalArgumentException e1) {
            System.out.println("Поймано: " + e1.getMessage());
        }

        System.out.println("\n--- 2. Simple: blank name ---");
        try {
            new SimpleProduct("   ", 500);
        } catch (IllegalArgumentException e2) {
            System.out.println("Поймано: " + e2.getMessage());
        }

        System.out.println("\n--- 3. Simple: price=0 ---");
        try {
            new SimpleProduct("Товар", 0);
        } catch (IllegalArgumentException e3) {
            System.out.println("Поймано: " + e3.getMessage());
        }

        System.out.println("\n--- 4. Discounted: base=0 ---");
        try {
            new DiscountedProduct("Скидка", 0, 10);
        } catch (IllegalArgumentException e4) {
            System.out.println("Поймано: " + e4.getMessage());
        }

        System.out.println("\n--- 5.Discounted: disc=-5 ---");
        try {
            new DiscountedProduct("Отриц", 1000, -5);
        } catch (IllegalArgumentException e5) {
            System.out.println("Поймано: " + e5.getMessage());
        }

        System.out.println("\n--- 6. Discounted: disc=150 ---");
        try {
            new DiscountedProduct("Сверх", 1000, 150);
        } catch (IllegalArgumentException e6) {
            System.out.println("Поймано: " + e6.getMessage());
        }

        System.out.println("\n--- 7. Fix: blank name ---");
        try {
            new FixPriceProduct("");
        } catch (IllegalArgumentException e7) {
            System.out.println("Поймано: " + e7.getMessage());
        }

        System.out.println("\n--- 8. Валидный Simple ---");
        try {
            SimpleProduct valid = new SimpleProduct("OK Товар", 1500);
            System.out.println("✓ Создан: " + valid.toString());
        } catch (IllegalArgumentException e8) {
            System.out.println("Ошибка: " + e8.getMessage());
        }

        System.out.println("\n=== Валидация протестирована! ===");
        System.out.println("\n=== Все тесты завершены! Модуль SkyShop готов! ===");
    }
}


