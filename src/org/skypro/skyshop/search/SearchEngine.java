package org.skypro.skyshop.search;

import java.util.*;

//Поисковый движок для объектов Searchable (Products и Articles).
// Поддерживает добавление, поиск по Map (sorted по именам) и findMostRelevant.

public class SearchEngine {
    private List<Searchable> searchableList;  // Динамический список (без лимита для простоты)
    private int maxCapacity;  // Для совместимости (не используется в add)

    //Конструктор: инициализирует List и maxCapacity (0=безлимит).

    public SearchEngine(int maxCapacity) {
        this.maxCapacity = maxCapacity >= 0 ? maxCapacity : 0;
        this.searchableList = new ArrayList<>();
    }

    //Добавляет Searchable в List (полиморфно). Null не добавляет.
    // Если maxCapacity>0 и полный — игнорирует (но здесь безлим).

    public void add(Searchable searchable) {
        if (searchable != null && (maxCapacity == 0 || searchableList.size() < maxCapacity)) {
            searchableList.add(searchable);
        }
    }

    //Возвращает размер списка.

    public int getCurrentSize() {
        return searchableList.size();
    }

    //Поиск: возвращает TreeMap<String, Searchable> (отсортировано по именам).
    //Только с >0 вхождений query (case-insensitive) в getSearchTerm().
    //Пустой query или нет совпадений — empty TreeMap.

    public Map<String, Searchable> search(String query) {
        TreeMap<String, Searchable> results = new TreeMap<>();  // Сортированная Map
        if (query == null || query.trim().isEmpty()) {
            return results;  // Empty
        }
        String lowerQuery = query.toLowerCase();
        for (Searchable s : searchableList) {
            if (countOccurrences(s.getSearchTerm(), lowerQuery) > 0) {
                results.put(s.getName(), s);  // Ключ: имя, значение: объект (дубли имена — последний)
            }
        }
        return results;
    }

    //Находит самый релевантный (max вхождений). Throw BestResultNotFound если 0.

    public Searchable findMostRelevant(String query) throws BestResultNotFound {
        if (query == null || query.trim().isEmpty()) {
            throw new BestResultNotFound("Не найден ни один результат для запроса '" + query + "'");
        }
        String lowerQuery = query.toLowerCase();
        Searchable best = null;
        int maxOccurrences = 0;
        for (Searchable s : searchableList) {
            int occ = countOccurrences(s.getSearchTerm(), lowerQuery);
            if (occ > maxOccurrences) {
                maxOccurrences = occ;
                best = s;
            }
        }
        if (best == null || maxOccurrences == 0) {
            throw new BestResultNotFound("Не найден ни один результат для запроса '" + query + "'");
        }
        return best;
    }

    //Подсчёт вхождений sub в text (case-insensitive, без перекрытий).

    private int countOccurrences(String text, String sub) {
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
