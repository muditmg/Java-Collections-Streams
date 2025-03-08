import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentSkipListDemo {
    //ConcurrentSkipListMap is a thread-safe, sorted, and concurrent NavigableMap implementation that provides
    // O(log n) time complexity for insertions, deletions, and lookups.
    // It is part of java.util.concurrent package and is implemented using a Skip List data structure.
    // A Skip List is a probabilistic data structure that allows
    // fast search, insertion, and deletion operations similar to a balanced binary search tree (BST),
    // but without requiring complex tree rotations.
    //A Skip List consists of multiple linked lists stacked on top of each other in levels. Each level is a subset of the one below it.


    public static void main(String[] args) {

        //how multiple threads can safely update a ConcurrentSkipListMap

        ConcurrentSkipListMap<Integer,String> concurrentSkipListMap= new ConcurrentSkipListMap<>();

        ExecutorService executorService= Executors.newScheduledThreadPool(3);

        Runnable task1 = ()-> {
            for(int i=1; i<=5; i++ ){
                concurrentSkipListMap.put(i*10, "Thread1- " + i);
            }
        };
        Runnable task2 = ()-> {
            for(int i=6; i<=10; i++ ){
                concurrentSkipListMap.put(i*10, "Thread2- " + i);
            }
        };

        //Task 3: Retrieve values while other threads are modifying
        Runnable task3 = ()-> {
            for(int i=10; i<=100; i += 10 ){
                System.out.println(("Key: " + i + ", Value: " + concurrentSkipListMap.get(i)));
            }
        };

        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);

        executorService.shutdown();
        try {
            // Wait for termination (Handle InterruptedException)
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("Timeout elapsed before termination.");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted while waiting for termination.");
            Thread.currentThread().interrupt();  // Preserve the interrupted status
        }

        System.out.println("Final Sorted Map: " + concurrentSkipListMap);




    }
}
