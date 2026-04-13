package lsp;

// Sparrow can fly but not swim
public class Sparrow extends Bird implements Flyable {
    public Sparrow() {
        super("Sparrow");
    }

    @Override
    public void fly() {
        System.out.println("Sparrow flying high!");
    }

    @Override
    public void move() {
        fly();
    }

    @Override
    public String getDescription() {
        return "A small bird that can fly";
    }
}
