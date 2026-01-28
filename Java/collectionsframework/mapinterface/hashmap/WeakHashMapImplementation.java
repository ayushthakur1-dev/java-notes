
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapImplementation {
    public static void main(String[] args) {
        Map<Integer, Image> imageCache = new WeakHashMap<>();

        Image firstImage = new Image(0, 0, 0);

        imageCache.put(1000, firstImage); // can be removed from map but object will remain
        imageCache.put(100, new Image(1, 0, 0)); // cannot be removed because JVM stores -128 to 127 into a pool
        imageCache.put(200, new Image(2, 0, 0)); // can be removed
        imageCache.put(300, new Image(3, 0, 0)); // can be removed
        imageCache.put(400, new Image(4, 0, 0)); // can be removed
        imageCache.put(500, new Image(5, 0, 0)); // can be removed

        System.out.println("Image cache before: ");
        System.out.println(imageCache);

        // this will not guarantee the gc but will work as an suggestion for JVM 
        // final call will be of JVM 
        System.gc(); 
        applicationSimulation();

        System.out.println("Image cache after: ");
        System.out.println(imageCache);

        // weak hash map will only clear the keys not the values so if there is no strong reference 
        // on those objects then only they will be picked by JVM in garbage collection

        // like here we can see that firstImage was having a strong reference so we can still access it
        System.out.println(firstImage + "\n");

        // similary if keys were of String type then they will be not be removed as they are stored in 
        // string pool that is be maintained by the JVM until the very end of application execution 
        // hence they will be treated as strong reference 
        
        // but if they are made using new String("value"), they can be removed by weak reference logic
        Map<String, Image> imageCache2 = new WeakHashMap<>();
        String str = new String("Three");
        imageCache2.put("One", new Image(1, 2, 3)); // stored in string pool will not be removed
        imageCache2.put(new String("Two"), new Image(2, 2, 3)); // can be removed
        imageCache2.put(str, new Image(3, 2, 3)); // have a strong reference will not be removed
        
        System.out.println("Image Cache2 Before:");
        System.out.println(imageCache2);

        System.gc();
        applicationSimulation();

        System.out.println("Image Cache2 After:");
        System.out.println(imageCache2);

        // if you explicitly want to declare an reference as weak reference you can do it 
        WeakReference<Image> imageWeakReference = new WeakReference<>(new Image(6, 0, 0));
    }

    private static void applicationSimulation() {
        try {
            Thread.currentThread().sleep(10000);
        } catch (Exception ex) {
        }
    }
}

class Image {
    private int id;
    private int height;
    private int width;

    Image(int id, int height, int width) {
        this.id = id;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Image " + Integer.toString(this.id);
    }
}