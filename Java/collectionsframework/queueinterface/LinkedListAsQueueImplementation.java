package queueinterface;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListAsQueueImplementation {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // throws exception if queue is empty
        queue.offer(2); // returns false if failed to add element due to any reason

        queue.remove(); // throws NoSuchElement exception if queue is empty
        queue.poll(); // returns null if queue is empty

        queue.peek(); // returns null if queue is empty
        queue.element(); // throws NoSuchElement exception if queue is empty
    }
}