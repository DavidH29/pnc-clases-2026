package lsp;

// Client code breaks
public class BirdWatcher {
    public void makeBirdFly(Bird bird) {
        bird.fly(); // Throws exception for Penguin
    }
}
