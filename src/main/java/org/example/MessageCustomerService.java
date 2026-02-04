package org.example;


import static org.example.Constants.LIMIT;

public class MessageCustomerService {

    private static final String MAX_BONUS_MESSAGE_SAMPLE = """
            Предупреждение: бонус для клиента %s ограничен лимитом %s
            """;

    private static final String ACCRUAL_BONUS_SAMPLE_MESSAGE = """
            Дорогой %s, вам начислено %s бонусных баллов.
            Текущий баланс: %s
            """;

    private final EmailSender emailSender;

    public MessageCustomerService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void warnAboutMaxCustomerBonus(String id){
        String message = createMessageToWarnAboutMaxCustomerBonus(id);

        System.out.println(message);
    }

    public void informCustomerAboutNewBonus(double bonus, Customer customer){
        String message = createMessageToInformCustomerAboutNewBonus(bonus, customer);

        System.out.println(message);

        emailSender.sendNotification(customer.getEmail(), message);
    }

    private String createMessageToWarnAboutMaxCustomerBonus(String id){
        return String.format(MAX_BONUS_MESSAGE_SAMPLE, id, LIMIT);
    }

    private String createMessageToInformCustomerAboutNewBonus(double bonus, Customer customer){
        return String.format(ACCRUAL_BONUS_SAMPLE_MESSAGE, customer.getName(), (int) bonus, customer.getBonusPoints());
    }
}
