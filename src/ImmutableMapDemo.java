import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//An ImmutableMap is a special type of map where the entries (key-value pairs) cannot be modified once the map is created.
// This provides a guarantee that the map will not change during the program's execution,
// offering safety in concurrent and multi-threaded environments,
// as well as reducing the risk of accidental data modification.

//Thread-Safety: Immutable objects can be safely shared between multiple threads without synchronization.
//Performance: Immutable objects can be optimized by the JVM or libraries, which can lead to better performance for certain use cases (e.g., caching).

//It is thread-safe, efficient, and prevents accidental modifications,
// making it perfect for use cases like configuration settings, cached data, and constants.

public class ImmutableMapDemo {
    public static void main(String[] args) {
        //to convert hashmap into immutable map:
        //  Map<Integer,String> map= new HashMap<>();
        //        map.put(1,"Apple");
        //        map.put(2,"Gummy");
        //
        //        Map<Integer, String> integerStringMap = Collections.unmodifiableMap(map);
        //        -->Java 8+ (Collections.unmodifiableMap())
        //        -->Java 9+ (Map.copyOf())
        //        -->Google Guava (ImmutableMap.copyOf())
        // Limitation:
        //Changes to the original HashMap will reflect in the immutable map!
        //If you need a truly immutable copy, consider Map.copyOf() or ImmutableMap.copyOf() instead.

        // above can be replaced with below:
        //Map<Integer, String> integerStringMap = Map.of(1, "Apple", 2, "Gummy"); -->  allows only 10 entries.

        Map<Integer, String> integerStringMap = Map.ofEntries(Map.entry(1, "Apple"),Map.entry( 2, "Gummy"));

        for(int key: integerStringMap.keySet()){
            System.out.println("key: " + key + " value: " + integerStringMap.get(key));
        }
//        integerStringMap.put(3,"orange");   --> if I try to modify it will throw exception : UnsupportedOperationException
    }
}
