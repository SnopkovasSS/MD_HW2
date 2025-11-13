package org.skypro.skyshop.search;

import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.article.Article;

public class SearchEngine {
    private final Searchable[] items;  // Фиксированный массив
    private int currentSize = 0;  // Трекер заполненности (для add())

    // Конструктор: размер массива
    public SearchEngine(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть положительным");
        }
        this.items = new Searchable[size];
    }

    // add(): Добавляет Searchable, если место есть (увеличивает currentSize)
    public void add(Searchable item) {
        if (currentSize < items.length) {
            items[currentSize] = item;
            currentSize++;
        } else {
            System.out.println("Массив полон, элемент не добавлен.");  // Опциональный лог, можно убрать
        }
    }

    // search(String query): Возвращает массив из 5 первых совпадений (или меньше)
    public Searchable[] search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new Searchable[0];  // Пустой запрос — пустой результат
        }

        Searchable[] results = new Searchable[5];  // Фикс 5 слотов (с null)
        int foundCount = 0;

        // Перебор всего массива
        for (int i = 0; i < currentSize; i++) {  // Только заполненные
            if (items[i] != null && items[i].getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[foundCount] = items[i];
                foundCount++;
                if (foundCount == 5) {
                    break;  // Прерываем после 5
                }
            }
        }

        // Если меньше 5, остаток null — OK по заданию
        return results;
    }

    // Опционально: геттер для размера (для тестов)
    public int getCurrentSize() {
        return currentSize;
    }
}
