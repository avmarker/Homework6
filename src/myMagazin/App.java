package myMagazin;
import homework7.DiscountProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Person person = null;
//  Ввод данных покупателя
        while (true) {
            System.out.print("Введите имя покупателя: ");
            String name = sc.nextLine().trim();
            try {
                if (name.isEmpty()) {
                    System.out.println("Имя не может быть пустым");
                    continue; // повторить ввод
                }
                if (name.length() < 3) {
                    System.out.println("Имя не может быть короче 3 символов");
                    continue;
                }
//Ввод суммы денег
                System.out.print("Введите сумму денег: ");
                String moneyStr = sc.nextLine().trim();// считываем сумму
                double money = Double.parseDouble(moneyStr); // преобразуем в число
                if (money < 0) {
                    System.out.println("Деньги не могут быть отрицательными ");
                    continue;
                }
// Создаем объект Person
                person = new Person(name, money);
                break; //выход из цикла- успешно

            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат суммы");//Если пользователь вводит не число
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());//Выводим сообщение из сеттеров
            }
        }
// Cоздаем список продуктов
        List<Product> products = new ArrayList<>();
// Создаем обычные продукты
        try {
            products.add(new Product("Хлеб", 40));
            products.add(new Product("Молоко", 60));
            products.add(new Product("Торт", 1000));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта:" + e.getMessage());
        }

// Создаем скидочный продукт (для примера кофе со скидкой 20% и сроком до 31.12.2025

        try {
            DiscountProduct discountCoffe = new DiscountProduct("Кофе растворимый", 879, 0.2, "2025-12-31");
            products.add(discountCoffe);// добавляем его в список
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании скидочного продукта:" + e.getMessage());
        }
        // Создаем еще один обычный продукт
        try {
            products.add(new Product("Масло", 150));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта:" + e.getMessage());
        }
// Выводим список доступных продуктов
        System.out.println ("\nДоступные продукты:");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
// Проверка: продукт может быть DiscountProduct
            if (p instanceof DiscountProduct) {
                DiscountProduct dp = (DiscountProduct) p;
                System.out.println(i + ". " + p.getName() + "-цена со скидкой:" + dp.getDiscountedPrice());
            } else {
                System.out.println(i + ". " + p.getName() + "- цена:" + p.getPrice());
            }
        }
// Цена покупок
        boolean somePurchased = false; //для определения, были ли покупки
        for (Product p : products) {
            double priceForPayment; // цена, которую будем списывать
            if (p instanceof DiscountProduct) {
                // У скидочного продукта цена со скидкой
                priceForPayment = ((DiscountProduct) p).getDiscountedPrice();
            } else {
                // обычный продукт -цена без скидки
                priceForPayment = p.getPrice();
            }
            // Создаем временный объект Product с ценой для списания
            Product paymentProduct = new Product(p.getName(), priceForPayment);

            // Пытаемся купить
            if (person.buyProduct(paymentProduct)) {
                System.out.println(person.getName() + "купил" + p.getName());
                somePurchased = true;
            } else {
                System.out.println(person.getName() + "не может позволить себе" + p.getName());
            }
        }
// Итог вводим купленные продукты или сообщение о нулевой покупке
        if (somePurchased) {
            System.out.println("\nИтоговые покупки:");
            System.out.println(person.toString());// список купленных продуктов
        } else {
            System.out.println("Ничего не куплено");
        }
        sc.close();// закрываем сканер
    }
}

