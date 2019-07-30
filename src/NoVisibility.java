/**
 * 2.3 volatile()保证数据可见性、有序性
 */
public class NoVisibility {
    volatile private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            System.out.println("run start!");
            while (!ready) ;
            System.out.println("run ready!");
            System.out.println(number);
        }
    }

    public static void main(String args[]) throws Exception {
        new ReaderThread().start();
        System.out.println("sleep 1");
        Thread.sleep(100);
        number = 42;
        ready = true;
        System.out.println("sleep 2");
        Thread.sleep(1000);
    }
}
