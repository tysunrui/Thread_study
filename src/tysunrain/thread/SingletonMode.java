package tysunrain.thread;

/**
 * @ClassName: SingletonMode
 * @Description: 各种多线程下的单例模式
 * @Author: Administrator
 * @Date: 2020/2/25 0025
 * @Version: 1.0
 **/
public class SingletonMode {
    public static void main(String[] args) {
//       demoInstanceA(); //测试同步方法的单例模式
//       demoInstanceB(); //使用同步代码块双重检验方式的单利模式
        demoInstanceC(); //使用静态内部类的方法实现的单例模式

    }

    private static void demoInstanceA(){
        Runnable runnable = ()->InstanceA.getInstancA();
        for (int i = 0; i < 100 ; i++) {
            new Thread(runnable).start();
        }
    }
    private static void demoInstanceB(){
        Runnable runnable = ()->InstanceB.getInstanceB();
        for (int i = 0; i < 100 ; i++) {
            new Thread(runnable).start();
        }
    }
    private static void demoInstanceC(){
        Runnable runnable = ()->InstanceC.getInstanceC();
        for (int i = 0; i < 100 ; i++) {
            new Thread(runnable).start();
        }
    }
}

/**
 * @Author chengpunan
 * @Description //使用同步方法的单例模式
 * @Date
 * @Param
 * @return
 **/
class InstanceA{

    private InstanceA(){
        System.out.println("InstanceA 被实例化1次");
    }
    private static InstanceA instanceA=null;
    public static synchronized InstanceA getInstancA(){
        if(null == instanceA){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instanceA = new InstanceA();
        }
        //System.out.println(String.format("被访问 %s 次", ++cnt));
        return instanceA;
    }
}

class InstanceB{
    private static InstanceB instanceB = null;
    private InstanceB(){
        System.out.println("InstanceB 实例化一个对象");
    }
    public static  InstanceB getInstanceB(){
        if(null == instanceB) {
            synchronized (InstanceB.class) {
                if(instanceB == null) {
                    try {
                        Thread.sleep(10);
                        instanceB = new InstanceB();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instanceB;
    }
}

class InstanceC{
    private InstanceC(){
        System.out.println("InstanceC 实例化一个对象！");
    }

    public static  InstanceC getInstanceC(){
        return Instance.instanceC;
    }

     private static class Instance{
        public static InstanceC instanceC = new InstanceC();
    }
}
