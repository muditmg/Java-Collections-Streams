import java.util.LinkedList;
import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> stack= new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.capacity());
        System.out.println(stack.peek());
        System.out.println(stack.search(4));  //Returns the 1-based position

        //LinkedList as stack

        LinkedList<Integer> linkedList= new LinkedList<>();
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addFirst(1);
        //linkedList.removeLast();
        linkedList.removeFirstOccurrence(3);
        linkedList.removeLastOccurrence(2);
        System.out.println(linkedList);


    }



}
