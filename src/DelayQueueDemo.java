//A DelayQueue is a special type of BlockingQueue where elements are retrieved only after a specified delay.
// It is part of the java.util.concurrent package and is used in task scheduling, caching, and retry mechanisms.

// Thread-safe (designed for concurrent applications).
//✅ Elements are ordered by their delay time (shortest delay first).
//✅ An element cannot be taken from the queue until its delay expires.
//✅ Implements BlockingQueue but requires elements to implement Delayed interface.
//✅ Uses a priority queue internally (sorted based on delay time).

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Delayed{
    private String taskName;
    private long startTime;

    public DelayedTask(String taskName, long delayInSeconds) {
        this.taskName = taskName;  //taskName stores the name of the task.
        this.startTime = System.nanoTime() + TimeUnit.SECONDS.toNanos(delayInSeconds);  //startTime stores the exact time when this task should become available.
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startTime - System.nanoTime(), TimeUnit.NANOSECONDS); //This method calculates the remaining time before a task becomes available.
    }
    //System.nanoTime() gives the current system time in nanoseconds.
    //startTime - System.nanoTime() calculates how much time is left before the task is ready.

    @Override
    public int compareTo(Delayed o) {  //This method orders tasks in DelayQueue by their remaining delay.
        return Long.compare(this.getDelay(TimeUnit.NANOSECONDS), o.getDelay(TimeUnit.NANOSECONDS));
    }
    @Override
    public String toString() {
        return taskName;
    }
}

class Prod implements Runnable {
    private BlockingQueue<DelayedTask> queue;

    public Prod(BlockingQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(new DelayedTask("Task 1 ", 5));
            queue.put(new DelayedTask("Task 2 ", 10));
            queue.put(new DelayedTask("Task 3 ", 20));
            System.out.println("Tasks added to queue...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //put() adds tasks to the queue.
    //Tasks are not immediately available; they wait until their delay expires.
    //The tasks are stored in ascending order of delay.
}

class cons implements Runnable {
    private BlockingQueue<DelayedTask> queue;

    public cons(BlockingQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                DelayedTask task = queue.take();   //take() waits until a task is ready.
                System.out.println("Processing: " + task);  //Tasks are retrieved in order of their delay.
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class DelayQueueDemo {
        public static void main(String[] args) {
        BlockingQueue<DelayedTask> queue = new DelayQueue<>();
        Thread prod = new Thread(new Prod(queue));
        Thread con = new Thread(new cons(queue));

        prod.start();
        con.start();
        }



        //put() adds delayed elements, and take() waits until they are ready.
}
