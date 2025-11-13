package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountedProduct(String name, int basePrice, int discount) {
        super(name);
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        int discountAmount = (basePrice * discount) / 100;
        return basePrice - discountAmount;
    }

    @Override
    public boolean isSpecial() {
        return true;  // Специальный: со скидкой
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";  // Формат: имя: стоимость (скидка%)
    }
}
