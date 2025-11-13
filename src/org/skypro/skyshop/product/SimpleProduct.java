package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price) {
        super(name);
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }

    // Добавлено: для совместимости с Searchable (если IDE жалуется; иначе опционально)
    @Override
    public String getName() {
        return super.getName();
    }
    // getSearchTerm(), getContentType() и getStringRepresentation() — от Product, не трогаем
}
