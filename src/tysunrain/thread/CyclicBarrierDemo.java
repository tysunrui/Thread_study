package tysunrain.thread;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName: CyclicBarrierDemo
 * @Description: 使所有线程同步开始启动，底层使用ReentrantLock 和 Condition 实现
 * @Author: Administrator
 * @Date: 2020/2/26 0026
 * @Version: 1.0
 **/
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);//parties 表示需要等待的线程数
    public static void startThread(){
        String name  = Thread.currentThread().getName();
        System.out.println(String.format("%s 线程准备启动。。。", name));
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s 线程启动完成！ 现在时间是 %s", name,new Date().getTime()));
    }

    public static void main(String[] args) {
        new Thread(()->startThread(),"Thread-01").start();
        new Thread(()->startThread(),"Thread-02").start();
        new Thread(()->startThread(),"Thread-03").start();
    }

}
