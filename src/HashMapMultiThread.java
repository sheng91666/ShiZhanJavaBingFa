import java.util.HashMap;
import java.util.Map;

/**
 * 2.8 并发下的map
 */
public class HashMapMultiThread {
    static Map<String, String> map = new HashMap();

    public static class AddThread implements Runnable {

        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i += 2) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));

            }
        }
    }

    public static void main(String args[]) throws Exception {
        Thread t1 = new Thread(new HashMapMultiThread.AddThread(0));
        Thread t2 = new Thread(new HashMapMultiThread.AddThread(0));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
