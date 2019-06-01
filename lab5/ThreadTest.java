package lessons.lesson5;

public class ThreadTest implements Runnable {
    private static Object shared = new Object();

    public void process() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + i);
            Thread.yield();
        }
    }

    @Override
    public void run() {
        synchronized (shared) {
            process();
        }
    }

    public static void main(String s[]) {
        for (int i = 0; i < 3; i++) {
            new Thread(new ThreadTest(), "Thread-" + i).start();
        }
    }
}

