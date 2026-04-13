package dip;

// Low-level modules implementing the abstraction
public class EmailSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}
