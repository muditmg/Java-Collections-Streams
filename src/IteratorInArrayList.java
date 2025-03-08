//🔹 List<E> extends Collection<E>, and
//🔹 Collection<E> extends Iterable<E>.
//Since ArrayList implements Iterable, it provides an iterator(), allowing it to be used in a for-each loop.


import java.util.ArrayList;
import java.util.Iterator;

public class IteratorInArrayList {
    //In Java, when you iterate over an ArrayList using a for-each loop or a simple iterator,
    // modifying the list (adding or removing elements) while iterating results in a ConcurrentModificationException.
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        Iterator<String> iterator = arrayList.iterator();
        while(iterator.hasNext()){
            String value = iterator.next();
            if (value.equals("B")) {
                  iterator.remove();  // use this instead, ✅ Yes (removal only) but not thread safe  or else just use copyonwrite.
                //The Iterator provides a remove() method, which safely removes elements while iterating.

//                arrayList.remove(value); // ❌ Expected to throw ConcurrentModificationException , in java 21 its does not throw error
                // Some IDEs may optimize or defer modifications, making it appear as if there's no exception.
            }
        }
        System.out.println(arrayList);
    }

}
