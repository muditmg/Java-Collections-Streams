
//A thread in Java is the smallest unit of a process that can execute tasks concurrently.
//It allows a program to perform multiple operations simultaneously,

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("Thread is running" + Thread.currentThread().getName());
    }
}

class MyTask extends MyThread implements Runnable{
    private int taskId;

    public MyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is running by " + Thread.currentThread().getName());
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
//    MyThread t1= new MyThread();
//    t1.start();

//    MyTask task = new MyTask();
//    Thread t2= new Thread(task);
//    t2.start();

        //ExecutorService: ExecutorService is part of the java.util.concurrent package and is a higher-level thread management API.
        //uses Thread Pooling: Reuses a fixed number of threads, which avoids the overhead of creating new threads for every task.
        ExecutorService executorService= Executors.newScheduledThreadPool(2);  //Creates a fixed thread pool with Executors.newFixedThreadPool(2). This means at most 2 threads will execute tasks concurrently.
        for(int i=0; i<5; i++){
            executorService.submit(new MyTask(i)); //Submits 5 tasks to the thread pool using executor.submit(new MyTask()).
        }
        executorService.shutdown(); //Calls executor.shutdown() to stop accepting new tasks and allows currently executing tasks to complete.
    }


//Using Runnable over extending Thread in Java is generally considered a better practice.
//Java classes can inherit from only one class (single inheritance).
// If you extend the Thread class, you lose the ability to extend another class because you’ve already used up the single inheritance.
//With Runnable, you implement an interface, so your class can still extend another class.
// With Runnable, the same task can be executed by multiple threads. You can create multiple Thread instances with the same Runnable object.

}
