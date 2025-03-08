//A ConcurrentLinkedDeque is a thread-safe, non-blocking, double-ended queue (Deque).
//It allows concurrent insertion and removal from both ends using lock-free operations.
// Thread-safe (Lock-free using CAS - Compare-And-Swap).
//✅ Non-blocking (No synchronized or explicit locks used).
//✅ Supports both FIFO (Queue) & LIFO (Stack) operations.
//✅ Unbounded (Grows dynamically as needed).
//✅ Fast in multi-threaded environments due to atomic updates.

//Multi-Threaded Example: Producer-Consumer Using

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

class Producerr implements Runnable{
    private Deque<Integer> deque;

    public Producerr(Deque<Integer> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
            for(int i=1; i<=5; i++){
                deque.addFirst(i);
                System.out.println(Thread.currentThread().getName() + " Added (Front): " + i);
                deque.addLast(i * 10);
                System.out.println(Thread.currentThread().getName() + " Added (rear): " + i*10);
            }
        }
}

class Consumerr implements Runnable{
 private Deque<Integer> deque;

 public Consumerr(Deque<Integer> deque){
     this.deque=deque;
 }
    @Override
    public void run() {
        while(!deque.isEmpty()){
            Integer front= deque.pollFirst();
            if(front!=null){
                System.out.println(Thread.currentThread().getName() + " removed (front): " + front);
            }
            Integer rear = deque.pollLast();
            if(rear!=null){
                System.out.println(Thread.currentThread().getName() + " removed (rear): " + rear);
            }
        }
    }
}


public class ConcurrentLinkedDequeDemo {
    public static void main(String[] args) {
        Deque<Integer> deque = new ConcurrentLinkedDeque<>();

        // Two producers adding elements
        Thread producer1 = new Thread(new Producerr(deque), "Producer1");
        Thread producer2 = new Thread(new Producerr(deque), "Producer2");

        // Two consumers removing elements
        Thread consumer1 = new Thread(new Consumerr(deque), "Consumer1");
        Thread consumer2 = new Thread(new Consumerr(deque), "Consumer2");

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

    //✅ Multiple producers and consumers work efficiently without blocking.
    //✅ CAS ensures atomic updates without locks.
}
