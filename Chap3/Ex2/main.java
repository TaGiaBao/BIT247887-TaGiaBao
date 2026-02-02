class NumberThread extends Thread {

    public NumberThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + " : " + i);
        }
    }
}

public class main {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            NumberThread t = new NumberThread("Thread-" + i);
            t.start();
        }
    }
}
