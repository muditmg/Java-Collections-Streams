import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class IndentityHashMapDemo {
    //IdentityHashMap vs HashMap
    public static void main(String[] args) {
        String key1 = new String("Key");
        String key2 = new String("Key");
        IdentityHashMap<String, String> identityHashMap= new IdentityHashMap<>();

        //Even though key1 and key2 have the same content ("key"), they are stored as separate entries because they are different objects in memory.
        // The IdentityHashMap compares the references of the keys using ==, not their contents.
        //As a result, the two different objects (key1 and key2) are treated as different keys, and each has a different value in the map.
        identityHashMap.put(key1, "value1");
        identityHashMap.put(key2, "value1");

        System.out.println(identityHashMap);

        //HashMap uses the equals() method to compare keys, so even though key1 and key2 are different objects in memory, they are considered equal because their content is the same.
        // Hence, the second insertion overrides the first, and only one entry is stored in the HashMap.
        HashMap<String, String> hashMap= new HashMap<>();
        hashMap.put(key1, "value");
        hashMap.put(key2, "value2");

        System.out.println(hashMap);

    }
}
