package org.example;

public class LoggingService {

    private static final String SUCCESS_CUSTOMER_PROCESS_MESSAGE_SAMPLE = """
            Обработан клиент: %s, начислено: %s, общий баланс: %s
            """;

    private static final String PROCESSED_CUSTOMERS_COUNT_SAMPLE = """
            Всего обработано клиентов: %s
            """;

    public void logSuccessCustomerProcess(double bonus, Customer customer){
        String message = createSuccessCustomerProcessMessage(bonus, customer);

        System.out.println(message);
    }

    private String createSuccessCustomerProcessMessage(double bonus, Customer customer) {
        return String.format(
                SUCCESS_CUSTOMER_PROCESS_MESSAGE_SAMPLE, customer.getId(), (int) bonus, customer.getBonusPoints()
        );
    }

    public void logProcessedCustomersCount(int size) {
        String message = createProcessedCustomersCountMessage(size);

        System.out.println(message);
    }

    private String createProcessedCustomersCountMessage(int size) {
        return String.format(PROCESSED_CUSTOMERS_COUNT_SAMPLE, size);
    }
}
