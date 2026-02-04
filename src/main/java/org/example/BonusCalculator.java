package org.example;

import static org.example.Constants.DEFAULT_RATE;
import static org.example.Constants.GOLD_CARD_LEVEL_BONUS_PERCENT;
import static org.example.Constants.HIGH_AMOUNT_THRESHOLD;
import static org.example.Constants.HIGH_RATE;
import static org.example.Constants.MEDIUM_AMOUNT_THRESHOLD;
import static org.example.Constants.MEDIUM_RATE;
import static org.example.Constants.SILVER_CARD_LEVEL_BONUS_PERCENT;

public class BonusCalculator {

    private final DateValidator dateValidator;

    public BonusCalculator(DateValidator dateValidator) {
        this.dateValidator = dateValidator;
    }

    public double calculate(Customer customer){
        double bonus = 0;

        for (Purchase purchase : customer.getPurchases()) {
            if (dateValidator.isCurrentMonth(purchase.getMonth())) {
                bonus += calculateDiscount(purchase.getAmount());
            }
        }

        double extraBonusPercentByCardLevel = findExtraBonusPercentByCardLevel(customer.getCardLevel());

        double extraBonusIfHolidaySeason = findExtraBonusIfHolidaySeason();

        return bonus * extraBonusPercentByCardLevel + extraBonusIfHolidaySeason;
    }

    private double findExtraBonusPercentByCardLevel(CardLevel cardLevel){
        if(cardLevel.equals(CardLevel.GOLD)) return GOLD_CARD_LEVEL_BONUS_PERCENT;
        if(cardLevel.equals(CardLevel.SILVER)) return SILVER_CARD_LEVEL_BONUS_PERCENT;

        throw new RuntimeException("Unknown card level: " + cardLevel);
    }

    private double findExtraBonusIfHolidaySeason(){
        return dateValidator.isHoliday() ? 50 : 0;
    }

    private double calculateDiscount(double amount) {
        if (amount > HIGH_AMOUNT_THRESHOLD) return amount * HIGH_RATE;
        if (amount > MEDIUM_AMOUNT_THRESHOLD) return amount * MEDIUM_RATE;
        return amount * DEFAULT_RATE;
    }
}
