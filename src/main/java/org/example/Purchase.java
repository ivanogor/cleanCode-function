package org.example;

import java.time.Month;

public class Purchase {
    private double amount;
    private Month month;

    public Purchase(double amount, Month month) {
        this.amount = amount;
        this.month = month;
    }

    public double getAmount() { return amount; }
    public Month getMonth() { return month; }
}
