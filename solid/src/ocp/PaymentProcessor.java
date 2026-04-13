package ocp;

// Closed for modification - doesn't need to change when new payment methods added
public class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.process(amount);
    }
}