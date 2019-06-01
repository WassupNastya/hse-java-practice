package lessons.lesson5;

public class DeadlockTest {
    public final static Object one = new Object(), two = new Object();

    public static void main(String s[]) {

        Thread t1 = new Thread() {
            public void run() {
                synchronized (one) {
                    Thread.yield();
                    synchronized (two) {
                        System.out.println("Success!");
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                synchronized (two) {
                    Thread.yield();
                    synchronized (one) {
                        System.out.println("Success!");
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}
