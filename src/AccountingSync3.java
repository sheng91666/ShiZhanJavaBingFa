/**
 * 2.7 3.直接作用于静态方法
 */
public class AccountingSync3 implements Runnable {
    //    static AccountingSync3 instance = new AccountingSync3();
    static int i = 0;

    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String args[]) throws Exception {
//        Thread t1 = new Thread(instance);
//        Thread t2 = new Thread(instance);
        Thread t1 = new Thread(new AccountingSync3());
        Thread t2 = new Thread(new AccountingSync3());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
