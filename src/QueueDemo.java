

//A Queue in Java is a FIFO (First-In-First-Out) data structure, meaning elements are added at the rear and removed from the front.
// It is part of Java’s Collection Framework and is defined in the java.util.Queue interface.

//✅ Ordering: Follows FIFO (First-In-First-Out).
//✅ Insertion & Deletion: enqueue() (add), dequeue() (remove).
//✅ Use Cases: Task scheduling, CPU scheduling, printer jobs, message queues, etc.


import java.util.LinkedList;
import java.util.Queue;

//Implementing Queue using LinkedList
public class QueueDemo {
    public static void main(String[] args) {

        Queue<String> queue= new LinkedList<>();
        queue.add("Alice");
        queue.add("Bob");
        queue.add("Charlie");

     for(String s : queue){
         System.out.println(s);
     }
     //   queue.poll() - Removes and returns the front element, returns null if empty (preferred for safety).
        //vs remove()  - 	Removes and returns the front element, throws exception if empty.
        System.out.println("Removed: " + queue.poll());

     //Returns the front element without removing, returns null if empty.
        System.out.println("Front Element: " + queue.peek());
    }
}
