import java.util.concurrent.PriorityBlockingQueue;
//A PriorityBlockingQueue orders elements by priority, not FIFO.
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> q = new PriorityBlockingQueue<>();
        q.add(30);
        q.add(10);
        q.add(20);

        while(!q.isEmpty()){
            System.out.println(q);
            System.out.println("Removed element " + q.poll());
            System.out.println("Remaining element " + q);

        }
    }


}
