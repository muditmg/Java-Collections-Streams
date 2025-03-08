import java.util.HashSet;
import java.util.Set;


//A HashSet is a part of the Java Collections Framework (java.util package) that represents a collection of unique elements.
// It is backed by an internal HashMap and provides constant-time performance (O(1)) for add, remove, and contains operations.

public class HashsetDemo {
    public static void main(String[] args) {
        Set<String> set= new HashSet<>();

        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple");  // duplicate will not be added

        System.out.println(set);
        System.out.println(set.size());

        for(String s : set){
            System.out.println(s);
        }

        //How HashSet Works Internally?
        //Uses an Internal HashMap
        //When you add an element to a HashSet, it actually stores it in an internal HashMap with a constant dummy value (like Boolean.TRUE).
        //Each element of the HashSet is stored as a key in the HashMap, ensuring uniqueness.

    }
}
