
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueImplementation {
    public static void main(String[] args) {
        // order elements based on their natural ordering (for premitives lowest first)
        // we can implement comparable to introduce custom ordering
        // or we can also use custom comparator
        // does not allow null elements
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(3);
        queue.add(6);
        queue.add(5);

        System.out.println(queue); 
        // it does not mean that our queue will be in sorted order but it will make sure that
        // elements will be processed in order of their priority
        // this happens because elements are stored in a min-heap(default), hence root will have
        // heighest priority and on removal the next element with heighest priority will become
        // the root hence complexity while removing or adding element is O(log n)
    
        while(!queue.isEmpty()) System.out.println(queue.poll());
    }
}
