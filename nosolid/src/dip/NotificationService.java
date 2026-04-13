package dip;

public class NotificationService {
    private EmailSender emailSender;
    private SMSSender smsSender;

    public NotificationService() {
        this.emailSender = new EmailSender(); // Hard dependency
        this.smsSender = new SMSSender(); // Hard dependency
    }

    public void notify(String type, String message) {
        if (type.equals("email")) {
            emailSender.sendEmail(message);
        } else if (type.equals("sms")) {
            smsSender.sendSMS(message);
        }
    }
}
