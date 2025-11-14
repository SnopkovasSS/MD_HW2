package org.skypro.skyshop.search;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

public class SearchEngine {
    private Searchable[] items;
    private int currentSize;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
        this.currentSize = 0;
    }

    public void add(Searchable item) {
        if (item != null && currentSize < items.length) {
            items[currentSize++] = item;
        }
    }

    public Searchable[] search(String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return new Searchable[0];
        }
        searchTerm = searchTerm.toLowerCase();
        Searchable[] results = new Searchable[5];
        int resultCount = 0;
        for (int i = 0; i < currentSize; i++) {
            if (resultCount >= 5) {
                break;
            }
            String term = items[i].getSearchTerm().toLowerCase();
            if (term.contains(searchTerm)) {
                results[resultCount++] = items[i];
            }
        }
        return results;
    }

    // Обновлённый метод: поиск наиболее подходящего (шаг 3+4: + throw BestResultNotFound если не найден)
    public Searchable findMostRelevant(String searchTerm) throws BestResultNotFound {
        if (searchTerm == null || searchTerm.isBlank()) {
            throw new BestResultNotFound(searchTerm);  // Throw для пустого запроса
        }
        searchTerm = searchTerm.toLowerCase();  // Case-insensitive
        int maxOccurrences = -1;
        Searchable bestMatch = null;
        for (int i = 0; i < currentSize; i++) {
            if (items[i] != null) {
                String term = items[i].getSearchTerm().toLowerCase();
                int occurrences = countOccurrences(term, searchTerm);
                if (occurrences > maxOccurrences) {
                    maxOccurrences =occurrences;
                    bestMatch = items[i];
                }
                // Если == max, то первый (не меняем)
            }
        }
        if (bestMatch == null || maxOccurrences == 0) {
            throw new BestResultNotFound(searchTerm);  // Throw если не найден (max=0)
        }
        return bestMatch;
    }

    // Вспомогательный метод: подсчёт вхождений подстроки (из подсказки)
    private int countOccurrences(String text, String sub) {
        if (sub.isBlank()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }

    // Геттер для размера (из предыдущих тестов)
    public int getCurrentSize() {
        return currentSize;
    }
}