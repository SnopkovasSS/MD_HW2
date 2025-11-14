package org.skypro.skyshop.product;



import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    // Конструктор с проверкой
    public Product(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Название продукта не может быть null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустой строкой или состоять только из пробелов");
        }
        this.name = name.trim();  // Опционально: убираем лишние пробелы
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm() {
        return getName();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}
