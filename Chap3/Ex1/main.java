class WorkerThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread dang chay...");
    }
}

class WorkerRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable dang chay...");
    }
}

public class main {
    public static void main(String[] args) {

        WorkerThread t1 = new WorkerThread();
        t1.start();

        Thread t2 = new Thread(new WorkerRunnable());
        t2.start();
    }
}

