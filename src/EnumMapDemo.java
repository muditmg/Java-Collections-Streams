import java.util.EnumMap;

//EnumMap is a specialized Map implementation in Java, designed specifically for use with enum types as keys.
//Only enum constants can be used as keys.
//Faster than HashMap for enum keys because it is optimized for enums.
//no hashing required since key is directly mapped to enum constants.
//  EnumMap Use an Array: Arrays provide O(1) lookup time for retrieving values, making EnumMap much faster than HashMap,
//  which typically uses hashing for lookups (which involves calculating a hash value and searching through buckets).
enum Day{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumMapDemo {
    public static void main(String[] args) {
        EnumMap<Day, String> schedule= new EnumMap<>(Day.class);
        schedule.put(Day.MONDAY, "9 AM - 5 PM");
        schedule.put(Day.TUESDAY, "9 AM - 5 PM");
        schedule.put(Day.WEDNESDAY, "9 AM - 5 PM");
        schedule.put(Day.THURSDAY, "9 AM - 5 PM");
        schedule.put(Day.FRIDAY, "9 AM - 3 PM");
        schedule.put(Day.SATURDAY, "Closed");
        schedule.put(Day.SUNDAY, "Closed");
        System.out.println("Monday Schedule: " + schedule.get(Day.MONDAY));
        System.out.println("Sunday Schedule: " + schedule.get(Day.SUNDAY));

        // Iterating over the EnumMap
        for(Day day: schedule.keySet()){
            System.out.println(day + ": " + schedule.get(day));
        }
// EnumMap is much faster than general-purpose Map implementations (like HashMap) when using enums as keys.
// This is because it internally uses an array to store values, which allows for constant-time access (O(1)) instead of hash-based lookups.
        //Since EnumMap uses an array internally, it is more memory-efficient than using a HashMap,
        // which needs to manage additional overhead for hashing and key-value pairs.
    }
}
