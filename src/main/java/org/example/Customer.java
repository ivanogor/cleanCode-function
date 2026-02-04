package org.example;

import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String email;
    private CardLevel cardLevel;
    private int bonusPoints;
    private List<Purchase> purchases;

    // Конструкторы, геттеры, сеттеры
    public Customer(String id, String name, String email, CardLevel cardLevel,
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
    public CardLevel getCardLevel() { return cardLevel; }
    public int getBonusPoints() { return bonusPoints; }
    public List<Purchase> getPurchases() { return purchases; }
    public void setBonusPoints(int points) { this.bonusPoints = points; }

    public void updateBonusPoints(double bonusPoints) { this.bonusPoints += (int) bonusPoints; }
}
