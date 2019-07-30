import lombok.Data;

public class StopThreadUnsafe {
    public static User u = new User();

    @Data//静态对象不起作用
    public static class User {
        private int id;
        private String name;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User() {
            id = 0;
            name = "0";
        }

        @Override
        public String toString() {
            return "User [ id = " + id + ", name = " + name + "]";
        }
    }

    public static class ChangeObjectThread extends Thread {

        volatile boolean stopFlag = false;

        public void stopFlag(){
            stopFlag = true;
        }

        @Override
        public void run() {

            while (true) {
                this.stopFlag();
                if(stopFlag){
                    System.out.println("exit by stopFlag");
                    break;
                }

                synchronized (u) {
                    System.out.println("ChangeObjectThread 被调用");
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.setId(v);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                    System.out.println("ChangeObjectThread:" + u.toString());
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    System.out.println("ReadObjectThread 被调用");
                    System.out.println("ReadObjectThread:" + u.toString());
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                        System.out.println("不一致");
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
