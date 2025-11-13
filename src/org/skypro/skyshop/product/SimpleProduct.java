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
        return false;  // Обычный товар — не специальный
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice();  // Формат: имя: стоимость
    }
}
