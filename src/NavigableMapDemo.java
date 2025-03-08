import java.util.NavigableMap;
import java.util.TreeMap;

//A NavigableMap is an extension of SortedMap with additional methods for navigating the map.
// It allows retrieving entries closest to a given key,either greater or smaller, and provides more flexible key-based operations.


public class NavigableMapDemo {
    public static void main(String[] args) {
        NavigableMap<Integer, String> navigableMap = new TreeMap<>();
        navigableMap.put(10, "Ten");
        navigableMap.put(20, "Twenty");
        navigableMap.put(30, "Thirty");
        navigableMap.put(40, "Forty");

        System.out.println("NavigableMap: " + navigableMap);

        System.out.println("Ceiling Entry (>=25): " + navigableMap.ceilingEntry(25));
        System.out.println("Floor Entry (<=25): " + navigableMap.floorEntry(25));
        System.out.println("Higher Entry (>20): " + navigableMap.higherEntry(20));
        System.out.println("Lower Entry (<20): " + navigableMap.lowerEntry(20));
        System.out.println("Descending Map: " + navigableMap.descendingMap());
    }
}


// navigablemap --> sortedmap --> map .