package org.skypro.skyshop;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Все тесты проекта SkyShop (с Map в корзине и TreeMap в поиске) ===");

        // 1. Тест ProductBasket (добавление, печать, total, isIn, clear)
        System.out.println("\n=== Тест ProductBasket (Map<String, List<Product>>) ===");
        ProductBasket basket = new ProductBasket();
        System.out.println("1. Добавление в пустую корзину:");
        SimpleProduct emptyTest = new SimpleProduct("Тест", 100);
        basket.addProduct(emptyTest);

        System.out.println("\n2. Добавление смешанных продуктов:");
        SimpleProduct mouse = new SimpleProduct("Мышь", 2000);
        DiscountedProduct smartphone = new DiscountedProduct("Смартфон", 40000, 20);
        SimpleProduct book = new SimpleProduct("Книга", 1000);
        SimpleProduct keyboard = new SimpleProduct("Клавиатура", 3000);
        DiscountedProduct laptop = new DiscountedProduct("Ноутбук", 90000, 10);
        SimpleProduct extra = new SimpleProduct("Доп. товар", 1000);
        basket.addProduct(mouse);
        basket.addProduct(smartphone);
        basket.addProduct(book);
        basket.addProduct(keyboard);
        basket.addProduct(laptop);
        basket.addProduct(extra);

        System.out.println("\n3. Печать корзины:");
        basket.printBasket();
        System.out.println("4. Общая стоимость: " + basket.getTotalPrice());
        System.out.println("5. Есть смартфон? " + basket.isProductInBasket("Смартфон"));
        System.out.println("6. Есть доп. товар? " + basket.isProductInBasket("Доп. товар"));

        System.out.println("\n7. Очистка корзины:");
        basket.clearBasket();
        System.out.println("8. Пустая корзина:");
        basket.printBasket();
        System.out.println("9. Стоимость пустой: " + basket.getTotalPrice());
        System.out.println("10. Поиск в пустой: " + basket.isProductInBasket("Мышь"));
        System.out.println("\n=== Корзина протестирована! (Map поддерживает дубликаты) ===");

        // 2. Тест removeProductsByName (для basket с Map)
        System.out.println("\n=== Тест удаления продуктов по имени (Map) ===");
        ProductBasket basketForRemove = new ProductBasket();
        System.out.println("Заполняем корзину 6 продуктами:");
        basketForRemove.addProduct(mouse);
        basketForRemove.addProduct(smartphone);
        basketForRemove.addProduct(book);
        basketForRemove.addProduct(keyboard);
        basketForRemove.addProduct(laptop);
        basketForRemove.addProduct(extra);
        basketForRemove.printBasket();

        System.out.println("\n1. Удаляем существующий продукт 'Смартфон':");
        List<Product> removedSmartphone = basketForRemove.removeProductsByName("Смартфон");
        System.out.println("Удалено продуктов: " + removedSmartphone.size());
        if (!removedSmartphone.isEmpty()) {
            System.out.println("Удаленные продукты:");
            for (Product p : removedSmartphone) {
                System.out.println("- " + p);
            }
        }

        System.out.println("\n2. Содержимое корзины после удаления:");
        basketForRemove.printBasket();

        System.out.println("\n3. Удаляем несуществующий продукт 'Чашка':");
        List<Product> removedCup = basketForRemove.removeProductsByName("Чашка");
        System.out.println("Удалено продуктов: " + removedCup.size());

        System.out.println("\n4.Содержимое корзины (без изменений):");
        basketForRemove.printBasket();
        System.out.println("\n=== Удаление протестировано! (Map удаляет все по имени) ===");

        // 3. Тест Article и Searchable
        System.out.println("\n=== Тест Article (Searchable) ===");
        Article article = new Article("Смартфон: Обзор", "Это крутой гаджет с камерой 108 МП и процессором Snapdragon.");
        System.out.println("Article toString():\n" + article);
        System.out.println("\nArticle title: " + article.getTitle());
        System.out.println("Article text: " + article.getText());
        System.out.println("\n=== Article протестирован! ===");

        // 4. Тест полиморфизма Searchable для Product наследников
        System.out.println("\n=== Тест Searchable для наследников Product ===");
        SimpleProduct simpleProd = new SimpleProduct("Simple Товар", 100);
        DiscountedProduct discProd = new DiscountedProduct("Discounted Товар", 200, 10);
        FixPriceProduct fixProd = new FixPriceProduct("Fix Товар", 1000);
        System.out.println("\n--- SimpleProduct ---");
        System.out.println("Simple search term: " + simpleProd.getSearchTerm());
        System.out.println("Simple content type: " + simpleProd.getContentType());
        System.out.println("Simple name: " + simpleProd.getName());
        System.out.println("Simple representation: " + simpleProd.getStringRepresentation());
        System.out.println("Simple toString(): " + simpleProd.toString());
        System.out.println("\n--- DiscountedProduct ---");
        System.out.println("Discounted search term: " + discProd.getSearchTerm());
        System.out.println("Discounted content type: " + discProd.getContentType());
        System.out.println("Discounted name: " + discProd.getName());
        System.out.println("Discounted representation: " + discProd.getStringRepresentation());
        System.out.println("Discounted toString(): " + discProd.toString());
        System.out.println("\n--- FixPriceProduct ---");
        System.out.println("Fix search term: " + fixProd.getSearchTerm());
        System.out.println("Fix content type: " + fixProd.getContentType());
        System.out.println("Fix name: " + fixProd.getName());
        System.out.println("Fix representation: " + fixProd.getStringRepresentation());
        System.out.println("Fix toString(): " + fixProd.toString());

        System.out.println("\n--- Полиморфизм: Общий тест Searchable ---");
        Searchable[] searchables = {simpleProd, discProd, fixProd, article};
        for (Searchable s : searchables) {
            System.out.println(s.getName() + " — " + s.getContentType() + " | Search term length: " + s.getSearchTerm().length());
        }
        System.out.println("\n=== Полиморфизм протестирован! ===");

        // 5. Тест SearchEngine (TreeMap + сортировка по именам)
        System.out.println("\n=== Тест SearchEngine (TreeMap + сортировка по именам) ===");
        SearchEngine engine = new SearchEngine(20);

        // Добавляем 8 элементов (Products + Articles)
        SimpleProduct mouseSE = new SimpleProduct("Мышь для офиса", 2000);
        DiscountedProduct phoneSE = new DiscountedProduct("Смартфон", 50000, 20);
        FixPriceProduct bookSE = new FixPriceProduct("Книга по Java", 1500);
        SimpleProduct keyboardSE = new SimpleProduct("Клавиатура", 3000);
        SimpleProduct noMatch = new SimpleProduct("Другой товар без совпадений", 1000);

        engine.add(mouseSE);
        engine.add(phoneSE);
        engine.add(bookSE);
        engine.add(keyboardSE);
        engine.add(noMatch);

        Article artBook = new Article("Обзор книг по Java", "Книга по Java — отличный выбор. Ещё одна книга по Java идеальна для новичков в программировании.");
        Article artPhone = new Article("Смартфон: Полный обзор", "Смартфон с отличной камерой и процессором.Этот смартфон подойдёт для игр и фото.");
        Article artNoMatch = new Article("Товары без совпадений", "Это статья о товарах, но без 'книги' или 'смартфона'.");
        engine.add(artBook);
        engine.add(artPhone);
        engine.add(artNoMatch);

        System.out.println("Добавлено элементов: " + engine.getCurrentSize());

        // Тест search() — Map, вывод sorted по именам
        Map<String, Searchable> resultsBook = engine.search("книга");
        System.out.println("\n--- Поиск 'книга' (Map, отсортировано по именам): " + resultsBook.size() + " найдено:");
        for (Map.Entry<String, Searchable> entry : resultsBook.entrySet()) {
            System.out.println(" - " + entry.getValue().getStringRepresentation());
        }

        Map<String, Searchable> resultsPhone = engine.search("смартфон");
        System.out.println("\n--- Поиск 'смартфон' (Map, отсортировано): " + resultsPhone.size() + " найдено:");
        for (Map.Entry<String, Searchable> entry : resultsPhone.entrySet()) {
            System.out.println(" - " + entry.getValue().getStringRepresentation());
        }

        // Тест findMostRelevant (max вхождений + исключение)
        System.out.println("\n--- Тест findMostRelevant (max вхождений + исключение) ---");
        try {
            Searchable bestBook = engine.findMostRelevant("книга");
            int occBook = countOccurrencesInTerm(bestBook.getSearchTerm(), "книга");
            System.out.println("Лучший для 'книга' (" + occBook + " вхожд.): " + bestBook.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Поймано BestResultNotFound: " + e.getMessage());
        }
        try {
            Searchable bestPhone = engine.findMostRelevant("смартфон");
            int occPhone = countOccurrencesInTerm(bestPhone.getSearchTerm(), "смартфон");
            System.out.println("Лучший для 'смартфон' (" + occPhone + " вхожд.): " + bestPhone.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Поймано BestResultNotFound: " + e.getMessage());
        }
        try {
            Searchable bestMouse = engine.findMostRelevant("мышь");
            System.out.println("Лучший для 'мышь' (1 вхожд.): " + bestMouse.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Поймано BestResultNotFound: " + e.getMessage());
        }
        try {
            engine.findMostRelevant("экзотика");
        } catch (BestResultNotFound e) {
            System.out.println("Поймано BestResultNotFound: " + e.getMessage());
        }
        try {
            engine.findMostRelevant("");
        } catch (BestResultNotFound e) {
            System.out.println("Поймано BestResultNotFound: " + e.getMessage());
        }
        System.out.println("\n=== SearchEngine протестирован! (Map sorted, findMostRelevant OK) ===");

        // 6. Тест исключений (плохие конструкторы)
        System.out.println("\n=== Тест проверки данных (исключения) ===");
        System.out.println("\n--- 1. SimpleProduct с null name ---");
        try {
            new SimpleProduct(null, 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
        System.out.println("\n--- 2. SimpleProduct с blank name ---");
        try {
            new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
        System.out.println("\n--- 3. SimpleProduct с price=0 ---");
        try {
            new SimpleProduct("OK", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
        System.out.println("\n--- 4.DiscountedProduct с basePrice=0 ---");
        try {
            new DiscountedProduct("OK", 0, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
        System.out.println("\n--- 5. DiscountedProduct с discount=-5 ---");
        try {
            new DiscountedProduct("OK", 100, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
        System.out.println("\n--- 6. DiscountedProduct с discount=150 ---");
        try {
            new DiscountedProduct("OK", 100, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
        System.out.println("\n=== Исключения протестированы! (Ловятся в catch) ===");

        System.out.println("\n=== Все тесты завершены! Проект работает с Map и TreeMap. ===");
    }

    // Вспомогательный метод для подсчёта вхождений (для вывода в findMostRelevant)
    private static int countOccurrencesInTerm(String text, String sub) {
        if (text == null || sub == null || sub.trim().isEmpty()) {
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
}



