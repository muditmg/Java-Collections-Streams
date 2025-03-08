 //SynchronousQueue (Direct Hand-off)
//A SynchronousQueue has no capacity—each put() must wait for a take().

 import java.util.concurrent.SynchronousQueue;

 public class SynchronousQueueDemo {
     public static void main(String[] args) {
         SynchronousQueue<Integer> queue = new SynchronousQueue<>();

         new Thread(() -> {
             try {
                 System.out.println("Putting: 100");
                 queue.put(100); // Blocks until taken
                 System.out.println("Inserted 100");
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }).start();

         new Thread(() -> {
             try {
                 Thread.sleep(2000);
                 System.out.println("Taking: " + queue.take()); // Unblocks producer
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }).start();
     }

     //✅ Producer blocks until the consumer takes the value.
// When to Use BlockingQueue?
     //✅ Multi-threaded applications (Producer-Consumer model).
     //✅ Thread-safe communication between multiple threads.
     //✅ Task queues (e.g., ThreadPoolExecutor internally uses BlockingQueue).
     //✅ Network request handling, logging systems, data buffering.
    // Efficient (O(1)) insertion & removal (locks ensure minimal CPU usage).
}
