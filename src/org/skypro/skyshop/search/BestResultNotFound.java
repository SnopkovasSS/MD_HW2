package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String searchTerm) {
        super("Не найден наиболее подходящий объект для запроса '" + searchTerm + "'");
    }
}

