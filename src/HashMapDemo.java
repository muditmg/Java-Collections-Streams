import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
    //Capacity of hashmap is always a power of 2 (eg. 16(default), 32, ...)

    //bucketIndex = hashCode(key) & (capacity - 1); ( & - AND operation)
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>(16,0.75f);
        map.put(1, "Mudit");
        map.put(2, "drake");
        map.put(3, "drizzy");
        map.put(4,"collision");
        System.out.println(map); // the order is not maintained.
        System.out.println( map.containsKey(2));
        System.out.println(map.get(1).hashCode());

        // iterate over map  : using keyset
        Set<Integer>  keys = map.keySet();  // 'Set' because key will be always unique
        for(int i : keys){    //  alternate: for(int i : map.keySet())
            System.out.println(map.get(i));
        }
        // using entryset
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for(Map.Entry<Integer, String> entry : entries){
            System.out.println(entry.getKey() + ":" + entry.getValue() +":" + entry.getClass());
        }
    }
}
