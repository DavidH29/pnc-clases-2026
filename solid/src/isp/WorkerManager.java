package isp;

// Flexible composition
public class WorkerManager {
    public void manageWork(Workable worker) {
        worker.work();
    }

    public void manageBreak(Eatable eater) {
        eater.eat();
    }
}
