package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;  // Только этот импорт

//Продукт со скидкой (implements Product + Searchable).

public class DiscountedProduct implements Product, Searchable {
    private String name;
    private double originalPrice;
    private double discount;

    public DiscountedProduct(String name, double originalPrice, double discount) {
        this.name = name;
        this.originalPrice = Math.max(0.0, originalPrice);
        this.discount = Math.max(0.0, Math.min(1.0, discount));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return originalPrice * (1 - discount);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getStringRepresentation() {
        return "DiscountedProduct: name='" + name + "', originalPrice=" + originalPrice +
                ", discount=" + (discount * 100) + "%, price=" + getPrice();
    }
    @Override
    public String getContentType() {
        return "product";  // String, без enum
    }

    @Override
    public String toString() {
        return name + " [discounted: " + getPrice() + "]";
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getDiscount() {
        return discount;
    }
}
