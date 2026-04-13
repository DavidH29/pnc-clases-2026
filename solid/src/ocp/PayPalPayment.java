package ocp;

public class PayPalPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}
