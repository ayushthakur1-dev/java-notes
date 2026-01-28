
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeImplementation {
    public static void main(String[] args) {
        // double ended queue
        // allows insertion and deletion from both ends
        // varsatile than regular queue and stack because it support all the operations of both

        Deque<Integer> deque = new LinkedList<>(); // null allowed, more memory, slower iteration
        Deque<Integer> deque2 = new ArrayDeque<>(); // null not allowed, less memory, faster iteration
        // ArrayDeque uses an internal array with head and tail pointer so instead of shifting 
        // all the elements after deletion it simply shifts the iterator
        // if head == tail then internal array size is increased by 2x growth factor

        // addFirst()
        // addLast() / add()
        // offerFirst()
        // offerLast() / offer()

        // removeFirst()
        // removeLast() / remove()
        // pollFirst()
        // pollLast() / poll()

        // peekFirst() / peek()
        // peekLast()
        // element() -> similar to peekFirst() but throws exception
    }
}
