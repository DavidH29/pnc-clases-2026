package isp;

// Robot forced to implement methods it doesn't need
public class RobotWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Robot working...");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robot doesn't eat!");
    }

    @Override
    public void sleep() {
        throw new UnsupportedOperationException("Robot doesn't sleep!");
    }
}
