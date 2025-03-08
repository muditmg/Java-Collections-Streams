import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


//ConcurrentHashMap is part of the java.util.concurrent package and
// is designed to be thread-safe while providing better performance than Hashtable or Collections.synchronizedMap.
//It uses fine-grained locking where the map is divided into multiple segments (buckets).
// Only the segment being accessed is locked, so other threads can work on other segments concurrently.

//SynchronizedMap: A single lock is applied to the entire map, meaning if one thread is accessing the map, other threads are blocked.

//ConcurrentHashMap can handle up to 16 segments (default) by default, but the number of segments can be adjusted depending on the concurrency level.
//It supports atomic operations like putIfAbsent, replace, remove, and compute, which allow thread-safe modifications without locking the entire map.
//Reads are never blocked. Multiple threads can read from the map at the same time without blocking each other.
// Writing operations (such as put(), remove()) may still require locks, but reads do not block them.
public class ConcurrentMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer,String > concurrentMap = new ConcurrentHashMap<>();

        Thread t1= new Thread(()->{
            for(int i=0; i<1000;i++) {
                concurrentMap.put(i, "Thread1");
            }
        });
        Thread t2= new Thread(()->{
            for(int i=1000; i<2000;i++) {
                concurrentMap.put(i, "Thread2");
            }
        });
        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Map size : " + concurrentMap.size());
    }

    // Disadvantages of ConcurrentHashMap and solution:
    //Problem 1: While ConcurrentHashMap supports atomic operations like putIfAbsent(), compute(), merge(), etc.,--> CAS: Compare and Swap: gpt)
    //           it does not support multi-step atomic operations (e.g., if you need to read a value,
    //           modify it, and then write it back atomically, you may encounter issues)
    //solution --> Use a synchronized block around the entire block of code if multi-step atomicity is required.
    //Problem 2: While ConcurrentHashMap is designed for high concurrency with multiple readers,
    //           write operations may still experience some performance degradation in highly concurrent environments.
    //           This is because even though the map uses bucket-level locking (via segments or internal locks),
    //           writing to the map can still be slower when multiple threads attempt to modify the same segment.
    //solution -->1.Use a ReadWriteLock outside of ConcurrentHashMap to optimize the read and write operations if you know there will be many more reads than writes.
       //         2.Consider using ConcurrentSkipListMap or CopyOnWriteArrayMap in cases where high concurrency for writing is not critical.
    //Problem 3: ConcurrentHashMap does not guarantee total ordering of the keys,
    //           meaning that iteration over the keys or entries might not be in the same order that they were inserted, and the order can change during concurrent modification.
    //solution --> 1. If ordering is important, consider using a LinkedHashMap or TreeMap for iteration with a predictable order,
    //                or maintain an additional list or queue alongside the map for keeping track of insertion order.
    //             2.If you need thread-safe ordered maps, use ConcurrentSkipListMap (which maintains order and is also concurrent).

// Java 7: segment based locking --> 16 segments
// Java 8: CAS --> compare and swap -- > no locking except during resizing and collision
}
