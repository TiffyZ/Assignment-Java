import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*  homework:
        * coding: impl customized thread pool(create customized blocking queue)
        *       1. user can user your thread pool
        *       2. submit runnable into thread pool => execute runnable
*/
public class MyCustomizedThreadPool {
    private final int nThreads;
    private final List<Thread> threads;
    private final BlockingQueue<Runnable> taskQueue;

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task){
            this.task =task;
        }

        @Override
        public void run() {
            while (task!=null||(task=taskQueue.poll())!=null){
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            //no tasks in the queue
            synchronized (threads){
                //从线程集合中删除当前线程
                threads.remove(this);
            }
        }
    }

    public MyCustomizedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.threads = new ArrayList<>();
        this.taskQueue = new LinkedBlockingQueue<>(nThreads);
    }

    public void execute(Runnable task) {
        synchronized (threads) {
            if (threads.size()<nThreads) {
                Worker newWorker = new Worker(task);
                threads.add(newWorker);
                newWorker.start();
            }else {
                try{
                    taskQueue.add(task);
                }catch (IllegalStateException e){
                    System.out.println("The waiting queue is full.");
                }
            }
        }
    }
    public static void main(String[] args) {
        MyCustomizedThreadPool threadPool = new MyCustomizedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            threadPool.execute(() -> System.out.println("thread"));
        }
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> System.out.println("thread"));
        }
    }
}
