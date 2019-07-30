/**
 * 2.7 线程安全的概念与synchronized
 *     1.指定加锁对象
 */
public class AccountingSync1 implements Runnable {
    static AccountingSync1 instance = new AccountingSync1();
    static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            synchronized (instance) {
                i++;
            }
        }

    }

    public static void main(String args[]) throws Exception {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
