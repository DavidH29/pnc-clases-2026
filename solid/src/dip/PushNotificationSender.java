package dip;

public class PushNotificationSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending push notification: " + message);
    }
}
