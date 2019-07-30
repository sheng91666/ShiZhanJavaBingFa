/**
 * 2.7 2.直接作用于示例方法
 */
public class AccountingSync2 implements Runnable {
    static AccountingSync2 instance = new AccountingSync2();
    static int i = 0;

    public synchronized void increase() {
        System.out.println("increase-"+Thread.currentThread().getName());
        i++;
    }

    @Override
    public void run() {
        System.out.println("run-"+Thread.currentThread().getName());
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    public static void main(String args[]) throws Exception {
//        Thread t1 = new Thread(instance);
//        Thread t2 = new Thread(instance);
        Thread t1 = new Thread(new AccountingSync2());
        Thread t2 = new Thread(new AccountingSync2());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
