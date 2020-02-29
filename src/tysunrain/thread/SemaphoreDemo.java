package tysunrain.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: SemaphoreDemo
 * @Description: 用于控制资源的访问权限（8个工人使用3台机器，每台机器只能供一人使用）,可用来控制执行任务的线程数
 * @Author: Administrator
 * @Date: 2020/2/26 0026
 * @Version: 1.0
 **/
public class SemaphoreDemo {
    private  static  int WORKER_CNT = 8;
    private  static  int MACHINE_CNT = 3;
    private static Semaphore semaphore = new Semaphore(MACHINE_CNT);

    public static void main(String[] args) {
        for (int i = 0; i <WORKER_CNT ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        String name = Thread.currentThread().getName();
                        System.out.println(String.format("工人 %s 正在工作！", name));
                        Thread.sleep(new Random().nextInt(10)*1000);
                        System.out.println(String.format("工人 %s 工作完成，释放机器！", name));
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },"Worker - 0"+i).start();
        }
    }

}
