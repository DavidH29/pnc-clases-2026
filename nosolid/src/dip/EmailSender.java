package dip;

// Violates DIP - depends on concrete classes
public class EmailSender {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}

