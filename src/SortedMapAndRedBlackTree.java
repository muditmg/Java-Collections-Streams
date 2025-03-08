import java.util.*;

// A SortedMap is a subinterface of Map in Java that maintains its key-value pairs in ascending order of keys.
// It is part of the java.util package and is commonly implemented using the TreeMap class.
//The compareTo method exists in the Comparable interface, implemented by key classes like String, Integer, or user-defined types.
//TreeMap relies on the key's compareTo method for natural sorting if no Comparator is provided.
//If keys don’t implement Comparable and no custom Comparator is supplied,
// it results in a runtime exception.If keys don’t implement Comparable and no custom Comparator is supplied, it results in a runtime exception.
//For natural sorting in a SortedMap (e.g., TreeMap), the keys stored in the map must implement the Comparable interface.
// The TreeMap itself does not implement Comparable;
// instead, it relies on the Comparable implementation of the key type to determine the natural ordering.
//For example, if the key is of type String, it uses String.compareTo.
//Similarly, for Integer, it uses Integer.compareTo.

//A SortedMap (e.g., TreeMap) uses a Red-Black Tree to store and manage its elements.
//The balancing of the tree during operations like insertion and deletion ensures the map's performance is consistent at O(nlogN) .




// INTERNAL WORKING:
//The internal working of a SortedMap (typically implemented by TreeMap) relies on a self-balancing binary search tree (Red-Black Tree) to store its entries.
// This ensures that the elements are always sorted in natural order (or a custom order if a comparator is provided).
//When you insert a key-value pair, TreeMap uses the compareTo method (for natural ordering) to find the correct position for the key in the Red-Black Tree.
public class SortedMapAndRedBlackTree {
    public static void main(String[] args) {
        SortedMap<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.put(3, "Three");
        sortedMap.put(1, "One");
        sortedMap.put(2, "Two");
        sortedMap.put(5, "Five");

        System.out.println("SortedMap: " + sortedMap); // Keys are sorted
        System.out.println("First Key: " + sortedMap.firstKey());
        System.out.println("Last Key: " + sortedMap.lastKey());
        System.out.println("HeadMap (keys < 3): " + sortedMap.headMap(3));
        System.out.println("TailMap (keys >= 3): " + sortedMap.tailMap(3));
        System.out.println("SubMap (2 to 5): " + sortedMap.subMap(2, 5));//Here, TreeMap uses Integer.compareTo for natural ordering.
        //confusion clarity: Integer.compare is a static method in the Integer class. It provides a utility to compare two Integer values.
        //It's commonly used in Comparable implementations to simplify comparisons of integer fields.
        //The compare method in the Comparator interface is entirely different.
        // It is used to compare two objects of a custom type, not primitive values like int.


        for(Map.Entry<Integer, String> entry: sortedMap.entrySet()){
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        //Use Case of SortedMap:
        //Maintaining a sorted leaderboard in a game.
        //Storing data like timestamps or sorted identifiers for easy range queries.




    }
}
