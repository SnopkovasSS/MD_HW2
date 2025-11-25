package org.skypro.skyshop;

import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.Article;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Тест SkyShop: SearchEngine с TreeSet (сортировка по длине name) ===\n");

        // Создаём SearchEngine
        SearchEngine engine = new SearchEngine();

        // Продукты с разными длинами name (дубли по name игнор)
        SimpleProduct shortSimple = new SimpleProduct("A", 100.0);  // Длина=1
        SimpleProduct longSimple = new SimpleProduct("Длинный Товар XYZ", 200.0);  // Длина=18
        FixPriceProduct mediumFix = new FixPriceProduct("Товар B", 150.0);  // Длина=7
        DiscountedProduct discounted = new DiscountedProduct("Товар C скидка", 40000.0, 20);  // Длина=15

        // Добавляем (дубликат по name не добавится)
        engine.add(shortSimple);
        engine.add(new SimpleProduct("A", 999.0));  // Дубликат по name="A" — игнор!
        engine.add(longSimple);
        engine.add(mediumFix);
        engine.add(discounted);

        System.out.println("Добавили продукты (size=4 уникальных):");
        System.out.println("Size: " + engine.size() + "\n");

        // Статьи с разными длинами title=name
        Article shortArt = new Article("X", "Короткая статья");  // Длина=1
        Article longArt = new Article("Очень длинная статья про товары", "Текст...");  // Длина=30
        Article mediumArt = new Article("Статья Y", "Текст про скидки");  // Длина=8

        engine.add(shortArt);
        engine.add(longArt);
        engine.add(mediumArt);
        engine.add(new Article("X", "Другой текст"));  // Дубликат по title="X" — игнор!

        System.out.println("Добавили статьи (size=3 уникальные, общий size=7):");
        System.out.println("Общий size: " + engine.size() + "\n");

        // Тест getAll() (отсортированный: длинные name первыми)
        System.out.println("Все items (TreeSet, отсортировано по длине name убывание):");
        Set<Searchable> all =engine.getAll();
        for (Searchable item : all) {
            System.out.println("- " + item.getName() + " (длина=" + item.getName().length() + "): " +
                    item.getStringRepresentation());
            if (item instanceof org.skypro.skyshop.product.Product p && p.isSpecial()) {
                System.out.println("  (Специальный с скидкой!)");
            }
        }
        System.out.println("(Ожидаем: длинные первыми, напр. 'Очень длинная...' → 'A')\n");

        // Тест search() (отсортированный TreeSet)
        System.out.println("Поиск по 'товар' (отсортировано: длинные name первыми):");
        Set<Searchable> results = engine.search("товар");
        for (Searchable r : results) {
            System.out.println("- " + r.getName() + " (длина=" + r.getName().length() + "): " +
                    r.getStringRepresentation() + " (type: " + r.getContentType() + ")");
        }
        System.out.println("Результатов: " + results.size() + "\n");  // 3-4 (продукты + статьи с 'товар')

        System.out.println("Поиск по пустой строке (пустой TreeSet):");
        Set<Searchable> empty = engine.search("");
        System.out.println("Результатов: " + empty.size() + "\n");  // 0

        System.out.println("Готово! Сортировка работает. ");
    }
}




