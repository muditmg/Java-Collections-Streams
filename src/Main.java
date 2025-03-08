import java.util.*;

class StringLengthComparator implements Comparator<String>{

    @Override
    public int compare(String s1, String s2) {
        return s2.length() - s1.length() ;
    }
}
//example of comparator
class Student{
    private String name;
    private double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa= gpa;
    }

    public String getName() {
        return name;
    }
    public double getGpa() {
        return gpa;
    }
}

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        ArrayList<Integer> arrayList= new ArrayList<>(10);// we can give any initial capacity if it exceeds then it resizes by 1.5 times.
        arrayList.add(1); //0
        arrayList.add(4); //1
        arrayList.add(6); //2

        arrayList.remove(1);
        arrayList.remove(Integer.valueOf(6));    //to remove element by value, to ensure that we are calling an object no the index we need to convert it into wrapper class.(Integer)
        arrayList.add(1,9);
        //System.out.println(arrayList.get(2));
        //System.out.println(arrayList.size());

//        for(int i=0; i<arrayList.size(); i++){
//            System.out.println(arrayList.get(i));
//        }

        for( int x: arrayList){
            System.out.println(x);
        }

        System.out.println(arrayList.contains(34));

        List<Integer> list1= List.of(22,33,23,55,77);

        arrayList.addAll(list1);
        System.out.println(arrayList);

        Collections.swap(arrayList, 1,3 );
        System.out.println(arrayList);

        //Collections.sort(arrayList);
        arrayList.sort(null);   //paramerter: comparator(here, null)
        System.out.println(arrayList);

        List<String> words1 = Arrays.asList("A", "BB", "DDD","CCCCC");
        words1.sort(null);  // will sort in alphabetical order
        System.out.println(words1);

        //if we want List to be length wise sorted we need to implement class which implements comparator and uses compare method which compares two objects
        List<String> words2 = Arrays.asList("AAAAAA", "BB", "DDD","CCCCC");
        words2.sort(new StringLengthComparator());
        System.out.println(words2);

        // we can do the above directly by using lamda expression.
        List<String> words3 = Arrays.asList("AAAAAA", "BB", "DDD","CCCCC");
        words3.sort((a,b) -> b.length()-a.length());
        System.out.println(words3);


        //one more example of comparator (class student)
        List<Student> students= new ArrayList<>();
        students.add(new Student("Mudit", 9));
        students.add(new Student("Mustard", 7.5));
        students.add(new Student("Drizzy", 7.5));
        students.add(new Student("Kendrick", 8));
        //students.sort((a,b) -> (int) (b.getGpa()- a.getGpa())); this won't work as gpa is in double
        students.sort((a,b) -> {
            if(b.getGpa()-a.getGpa()>0){
                return 1;
            }
            else if(b.getGpa()-a.getGpa()<0){
                return -1;
            }
            else{
                return 0;
            }
        });
        for(Student s: students){
            System.out.println(s.getName() + ": " + s.getGpa());
        }

     // now this all we can do with predefined methods
        Comparator<Student> comparator = Comparator.comparing(Student::getGpa).reversed().thenComparing(Student::getName); // we created object of the comparator, (::)- double colon operator -method reference - means what method we want to call for students
        students.sort(comparator);
        for(Student s: students){
            System.out.println(s.getName() + ": " + s.getGpa());
        }
    }


}