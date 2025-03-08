import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


//Comparable is used to define the natural ordering of objects of a class
//Comparable interface and overrides its compareTo() method to specify how objects of that class should be compared.
class Human implements Comparable<Human>{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Human o) {
        return   this.getName().compareTo(o.getName()); //this.getName().length() - o.getName().length() ;
    }
}

public class ComparableVsComparator {

    public static void main(String[] args) {

        ArrayList<Human> humans= new ArrayList<>();
        humans.add(new Human("Alice", 23));
        humans.add(new Human("Bob", 22));
        humans.add(new Human("driz", 23));
        System.out.println(humans);

        Collections.sort(humans);
        System.out.println(humans);

        // comparator
        Comparator<Human> comparator = Comparator.comparing(Human:: getAge).thenComparing(Human::getName); //thenComparing(Human::getName) ensures that if two humans have the same age, they are further sorted by their name in lexicographical (alphabetical) order.
        humans.sort(comparator);  // internal algorithm timsort (read later gpt)
        System.out.println(humans);


    }
}
