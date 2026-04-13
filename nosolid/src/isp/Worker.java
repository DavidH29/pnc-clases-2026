package isp;

// Violates ISP - fat interface
public interface Worker {
    void work();
    void eat();
    void sleep();
}

