import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.function.Consumer;

@FunctionalInterface    //A Functional Interface is an interface that has only one abstract method.
interface Sum{
    int add(int a, int b);
}
public class StreamBasics {
    //🔹 What is a Lambda Expression?
    //A Lambda Expression is a shorter way of writing an anonymous function (i.e., a function without a name).
    // It is used in functional programming and Java Streams API.
    //Lambdas are useful for writing concise code in Java Streams & Functional Interfaces
    public static void main(String[] args) {
        Sum sum = (a,b) -> a+b;
        System.out.println(sum.add(57,89));

    //Java has several built-in functional interfaces in java.util.function package. The most commonly used ones are:
    //Predicate<T> – Returns a boolean (used for filtering)
    //Consumer<T> – Takes an input, performs an action, but returns nothing
    //Function<T, R> – Takes an input and returns an output
    //Supplier<T> – Returns a value but takes no input

    //Predicate:
        //@FunctionalInterface
        //public interface Predicate<T> {
        //    boolean test(T t);
        //}
        Predicate<Integer> isEven = num -> num%2==0;
        System.out.println(isEven.test(7));
        System.out.println(isEven.test(6));

        BiPredicate<Integer,String> isEligible = (age,country)-> age>=18 && country.equals("India");
        System.out.println(isEligible.test(19,"India")); // true

        // Consumer
        //@FunctionalInterface
        //public interface Consumer<T> {
        //    void accept(T t);
        //}
        //Consumer is commonly used with forEach() in Streams.
        List<String> names = List.of("Alice", "Bob", "Charlie");
        Consumer<String> printName = name -> System.out.println("Hello " + name);
        names.forEach(printName);

        BiConsumer<String,Integer> printDetails = (name,age)-> System.out.println(name + " is " + age + " years old");
        printDetails.accept("Mudit",26);

        //Function
        //@FunctionalInterface
        //public interface Function<T, R> {
        //    R apply(T t);
        //}     inputType returnType
        Function<String, Integer>   stringLen= str -> str.length();
        System.out.println(stringLen.apply("Drizzy"));

        BiFunction<String,String,String> fullName= (a,b) -> (a + " " + b);
        System.out.println(fullName.apply("Mudit","Gupta"));

        // Supplier
        //@FunctionalInterface
        //public interface Supplier<T> {
        //    T get();
        //}
        //✅ Used for generating or fetching values.
        Supplier<Integer> randomSup = () -> new Random().nextInt(100);
        System.out.println(randomSup.get());

        // UnaryOperator<T> (Single Input, Returns Same Type Output)
        //Method: T apply(T t) (Same as Function<T, T>)
        UnaryOperator<Integer> mult = num -> num*num;
        System.out.println(mult.apply(33));

        //BinaryOperator<T> (Two Inputs of Same Type, Returns Same Type Output)
        //Method: T apply(T t1, T t2) (Same as BiFunction<T, T, T>)

        BinaryOperator<Integer> max = (a,b) -> a>b? a:b;
        System.out.println(max.apply(7,69));
    }
}
