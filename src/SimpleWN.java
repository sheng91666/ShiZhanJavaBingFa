/**
 * 2.2.4 wait() notify() demo
 */
public class SimpleWN {
    final static Object obj = new Object();
    static String str = "1";

    public static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println("STR1:" + str);
            synchronized (obj) {
                str = "2";
                System.out.println("STR2:" + str);
                System.out.println(System.currentTimeMillis() + ":T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for obj");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("STR3:" + str);
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
            System.out.println("STR4:" + str);
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println("STR5:" + str);
            synchronized (obj) {
                str = "3";
                System.out.println("STR6:" + str);
                System.out.println(System.currentTimeMillis() + ":T2 start! notify one thread");
                obj.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                System.out.println("STR6:" + str);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("STR7:" + str);
            }
        }
    }

    public static void main(String args[]) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }

}
