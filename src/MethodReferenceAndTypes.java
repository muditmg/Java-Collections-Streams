
//Method references are a shorter way of writing lambda expressions.
// They allow us to refer to an existing method by name instead of using a lambda expression.


import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
class Employees{

    public Employees(){
        System.out.println("Employee created!");
    }
}
public class MethodReferenceAndTypes {
   //static method
    public static Integer square(Integer num){    //doubt: Functional Interfaces Require Wrapper Types
        return num*num;                                        // Functional interfaces like Function<T, R> work with objects, not primitives.
    }

    //Instance methods
    public void showMsg(){
        System.out.println("Hey there!");
    }

    public String showMsg2(){
       return ("Hey there!");
    }

    public static void main(String[] args) {
        //Reference to a Static Method
        //Function<Integer,Integer> res = x -> MethodReferenceAndTypes.square(x);  --> lamda exp
        Function<Integer,Integer> res = MethodReferenceAndTypes::square;
        System.out.println(res.apply(12));


        //Reference to an Instance Method (of a specific object)
        //Supplier<String>  msg = () -> {MethodReferenceAndTypes.showMsg}; --> showMsg is an instance method, not a static method, so it cannot be called directly with ClassName.methodName.
        MethodReferenceAndTypes obj = new MethodReferenceAndTypes();
        //using lambda:
        Supplier<String> res2 = () -> {obj.showMsg(); return "";};
        System.out.println(res2.get());
        //stream:
        Supplier<String> res4 = obj::showMsg2;
        System.out.println(res4.get());
        Runnable res3 = obj::showMsg; //Since showMessage() returns void, it matches the Runnable functional interface, which also returns void:
        res3.run();


        //Reference to an Instance Method (of an arbitrary object of a class)
        Function<String, String> fn = str-> str.toUpperCase();  // lamda
        System.out.println(fn.apply("drizzy"));

        Function<String, String> fn1= String::toUpperCase;
        System.out.println(fn1.apply("mudit"));


        //Reference to a Constructor
        Supplier<Employees> ans = () -> new Employees();  // lamda
        ans.get();
        Supplier<Employees> ans1 = Employees::new;
        ans1.get();


        //Sorting a List with Method Reference
        List<Integer> immutableList = List.of(3,2,6,5,44,23,66);   //List.of(...) creates an immutable list. Collections.sort(list, comparator) sorts the list in place (modifies the original list). , so here sort() will throw exception.
        //Arrays.asList, same with this: returns a fixed-size list backed by an array. The structure cannot be modified, so sorting fails.
        List<Integer> mutableList = new ArrayList<>(List.of(3,2,6,5,44,23,66));
        //using Lamda
        Collections.sort(mutableList, (a,b) -> a.compareTo(b) );
        System.out.println(mutableList);

        //using stream
        Collections.sort(mutableList, Integer::compareTo);
        System.out.println(mutableList);
    }
}
