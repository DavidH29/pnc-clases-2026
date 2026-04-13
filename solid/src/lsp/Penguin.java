package lsp;

// Penguin can swim but not fly
public class Penguin extends Bird implements Swimmable {
    public Penguin() {
        super("Penguin");
    }

    @Override
    public void swim() {
        System.out.println("Penguin swimming gracefully!");
    }

    @Override
    public void move() {
        swim();
    }

    @Override
    public String getDescription() {
        return "A flightless bird that swims";
    }
}
