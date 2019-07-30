/**
 * 2.2.3线程中断demo
 * 1. 先打印cccc，dddd，
 * 2. 线程睡眠2秒，这2秒中，while执行了很多次，打印了aaaa，bbbb，
 * 3. 2秒后执行t1.interrupt(); 打印eeee，设置了线程中断标记，
 * 4. Thread.currentThread().isInterrupted()==true,进入if，退出while循环
 */
public class test1 {
    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("aaaa");
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("is Interrupted!");
                        break;
                    }
                    System.out.println("bbbb");
                    Thread.yield();
                }
            }
        };
        System.out.println("cccc");
        t1.start();
        System.out.println("dddd");
        Thread.sleep(1);
        t1.interrupt();
        System.out.println("eeee");
    }
}
