
//A BlockingQueue is a type of queue that supports blocking operations. It is part of the java.util.concurrent package and
// is used in multi-threaded applications to manage producer-consumer scenarios efficiently.

//How It Works:
//If the queue is full, put() will wait until space becomes available.
//If the queue is empty, take() will wait until an element is added.
//No need for explicit synchronization since BlockingQueue handles it internally.


// //BlockingQueue Example: Producer-Consumer Model


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable{

    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            for(int i=1; i<=5; i++){
                System.out.println("Producing" + i);
                queue.put(i);// Blocks if queue is full
                Thread.sleep(1000);

            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();

        }
    }
}
class Consumer implements Runnable{

    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            while(true){
                Integer item= queue.take();  // Blocks if queue is empty
                System.out.println("Consuming " + item);
                Thread.sleep(2000);   // Simulate consumption time

            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();

        }
    }
}
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        Thread producer= new Thread(new Producer(queue));
        Thread consumer= new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
//        try {
//            producer.join(1000);
//            consumer.join(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

}

//Internal Working of BlockingQueue
//Uses Locks & Conditions (ReentrantLock) to manage blocking behavior.
//Producer thread waits if the queue is full.
//Consumer thread waits if the queue is empty.
//Thread-safe & Efficient (avoids busy-waiting by suspending threads).

// Internals of ArrayBlockingQueue
//Uses a fixed-size array.
//Uses two locks (separate for put & take) to reduce contention.
//Fairness option: Can be FIFO-based (default) or priority-based.
