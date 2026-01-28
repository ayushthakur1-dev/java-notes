
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueImplementation {
    // thread-safe
    // does not use blocking mechanism
    // uses CAS -> Compare And Swap 
    // similar to ConcurrentLinkedHashMap
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        // similar to ConcurrentLinkedQueue, but now it is double ended queue   
        ConcurrentLinkedDeque<Integer> deque = new ConcurrentLinkedDeque<>();
    }
}
