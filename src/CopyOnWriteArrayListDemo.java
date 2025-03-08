import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        List<String> copyOnWriteArrayList= new CopyOnWriteArrayList<>();
        // "Copy on write" means whenever a write operation
        // like adding or removing elements
        // instead of directly modifying the existing list
        //a new copy of list is created anf the modification is applied to that copy
        // this ensures that other threads reading the list while its being modified are unaffected.

        // Read operations: fast and direct , since they happen on a stable list without interference of modification.
        // Write operation: a new copy of the list is created for every modification.
        // use when there are more read intensive operations and less write

        copyOnWriteArrayList.add("Mudit");
        copyOnWriteArrayList.add("Drizzy");
        copyOnWriteArrayList.add("Kendrick");
        System.out.println("Initial list: " + copyOnWriteArrayList);

        for(String item : copyOnWriteArrayList){
            System.out.println(item);
            // modify list while reading
            if(item.equals("Drizzy")){
                copyOnWriteArrayList.add("Drake");
                System.out.println("Item added while reading");
            }
        System.out.println("updated item list: " + copyOnWriteArrayList);
        }

        // another example

        List<String>  sharedList = new CopyOnWriteArrayList<>();
        sharedList.add("item1");
        sharedList.add("item2");
        sharedList.add("item3");
        Thread readerThread = new Thread(()->{
            try{
                while(true){
                    for(String item : sharedList){
                        System.out.println("Reading item: " + item);
                        Thread.sleep(100); // small delay to simulate work
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread writerThread = new Thread(()->{
            try{
                Thread.sleep(500); // delay to allow reading to start first.
                sharedList.add("item4");
                System.out.println("item4 added to the list");

                Thread.sleep(500); // delay to allow reading to start first.
                sharedList.remove("item1");
                System.out.println("item1 removed from the list");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        readerThread.start();
        writerThread.start();
    }
}
