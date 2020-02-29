package tysunrain.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ConditionDemo
 * @Description: 使用lock方式加锁和解锁
 * @Author: Administrator
 * @Date: 2020/2/26 0026
 * @Version: 1.0
 **/
public class ConditionDemo {

    private static int MAX_LIMIT=10;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static  int i =0;

    private static void odd(){
        while(i<MAX_LIMIT){
            lock.lock();
            try{
                if(i%2==1){
                    System.out.println("奇数："+i);
                    i++;
                    condition.signal();
                }else{
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                lock.unlock();
            }
        }

    }

    private static void event(){

        while(i<MAX_LIMIT){
            lock.lock();
            try{
                if(i%2==0){
                    System.out.println("偶数："+i);
                    i++;
                    condition.signal();
                }else{
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(()->odd()).start();
        new Thread(()->event()).start();
    }
}
