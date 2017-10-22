package pl.codeleak.samples.junit5.mockito;

class NotificationSender {

    private static final String MESSAGE = "Sending message about %s with content %s";

    void sendMessage(String receiver, String content) {
        System.out.println(String.format(MESSAGE, receiver, content));
    }
}