import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//A Stream in Java is a sequence of elements that allows functional-style operations like filtering, mapping, sorting, and reducing.
public class StreamDive {
    public static void main(String[] args) {
        //From a List or Set
        List<Integer> values = List.of(3,5,4,22,7);
        Stream<Integer> stream = values.stream();
        stream.forEach(x-> System.out.println(x));

        //From an Array
        Integer[] array = {3,5,67,4};
        Stream<Integer> st = Arrays.stream(array);
        st.forEach(System.out::println);

        //Using Stream.of()
        Stream<Integer> sr = Stream.of(1, 2, 3, 4, 5);

        //🔹 Intermediate vs. Terminal Operations
        //Operation Type	                Example Methods                              	Description
        //Intermediate (Transforms Data)	filter(), map(), distinct(), sorted(), limit()	Returns a new stream, does not execute immediately (lazy).
        //Terminal (Consumes Data)	        forEach(), collect(), count(), reduce()	        Executes the pipeline and produces a result (list, sum, etc.).
        //Streams allow method chaining, meaning you can apply multiple operations in sequence.


        //filter(Predicate<T>) - Filtering Elements
        //filter() keeps only the elements that match a condition (Predicate).
        List<Integer> num = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> even = num.stream().filter(x->x%2==0).filter(x->x>2).collect(Collectors.toList());
        System.out.println(even);


        //map(Function<T, R>) - Transforming Elements
        //map() modifies each element of the stream (e.g., converts lowercase to uppercase, squares numbers)
        List<Integer> sqr = num.stream().filter(x->x%3==0).map(x->x*x).collect(Collectors.toList());
        System.out.println(sqr);


        //sorted() - Sorting Elements
        //sorted() sorts elements in natural order (Comparable like String and Integer).
        List<Integer> numbers = List.of(44,56,23,9,4,9,1);
        List<Integer> sorted = numbers.stream()
                .filter(x->(x&1)==1)
                .map(x->x*x).sorted((a,b)->b.compareTo(a))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(sorted);

        //Limit(n) – Get First N Elements
        //How to get the top N elements from a list?"
        List<Integer> firstTwo = numbers.stream().limit(2).collect(Collectors.toList());
        System.out.println(firstTwo);

        //skip(n) – Skip First N Elements
        //How to skip first N elements and process the rest?"
        List<Integer> skip = numbers.stream().skip(2).collect(Collectors.toList());
        System.out.println(skip);

        //reduce() – Aggregation (Sum, Max, Min)
        //How to compute sum, max, or min using Streams?"
        //Optional<T> reduce(BinaryOperator<T> accumulator)
        //T reduce(T identity, BinaryOperator<T> accumulator)
        //identity	-> The initial/default value (e.g., 0 for sum, 1 for product)
        //accumulator -> A function to combine elements (e.g., (a, b) -> a + b)
        //Optional<T> -> 	If no elements exist, returns Optional.empty()

       int sum = numbers.stream().reduce(0,Integer::sum);
        System.out.println(sum);

        //groupingBy() – Grouping Elements
        //How to group elements in a list based on a property?
        List<String> words = List.of("apple", "banana", "cherry", "apricot", "blueberry");
        Map<Integer,List<String>> map = words.stream().collect(Collectors.groupingBy(x->x.length()));
        System.out.println(map);


        //counting occurence of the characters:
        String input = "java is amazing";
        char target = 'a';
        int count = input
                .chars()
                .mapToObj(c->(char) c)
                .reduce(0, (add,x)->x == target ? add+1 : add,Integer::sum);

        //without using reduce(
        long cn = input.chars().filter(x->x==target).count();
        System.out.println(cn);
        System.out.println(count);

        //What Are Parallel Streams?
        //A parallel stream in Java allows multiple threads to process elements in parallel to improve performance.
        List<Integer> n = List.of(1, 2, 3, 4, 5);

        // In stream(), all elements run on main thread.
        //✅ In parallelStream(), elements run on multiple threads (ForkJoinPool-worker-*).

        // ✅ Sequential Stream (Single Thread)
        n.stream()
                .forEach(x -> System.out.println("Sequential: " + x + " " + Thread.currentThread().getName()));
               //forEachOrdered() -->Preserves order in parallel stream
               //forEach() -->Unordered (parallel may shuffle output)
        // ✅ Parallel Stream (Multiple Threads)
        n.parallelStream()
                .forEachOrdered(x -> System.out.println("Parallel: " + x + " " + Thread.currentThread().getName()));



        //     //When using parallelStream(), Java splits the stream into multiple substreams,
        //    // processes them separately, and then combines results.
        //    //This is why a neutral identity value is required, ensuring correctness.
        List<Integer> nm = List.of(1, 2, 3, 4, 5);

        int s = nm.parallelStream()
                .reduce(0, Integer::sum); // ✅ Parallel safe
        //✅ Why We Need an Identity (0)?
        //Without identity, parallel streams won’t know how to merge results from different threads.

        System.out.println("Sum: " + s); // Output: 15


        //Cumulative Sum Using Parallel Stream
        //❌ Issue with Parallel Streams:
        //Parallel streams process elements in random order, so it doesn’t work correctly for cumulative sums.
        //We must use AtomicInteger for thread safety.
        AtomicInteger add = new AtomicInteger(0);
        List<Integer> cummulativeSum = numbers.parallelStream().map(x->add.addAndGet(x)).collect(Collectors.toList());
        System.out.println(cummulativeSum);


        //Factorial Using Parallel Streams
        int k =5;
        AtomicInteger start = new AtomicInteger(1);
        IntStream.rangeClosed(1,k).parallel().forEach(x->start.updateAndGet(y->y*x));
        System.out.println(start);

        // Fibonacci
        int a = 10;

        AtomicInteger prev = new AtomicInteger(0);
        AtomicInteger curr = new AtomicInteger(1);

        List<Integer> fibonacciSeries = IntStream.range(0, a)
                .parallel()
                .map(i -> (i <= 1) ? i : prev.getAndSet(curr.get()) + curr.getAndAccumulate(prev.get(), Integer::sum))
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Fibonacci Series: " + fibonacciSeries);


        // FlatMap
        //flatMap() flattens a structure: It converts multiple streams into a single stream.
        //It is used when dealing with lists of lists, arrays of lists, or nested collections.
        // Method	     What It Does?	                                   Example Input       Example Output
        // map()	     Transforms each element but keeps structure	   [[1, 2], [3, 4]]	   [[1, 2], [3, 4]]
        // flatMap()	 Flattens nested structure into a single stream	   [[1, 2], [3, 4]]	   [1, 2, 3, 4]

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        // Using flatMap to flatten the list of lists
        List<Integer> flatList = listOfLists.stream()
                .flatMap(List::stream) // ✅ Flattens each inner list into a single stream
                .collect(Collectors.toList());

        System.out.println(flatList);
    }


    }



