/**
 * 2.3 volatile() 不能代替锁且无法保证复合操作的原子性
 */
public class VolatileTest1 {
    static volatile int i = 0;

    public static class PlusTask implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i++;
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public static void main(String args[]) throws Exception {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
            System.out.println("start--i:" + i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("join--i:" + i);
            threads[i].join();
        }

        System.out.println(i);

    }
}
