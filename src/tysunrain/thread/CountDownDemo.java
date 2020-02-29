package tysunrain.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownDemo
 * @Description: 通过CountDownLatch使等待一些线程结束再执行指定线程
 * @Author: Administrator
 * @Date: 2020/2/25 0025
 * @Version: 1.0
 **/
public class CountDownDemo {
    private static CountDownLatch countDownLatch = new CountDownLatch(5);
    public static void coach(){
        String name = Thread.currentThread().getName();
        System.out.println(String.format("教练 %s 准备中。。。", name));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();
            System.out.println(String.format("教练 %s 准备完毕！开始训练！！！", name));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void racer(){
        String name  = Thread.currentThread().getName();
        System.out.println(String.format("运动员 %s 正在准备中。。。", name ));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("运动员 %s 准备完毕！", name));
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        new Thread(()->coach(),"Jhon").start();
        new Thread(()->racer(),"Tom").start();
        new Thread(()->racer(),"Lucy").start();
        new Thread(()->racer(),"Jack").start();
        new Thread(()->racer(),"Mike").start();
        new Thread(()->racer(),"Jan").start();
    }
}

