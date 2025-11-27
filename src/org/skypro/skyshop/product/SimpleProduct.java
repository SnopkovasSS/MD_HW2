package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;  // Только этот импорт

//Простой продукт (implements Product + Searchable).

public class SimpleProduct implements Product, Searchable {
    private String name;
    private double price;

    public SimpleProduct(String name, double price) {
        this.name = name;
        this.price = Math.max(0.0, price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getStringRepresentation() {
        return "SimpleProduct: name='" + name + "', price=" + price;
    }

    @Override
    public String getContentType() {
        return "product";  // String, без enum
    }

    @Override
    public String toString() {
        return name + " [simple: " + price + "]";
    }
}