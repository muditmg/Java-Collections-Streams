import java.util.ArrayList;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>(3, 4); //we can manually set the increment by value of the capacity as second parameter
        System.out.println(vector.capacity());
        vector.add(33);
        vector.add(67);
        vector.add(32);
        vector.add(67);
        System.out.println(vector.capacity());
        System.out.println(vector.isEmpty());
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i));
        }

        //ArrayList vs Vector

        //ArrayList<Integer> list = new ArrayList<>();  // final size of the list should be 2000 but its variable coz ArrayList is not synchronized and not thread safe.
                                                     // in this case both the threads are accessing the list at the same time so whatever thread/instant clashes then the value counts only once.

        Vector<Integer> list = new Vector<>(); // Final size of the list will be 2000 when using Vector coz its thread safe
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                list.add(i);
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
        System.out.println(list.size());
    }
}
