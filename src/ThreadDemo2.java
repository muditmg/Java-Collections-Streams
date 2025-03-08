import java.util.HashMap;
import java.util.Hashtable;

class Counter {
    private int count = 0;

    public void increment() {    //A synchronized method ensures that only one thread at a time executes the code inside it.:
                                    // public synchronized void increment() {
                                      //        count++;
                                       //    }
        count++;
    }

    public int getCount() {    //   public synchronized int getCount() {
                                //    return count;
                                // }
        return count;
    }

//    //Another way to handle this is to use the AtomicInteger class, which provides thread-safe operations for integers:
//    class Counter {
//        private AtomicInteger count = new AtomicInteger(0);
//
//        public void increment() {
//            count.incrementAndGet(); // Thread-safe increment
//        }
//
//        public int getCount() {
//            return count.get();
//        }
//    }
}
public class ThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        // Create multiple threads
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final Count: " + counter.getCount()); //The final count is less than 2000 because t1 and t2 both accessed the increment() method simultaneously,
                                                                  // leading to inconsistent updates.



        // HashMap: Not thread-safe and No internal synchronization unlike Hashtable

        HashMap<Integer, String> map = new HashMap<>();
        // Create multiple threads
        Thread t3 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) map.put(i, "Thread1");

        });
        Thread t4 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) map.put(i, "Thread2");
        });

        t3.start();
        t4.start();

        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Map Size: " + map.size());
    }

  //HashMap is not thread-safe but can be made thread-safe using Collections.synchronizedMap() or ConcurrentHashMap.
}
