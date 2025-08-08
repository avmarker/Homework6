package myMagazin;
import java.util.ArrayList; // для хранения списка продуктов
import java.util.List; // интерфейс List
import java.util.Objects;

public class Person {
    private String name; // имя
    private double money; // деньги
    private List<Product> products; // список продуктов

// Конструктор через Generate

    public Person(String name, double money) {
        setName(name); //
        setMoney(money); //
        products = new ArrayList<>(); // создаем пустой список
    }
// Геттеры
    public String getName() {
        return name;
    }
    public double getMoney() {
        return money;
    }
    public List<Product> getProducts() {
        return products;
    }
//  Установка имени с прооверкой
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) { //
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        this.name = name.trim();
    }
//  Установка денег с проверкой

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }
//  Метод покупки если есть деньги, покупка происходит
    public boolean buyProduct(Product p) {
        if (p.getPrice() <= this.money) { // есть достаточно денег
            this.money -= p.getPrice(); // списываем деньги
            this.products.add(p); // добовляем продукт
            return true; // успешная покупка
    }
        return false; // недостаточно денег
    }
//  Метод для отображения покупателя и его продуктов
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ");
        if (products.isEmpty()) {
            sb.append("Ничего не куплено");
        } else {
            for (int i = 0; i < products.size(); i++) {
                sb.append(products.get(i).getName());
                if (i < products.size() - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return name.equals(p.name) && Double.compare(p.money, money) == 0 && products.equals(p.products);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        long temp = Double.doubleToLongBits(money);
        result = 31 * result + (int)(temp ^ (temp >>> 32));
        result = 31 * result + products.hashCode();
        return result;
    }
}




