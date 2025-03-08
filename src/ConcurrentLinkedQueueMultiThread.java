import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//A ConcurrentLinkedQueue is a thread-safe, non-blocking queue that allows multiple threads to access it simultaneously.
//✅ Thread-safe (uses atomic operations, no explicit locking).
//✅ Non-blocking (does not use synchronized or locks).
//✅ FIFO (First-In-First-Out) order.
//✅ Unbounded (grows dynamically as needed).
//✅ High performance in multi-threaded environments.
//✅ Uses CAS (Compare-And-Swap) for atomic updates instead of locks.
class Prodcer implements Runnable {
    private Queue<Integer> queue;

    public Prodcer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            queue.offer(i);
            System.out.println(Thread.currentThread().getName() + " added: " + i);
        }
    }
}

class Con implements Runnable {
    private Queue<Integer> queue;

    public Con(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!queue.isEmpty()) {
            Integer value = queue.poll();
            if (value != null) {
                System.out.println(Thread.currentThread().getName() + " removed: " + value);
            }
        }
    }
}

public class ConcurrentLinkedQueueMultiThread {
    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();

        // Two producers adding elements
        Thread producer1 = new Thread(new Prodcer(queue), "Producer1");
        Thread producer2 = new Thread(new Prodcer(queue), "Producer2");

        // Two consumers removing elements
        Thread consumer1 = new Thread(new Con(queue), "Consumer1");
        Thread consumer2 = new Thread(new Con(queue), "Consumer2");

        producer1.start();
        producer2.start();

        try {
            Thread.sleep(100); // Ensure producers have time to add elements
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer1.start();
        consumer2.start();
    }
}
