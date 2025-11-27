package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;  // Для basket/
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.*;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Тест SkyShop: SearchEngine с TreeSet (Stream filter + collect) ===\n");

        // Тест 1: SearchEngine (задание 1)
        SearchEngine engine = new SearchEngine();

        // Продукты (implements Searchable)
        SimpleProduct shortSimple = new SimpleProduct("A", 100.0);  // len=1
        SimpleProduct longSimple = new SimpleProduct("Длинный Товар XYZ", 200.0);  // len=18
        FixPriceProduct mediumFix = new FixPriceProduct("Товар B", 150.0);  // len=7
        DiscountedProduct discounted = new DiscountedProduct("Товар C скидка", 40000.0, 0.2);  // len=15, price=32000

        engine.add(shortSimple);
        engine.add(new SimpleProduct("A", 999.0));  // Дубликат игнор
        engine.add(longSimple);
        engine.add(mediumFix);
        engine.add(discounted);

        System.out.println("Добавили продукты (size=4):");
        System.out.println("Size: " + engine.size() + "\n");

        // Статьи
        Article shortArt = new Article("X", "Короткая статья");  // len=1
        Article longArt = new Article("Очень длинная статья про товары", "Текст...");  // len=30
        Article mediumArt = new Article("Статья Y", "Текст про скидки");  // len=8

        engine.add(shortArt);
        engine.add(longArt);
        engine.add(mediumArt);
        engine.add(new Article("X", "Другой текст"));  // Дубликат игнор

        System.out.println("Добавили статьи (общий size=7):");
        System.out.println("Общий size: " + engine.size() + "\n");
        // getAll: sorted descending
        System.out.println("Все items (sorted по длине name, длинные первыми):");
        Set<Searchable> all = engine.getAll();
        for (Searchable item : all) {
            System.out.println("- " + item.getName() + " (длина=" + item.getName().length() + "): " +
                    item.getStringRepresentation() + " (type: " + item.getContentType() + ")");
            if (item instanceof Product p && p.isSpecial()) {
                System.out.println("  (Special с скидкой!)");
            }
        }
        System.out.println("\n");

        // search: Stream filter + collect (задание 1)
        System.out.println("Поиск по 'товар' (filter + TreeSet collect, sorted):");
        Set<Searchable> results = engine.search("товар");
        for (Searchable r : results) {
            System.out.println("- " + r.getName() + " (длина=" + r.getName().length() + "): " +
                    r.getStringRepresentation() + " (type: " + r.getContentType() + ")");
        }
        System.out.println("Результатов: " + results.size() + "\n");  // ~4

        System.out.println("Поиск по '' (пустой TreeSet):");
        System.out.println("Результатов: " + engine.search("").size() + "\n");  // 0

        // Тест 2: ProductBasket (задание 2)
        System.out.println("=== Тест ProductBasket (Stream flatMap, forEach, filter/count) ===\n");
        ProductBasket basket = new ProductBasket();
        basket.addProduct(new SimpleProduct("Apple", 10.0), "Fruits");
        basket.addProduct(new SimpleProduct("Book", 20.0), "Books");
        basket.addProduct(new DiscountedProduct("Laptop", 1000.0, 0.2), "Electronics");  // 800, special
        basket.addProduct(new SimpleProduct("Orange", 15.0), "Fruits");
        basket.addProduct(new FixPriceProduct("Gift", 30.0), "Gifts");

        basket.printBasket();  // Категории, total=875, special=1

        System.out.println("Размер: " + basket.size() + " (ожидание: 5)");
        System.out.println("Total: " + basket.getTotalCost() + " (ожидание: 875.0)");
        System.out.println("Готово! Задание выполнено. ");
    }
}


