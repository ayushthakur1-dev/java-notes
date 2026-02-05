package listinterface;

import java.util.LinkedList;

public class LinkedListImplementation {
    // Internal Working: 
    // stores it's elements as nodes in a doubly linked list
    // has two internal pointers next and previous
    // have pointers to both head and tail (but why?) -> 
    // it implements both List and Deque, so in order to 
    // do the operations of deque it should be able to add and remove elements from both ends
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        // add
        // add(element): add the element at end
        // addLast(element): add element at end
        // addFirst(element): add element at head

        // remove(element), 
        // remove(index), 
        // removeLastOccurence(), removeFirstOccurence(), removeAll(collection), ....

        // get: 
        // get(index): time complexity will be O(n)
        // getFirst(element), getLast(element), ....
    }
}
