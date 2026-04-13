package ocp;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}
