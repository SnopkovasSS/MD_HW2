package org.skypro.skyshop.search;

import org.skypro.skyshop.search.BestResultNotFound;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private List<Searchable> items = new ArrayList<>();  // Динамический список вместо массива

    // Конструктор без maxSize (динамика)
    public SearchEngine() {
        // items = new ArrayList<>();  // Инициализация в поле, динамическая
    }

    // Добавление (теперь без лимита и сообщений об ошибках)
    public void add(Searchable item) {
        if (item != null) {
            items.add(item);
        }
    }

    // Текущий размер (items.size())
    public int getCurrentSize() {
        return items.size();
    }

    // Поиск по термину (return лучший)
    public Searchable find(String term) {
        if (term == null || term.trim().isEmpty()) {
            return null;  // Или throw, но как раньше
        }
        int maxOccurrences = -1;
        Searchable bestMatch = null;
        for (Searchable item : items) {
            int occurrences = countOccurrences(item.getSearchTerm(), term);
            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
                bestMatch = item;
            }
        }
        return bestMatch;  // Если max=0 — null, как раньше (или интегрируй с findMostRelevant)
    }

    // Новый метод: findMostRelevant с исключением (шаг 5, без изменений логики)
    public Searchable findMostRelevant(String term) throws BestResultNotFound {
        if (term == null || term.trim().isEmpty()) {
            throw new BestResultNotFound("Не может быть пустым: '" + term + "'");
        }
        int maxOccurrences = -1;
        Searchable bestMatch = null;
        for (Searchable item : items) {
            int occurrences = countOccurrences(item.getSearchTerm(), term);
            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
                bestMatch = item;
            }
        }
        if (bestMatch == null || maxOccurrences == 0) {
            throw new BestResultNotFound("Не найден лучший результат для: '" + term + "'");
        }
        return bestMatch;
    }

    // Вспомогательный: подсчёт вхождений (как раньше, private)
    private int countOccurrences(String text, String sub) {
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
}