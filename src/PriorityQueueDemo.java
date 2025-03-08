
//A PriorityQueue in Java is a special type of queue where elements are ordered based on their priority,
// instead of the usual FIFO (First-In-First-Out) order.
//PriorityQueue is implemented as a Binary Heap (a Min-Heap by default).
//A Binary Heap is a complete binary tree that maintains the heap property:
//Min-Heap: Parent nodes are always ≤ their children.
//Max-Heap: Parent nodes are always ≥ their children.


import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {

        //Basic Example of PriorityQueue (Natural Order - Min Heap)

        PriorityQueue<Integer> pq= new PriorityQueue<>();
        pq.add(30);
        pq.add(10);
        pq.add(20);
        pq.add(40);
        System.out.println(pq);  //

        while(!pq.isEmpty()){
            System.out.println(pq.poll());
            System.out.println(pq);
        }


        //By default, PriorityQueue sorts elements in natural ascending order.
        //To reverse the order (Max-Heap), we use a custom Comparator.

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
        maxHeap.add(30);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(40);
        System.out.println(maxHeap);
        while(!maxHeap.isEmpty()){
            System.out.println(maxHeap.poll());
            System.out.println(maxHeap);
        }

    }
}
