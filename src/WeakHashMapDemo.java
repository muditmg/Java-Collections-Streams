import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        WeakHashMap<String, Image> imageCache = new WeakHashMap<String, Image>();
        imageCache.put("img1", new Image("image1")); // A WeakHashMap is used, but its weak key handling only comes into play when the keys are no longer strongly referenced.
        imageCache.put("img2", new Image("image2"));  // In this case, the keys ("img1" and "img2") are String literals, which are always strongly referenced because they are stored in the Java string pool.
                                                            // The Image object is stored as a value in the WeakHashMap. Values in a WeakHashMap are always strongly referenced
        //meaning that they are not eligible for garbage collection as long as the map itself is reachable and the value has not been explicitly removed from the map
        System.out.println(imageCache);

        ///NOW to make String weak referenced:
        //you must ensure that the key is not a string literal
        //String literals are stored in the Java string pool, which keeps them strongly referenced and prevents them from being garbage collected.
        //which keeps them strongly referenced and prevents them from being garbage collected.
        //Instead, you can create a String object explicitly using the new keyword. Unlike string literals,
        // String objects created with new are not interned in the string pool and can be garbage collected when no strong references exist.

        String img3 = new String("img3");
        String img4= new String("img4");
        imageCache.put(img3, new Image("image3"));
        imageCache.put(img4, new Image("image4"));

        //After nullifying the reference to img3,
        //The key-value pair (img3, "image3") becomes eligible for garbage collection because the key is no longer strongly referenced.
        img3= null;
        System.gc();
        try{
            System.out.println("wait for 10 seconds");
            Thread.sleep((10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(imageCache);





    }
}
class Image{
    private String name;

    @Override
    public String toString() {    // if not overridden it will print the hashcode of the object because of the default implementation.
        return name;
    }

    public Image(String name) {
        this.name = name;
    }
}


