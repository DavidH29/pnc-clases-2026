package dip;

import java.util.List;

// High-level module depends on abstraction
public class NotificationService {
    private final List<MessageSender> senders;

    // Dependency injection through constructor
    public NotificationService(List<MessageSender> senders) {
        this.senders = senders;
    }

    public void notifyAll(String message) {
        for (MessageSender sender : senders) {
            sender.send(message);
        }
    }
}
