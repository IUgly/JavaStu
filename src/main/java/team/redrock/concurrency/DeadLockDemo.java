package team.redrock.concurrency;

public class DeadLockDemo {
    /**
     * 线程t1和线程t2互相等待对方释放锁
     * 避免死锁的方法：
     * 1）避免一个线程同时获取多个锁
     * 2）避免一个线程在锁内同时占用多个资源
     * 3）尝试使用定时锁
     */
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock(){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println(2);
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
