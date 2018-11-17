package team.redrock.concurrency;

public class ConcurrencyTest {
    /**
     * 执行次数大于一百万，并行才比串行快
     * 原因： 多线程并行，CPU不停地切换线程，切换前会保存上一个任务的状态，
     *       以便下次切换回这个任务时，可以再加载这个任务的状态，任务从保存
     *       到再加载的过程就是一次上下文切换。所以是上下文切换和线程的创建的开销
     *       影响了并行执行的速度
     */
    private static final long count = 10000111;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    /**
     * 并行
     * @throws InterruptedException
     */
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a = 0;
                for (long i = 0;i<count; i++){
                    a+=5;
                }
            }
        });
        thread.start();
        int b =0;
        for (long i=0; i<count; i++){
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.print("concurrency:" +time+"ms,b="+b+"\n");
    }

    /**
     * 串行
     */
    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i =0; i<count; i++){
            a+=5;
        }
        int b = 0;
        for (long i=0; i<count; i++){
            b--;
        }
        long time = System.currentTimeMillis() -start;
        System.out.println("serial" + time+ "ms,b="+b+",a="+a);
    }


}
