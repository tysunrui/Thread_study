package tysunrain.thread;

import javax.naming.spi.DirectoryManager;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadPoolDemo
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2020/2/26 0026
 * @Version: 1.0
 **/
public class ThreadPoolDemo {

    private static ThreadPoolExecutor threadPoolExecutor  = new ThreadPoolExecutor(5,15,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));

    public static void main(String[] args) {
        File rootFile = new File("C:\\Users\\Administrator\\Desktop\\试题\\src");
        var files = rootFile.listFiles();
        for (var file : files) {
            threadPoolExecutor.execute(new RunTask(file,"C:\\Users\\Administrator\\Desktop\\试题\\dest"));
        }
    }
    static class RunTask implements Runnable{
        private File file;
        private String outPutFolder;

        public RunTask(File file, String outPutFolder) {
            this.file = file;
            this.outPutFolder = outPutFolder;
        }

        @Override
        public void run() {
            System.out.println(String.format("线程 %s 正在工作！", Thread.currentThread().getName()));
            try {
                InputStream inputStream = new FileInputStream(file);
                File tempfile = new File(outPutFolder+"/Thread.currentThread().getName()"+" "+file.getName());

                OutputStream outputStream = new FileOutputStream(tempfile);
                byte[] buffer = new byte[1024];
                int byteread = 0;
                while((byteread=inputStream.read(buffer))!=-1){
                    outputStream.write(buffer,0,byteread);
                }
                outputStream.close();
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
