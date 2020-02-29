package tysunrain.thread;

public class ThreadMethod {
    private static int TicketsCnt=10;
    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                    while (TicketsCnt > 0) {
                        synchronized (this) {
                            System.out.println("售票员 " + Thread.currentThread().getName() + " 卖出一张票！，剩余" + --TicketsCnt + " 张票");
                        }
                    }

            }
        };
        Thread solder1 = new Thread(runnable,"1");
        Thread solder2 = new Thread(runnable,"2");
        Thread solder3 = new Thread(runnable,"3");
        Thread solder4 = new Thread(runnable,"4");
        solder1.start();
        solder2.start();
        solder3.start();
        solder4.start();
    }
}
