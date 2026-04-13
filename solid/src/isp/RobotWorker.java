package isp;

// Robot only implements what it needs
public class RobotWorker implements Workable {
    @Override
    public void work() {
        System.out.println("Robot working...");
    }
}
