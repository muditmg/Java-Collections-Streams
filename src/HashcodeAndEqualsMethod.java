import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class HashcodeAndEqualsMethod {
    // problem with hashmap occurs when the pair includes object. Object returns a hash code derived from the memory address of the object
    //As a result, two different instances of a class will almost always produce different hash codes, even if the instances have identical field values.
    // If a class does not override the hashCode() method, the default implementation from the Object class is used.
    //that's we will manage (below case)
    public static void main(String[] args) {
        HashMap<Person,String> map = new HashMap<>();
        Person p1= new Person("Mudit", 1);
        Person p2= new Person("drizzy", 2);
        Person p3= new Person("Mudit", 1);


        map.put(p1, "developer");
        map.put(p2, "manager");
        map.put(p3, "HR");

        System.out.println(p1);
        System.out.println(map);
        System.out.println(map.size());
        map.forEach((key,value) -> System.out.println(key + " " + value));




    }
}
class Person{
    private String name;

    public Person (String name, int id){
        this.name=name;
        this.id=id;
    }
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Person() {
        super();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){return true;} // this refers to p3.
                                       // obj refers to p1 (the existing key in the bucket).
                                       //p3 == p1 evaluates to false because they are different objects in memory.

        if(obj == null || getClass() != obj.getClass() ){return false;} // obj is not null, so the first condition is false.
                                                                        // Both p3 and p1 belong to the same class (Person), so the second condition is also false.
        Person person = (Person) obj; // obj (which is p1) is cast to Person so its fields can be compared with p3.
        return id== person.id && Objects.equals(name, person.name); //id == person.id: p3.id is 1 and p1.id is 1, so this condition is true.
                                                                    // p3.name is "Mudit" and p1.name is "Mudit".

    }


    @Override
    public String toString() {
        return "id: " + id + " name: " + name;
    }
}