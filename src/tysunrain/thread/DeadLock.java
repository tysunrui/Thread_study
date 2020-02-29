package tysunrain.thread;
/**
 * @Author chengpunan
 * @Description //死锁实例
 * @Date
 * @Param
 * @return
 **/
public class DeadLock  {

    public static void main(String[] args) {

        Runnable runnable1 = ()->{
            synchronized ("A"){
                System.out.println("线程1持有锁A！");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("线程放弃A锁，等待被A锁唤醒！");
                    "A".wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized ("B"){
                    System.out.println("线程1持有锁A和B！");
                }
            }
        };
        Runnable runnable2 = ()->{
            synchronized ("B"){
                System.out.println("线程2持有锁B！");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized ("A"){
                    System.out.println("线程2持有锁A和B!");
                    "A".notify();//在同步代码块结束后才会唤醒
                    System.out.println("线程2释放锁A！");
                    System.out.println("唤醒等待A锁的线程！");
                }
                System.out.println("线程2释放锁B！");
            }
        };
        new Thread(runnable1,"thread-01").start();
        new Thread(runnable2,"thread-02").start();
    }
}
