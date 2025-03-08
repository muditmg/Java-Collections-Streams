import java.util.Arrays;

public class LinkedList {
    public static void main(String[] args) {
        java.util.LinkedList<Integer> linkedList= new java.util.LinkedList<>();
        linkedList.add(33);
        linkedList.add(56);
        linkedList.add(21);
       System.out.println(linkedList.get(2));
       System.out.println(linkedList);
       linkedList.removeIf(x->x % 11 == 0);
        System.out.println(linkedList);

        java.util.LinkedList<String> animal= new java.util.LinkedList<>(Arrays.asList("dog","cat","rabbit"));
        animal.removeIf(x->x.length()>3);
        System.out.println(animal);
        animal.removeAll(Arrays.asList("cat"));
        System.out.println(animal);




    }
}

