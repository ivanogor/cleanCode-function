package org.example;

public class EmailSender {

    private static final String SUCCES_EMAIL_MESSAGE_SAMPLE = """
            "Отправлено письмо на %s : %s
            """;

    public void sendNotification(String email, String message) {
        message = createSuccessEmailMessage(email, message);

        System.out.println(message);
    }

    private String createSuccessEmailMessage(String email, String message) {
        return String.format(SUCCES_EMAIL_MESSAGE_SAMPLE, email, message);
    }
}
