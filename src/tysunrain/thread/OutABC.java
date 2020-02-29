package tysunrain.thread;
/**
 * @Author chengpunan
 * @Description //多线程按顺序输出ABC
 * @Date
 * @Param
 * @return
 **/
public class OutABC implements Runnable{

    private String name;
    private Object prev;
    private Object self;

    public OutABC(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }



    @Override
    /**
     * @Author chengpunan
     * @Description //TODO
     * @Date
     * @Param
     * @return
     **/
    public void run() {
        int cnt=10;
        while(cnt>0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    --cnt;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (self){
            self.notify();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable pa = new OutABC("A","C","A");
        Runnable pb = new OutABC("B","A","B");
        Runnable pc = new OutABC("C","B","C");

        Thread thread_a = new Thread(pa);
        Thread thread_b = new Thread(pb);
        Thread thread_c = new Thread(pc);
        thread_a.start();
        Thread.sleep(100);
        thread_b.start();
        Thread.sleep(100);
        thread_c.start();

    }
}
