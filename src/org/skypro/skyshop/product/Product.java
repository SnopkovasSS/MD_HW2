package org.skypro.skyshop.product;



import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    @Override  // Теперь это реализует getName() из Searchable (конфликт снят!)
    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm() {
        return getName();  // Поиск по имени товара
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    // getStringRepresentation() — default из интерфейса, не переопределяем
}

