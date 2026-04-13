package ocp;

// New payment method can be added without modifying existing code
public class CryptoPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing cryptocurrency payment of $" + amount);
    }
}
