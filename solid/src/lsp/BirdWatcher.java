package lsp;

// Client code using correct abstraction
public class BirdWatcher {
    public void watchBirdMove(Bird bird) {
        bird.move(); // Works for all birds
    }

    public void watchBirdFly(Flyable flyableBird) {
        flyableBird.fly(); // Only for birds that can fly
    }
}
