package lsp;

// Better abstraction
public abstract class Bird {
    protected String name;

    public Bird(String name) {
        this.name = name;
    }

    public abstract void move();
    public abstract String getDescription();
}

