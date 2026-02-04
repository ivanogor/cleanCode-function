import java.util.List;

public class DirtyCode {

    public class LoyaltyCardService {

        public void processMonthlyBonus(List<Customer> customers, int currentMonth, boolean isHolidaySeason) {
            for (Customer customer : customers) {
                double bonus = 0;

                // Расчет бонусов за покупки
                for (Purchase purchase : customer.getPurchases()) {
                    if (purchase.getMonth() == currentMonth) {
                        if (purchase.getAmount() > 1000) {
                            bonus += purchase.getAmount() * 0.05;
                        } else if (purchase.getAmount() > 500) {
                            bonus += purchase.getAmount() * 0.03;
                        } else {
                            bonus += purchase.getAmount() * 0.01;
                        }
                    }
                }

                // Дополнительные бонусы
                if (customer.getCardLevel().equals("GOLD")) {
                    bonus *= 1.2;
                } else if (customer.getCardLevel().equals("SILVER")) {
                    bonus *= 1.1;
                }

                if (isHolidaySeason) {
                    bonus += 50;
                }

                // Проверка лимитов
                if (bonus > 1000) {
                    bonus = 1000;
                    System.out.println("Предупреждение: бонус для клиента " +
                            customer.getId() + " ограничен лимитом 1000");
                }

                // Обновление баланса
                customer.setBonusPoints(customer.getBonusPoints() + (int) bonus);

                // Отправка уведомления
                if (bonus > 0) {
                    String message = "Дорогой " + customer.getName() +
                            ", вам начислено " + (int) bonus +
                            " бонусных баллов. Текущий баланс: " +
                            customer.getBonusPoints();
                    sendNotification(customer.getEmail(), message);
                }

                // Логирование
                System.out.println("Обработан клиент " + customer.getId() +
                        ", начислено: " + (int) bonus +
                        ", общий баланс: " + customer.getBonusPoints());
            }

            System.out.println("Всего обработано клиентов: " + customers.size());
        }

        private void sendNotification(String email, String message) {
            // Отправка email
            System.out.println("Отправлено письмо на " + email + ": " + message);
        }

        // Вспомогательные классы
        static class Customer {
            private String id;
            private String name;
            private String email;
            private String cardLevel;
            private int bonusPoints;
            private List<Purchase> purchases;

            // Конструкторы, геттеры, сеттеры
            public Customer(String id, String name, String email, String cardLevel,
                            int bonusPoints, List<Purchase> purchases) {
                this.id = id;
                this.name = name;
                this.email = email;
                this.cardLevel = cardLevel;
                this.bonusPoints = bonusPoints;
                this.purchases = purchases;
            }

            public String getId() { return id; }
            public String getName() { return name; }
            public String getEmail() { return email; }
            public String getCardLevel() { return cardLevel; }
            public int getBonusPoints() { return bonusPoints; }
            public List<Purchase> getPurchases() { return purchases; }
            public void setBonusPoints(int points) { this.bonusPoints = points; }
        }

        static class Purchase {
            private double amount;
            private int month;

            public Purchase(double amount, int month) {
                this.amount = amount;
                this.month = month;
            }

            public double getAmount() { return amount; }
            public int getMonth() { return month; }
        }
    }
}
