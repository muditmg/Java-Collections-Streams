
//A Deque (Double-Ended Queue) is a linear data structure that allows insertion and deletion from both ends (front and rear).
// It is part of Java's java.util package and is implemented by ArrayDeque and LinkedList.

// Supports FIFO (Queue) and LIFO (Stack) operations.
//✅ Efficient insertions/removals from both ends (O(1)).
//✅ Implemented using ArrayDeque (faster) and LinkedList.
//✅ No capacity restriction (unless a BlockingDeque is used)

import java.util.ArrayDeque;
import java.util.Deque;

public class DoubleEndedQueue {
    public static void main(String[] args) {

        //Example: Deque Using ArrayDeque
        //How ArrayDeque Works Internally?
        //Uses a circular array for storage.    -->Circular Array (GPT)
        //Expands dynamically when needed.
        //No memory overhead (unlike LinkedList).

        Deque<Integer> deque= new ArrayDeque<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        System.out.println(deque);

        System.out.println("Removed First: " + deque.removeFirst()); // Output: 5
        System.out.println("Removed Last: " + deque.removeLast());

        // Deque as a Stack (LIFO)
        Deque<Integer> stack= new ArrayDeque<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack: " + stack); // Output: [30, 20, 10]

        // Pop elements
        System.out.println("Popped: " + stack.pop()); // Output: 30

        System.out.println("Stack after pop: " + stack);

    }
}
