package org.example;

import static org.example.Constants.LIMIT;

public class LoyaltyCardProcessor {

    private final BonusCalculator bonusCalculator;
    private final MessageCustomerService messageCustomerService;
    private final LoggingService loggingService;

    public LoyaltyCardProcessor(BonusCalculator bonusCalculator, MessageCustomerService messageCustomerService, LoggingService loggingService) {
        this.bonusCalculator = bonusCalculator;
        this.messageCustomerService = messageCustomerService;
        this.loggingService = loggingService;
    }

    public void processLoyaltyCard(Customer customer) {
        double bonus = bonusCalculator.calculate(customer);

        if(bonus > LIMIT) {
            bonus = LIMIT;
            messageCustomerService.warnAboutMaxCustomerBonus(customer.getId());
        }

        customer.updateBonusPoints(bonus);

        if (bonus > 0) {
            messageCustomerService.informCustomerAboutNewBonus(bonus, customer);
        }

        loggingService.logSuccessCustomerProcess(bonus, customer);
    }
}
