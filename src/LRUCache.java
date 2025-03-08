import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private int capacity;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    //Constructor
    public LRUCache(int capacity){
        super(capacity, 0.75f, true);
        this.capacity=capacity;

    }

    //Access order is widely used in LRU Cache implementations.
    //An LRU Cache evicts the least recently accessed entries when it exceeds a specified capacity.
    //Using a LinkedHashMap with accessOrder = true simplifies the implementation.The least recently accessed entry is always at the head of the map.
    public static void main(String[] args) {
        LRUCache<String,Integer> lruCache= new LRUCache<>(3);
        lruCache.put("Mudit", 33);
        lruCache.put("drake", 67);
        lruCache.put("drizzy", 88);
        lruCache.put("kendrick", 44);

        lruCache.get("drizzy");

        System.out.println(lruCache);
    }
}
