package listinterface;

import java.util.Stack;

public class StackImplementation {
    // follows LIFO
    // subclass of vector, which means inherits all the features but restricted by LIFO principle

    // it is recommended to use ArrayDeque as a stack in single threaded env
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        // push
        // pop
        // peek
        // isEmpty
        // size
        // search(object) -> returns index of that object from top following 1-based indexing else -1
    }
}