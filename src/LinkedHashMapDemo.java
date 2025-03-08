import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapDemo {
    //Maintains insertion order by using a doubly linked list in addition to the hash table.
    //The linked list tracks the order in which entries were added, and iteration follows that order.
    //The linked list is used to track the insertion order of entries.
    // Each entry in the map has two additional pointers (previous and next) that link it to the previous and next entries in the list
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> linkedHashMap= new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("Mudit", 10);
        linkedHashMap.put("drizzy", 45);
        linkedHashMap.put("drake", 23);
        linkedHashMap.put("ovo", 78);

        linkedHashMap.put("ovo", 47);
        linkedHashMap.put("drake", 78);
        linkedHashMap.get("Mudit");
        for(Map.Entry<String, Integer> entry: linkedHashMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    // AccessOrder: when accessOrder is set to true - Every time a key is accessed (via get or put),
    // that entry is moved to the end of the map to signify it is the most recently accessed.
    //Access order is widely used in LRU Cache implementations.

}
