package ocp;

// Violates OCP - need to modify existing code for new payment methods
public class PaymentProcessor {
    public void processPayment(String paymentType, double amount) {
        if (paymentType.equals("CreditCard")) {
            // Process credit card payment
            System.out.println("Processing credit card payment of $" + amount);
        } else if (paymentType.equals("PayPal")) {
            // Process PayPal payment
            System.out.println("Processing PayPal payment of $" + amount);
        }
        // Need to add new else-if for each new payment method
    }
}