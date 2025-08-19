package myMagazin;

import java.util.Objects;

public class Product {
    private String name; // название продукта
    private double price; // цена продукта

//   Конструктор -создаем через Generate,вызывается при создании объекта
    public Product(String name, double price) {
        setName(name);// сеттер для проверки и установки
        setPrice(price); // сеттер для проверки и установки
    }
// Геттер для имени и цены, return (возвращает имя и цену)
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
//  Установка имени с проверкой
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) { // проверка на пустоту или null
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        if (name.trim().matches("^[0-9]+$")) {
            throw new IllegalArgumentException("Название продукта не должно содержать только цифры");
        }
        if (name.trim().length()< 3) {
            throw new IllegalArgumentException("Название продукта должно быть минимум 3 символа");
        }
        this.name = name.trim(); // удаляем лишние пробелы
    }
//  Установка цены с проверкой
    public void setPrice(double price) {
        if (price < 0) { // цена не должна быть отрицательной
            throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        }
        this.price = price;
    }
//  Переопределение toString - для выводов
    @Override
    public String toString() {
         return name + "(" + price + ")"; // строка с названием и ценой
    }
//  Переопределение  equals - сравнение двух объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // сам с собой
        if (!(o instanceof Product)) return false; // разные типы
        Product p = (Product) o;
        Product product = (Product) o;
        return name.equals(p.name) && Double.compare(p.price, price) == 0; //равны, если название и цена
    }
//  Для работы с хэш -таблицами

    @Override
    public int hashCode() {
        int result = name.hashCode();
        long temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32 ));
        return result;
    }
}

