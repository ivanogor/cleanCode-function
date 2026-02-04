package org.example;

import java.util.List;


public class LoyaltyCardService {

    private final LoyaltyCardProcessor loyaltyCardProcessor;
    private final LoggingService loggingService;

    public LoyaltyCardService(LoyaltyCardProcessor loyaltyCardProcessor, LoggingService loggingService) {
        this.loyaltyCardProcessor = loyaltyCardProcessor;
        this.loggingService = loggingService;
    }

    public void processMonthlyBonus(List<Customer> customers) {
        for (Customer customer : customers) {
            loyaltyCardProcessor.processLoyaltyCard(customer);
        }

        loggingService.logProcessedCustomersCount(customers.size());
    }
}