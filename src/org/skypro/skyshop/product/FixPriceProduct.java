package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;  // Только этот

//Продукт с фиксированной ценой (implements Product + Searchable).

public class FixPriceProduct implements Product, Searchable {
    private String name;
    private double fixedPrice;

    public FixPriceProduct(String name, double fixedPrice) {
        this.name = name;
        this.fixedPrice = Math.max(0.0, fixedPrice);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return fixedPrice;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getStringRepresentation() {
        return "FixPriceProduct: name='" + name + "', fixedPrice=" + fixedPrice;
    }

    @Override
    public String getContentType() {
        return "product";  // String
    }

    @Override
    public String toString() {
        return name + " [fix: " + fixedPrice + "]";
    }

    public double getFixedPrice() {
        return fixedPrice;
    }
}