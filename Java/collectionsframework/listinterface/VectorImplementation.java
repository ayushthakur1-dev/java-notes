
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class VectorImplementation {
    // part of java.util.package and is one of the legacy class 
    // was introduced in jdk 1.0 before collection framework
    // is synchronized, which makes it thread safe
    // now is part of collection framework
    // uses internal array

    // not recommended to use due to it's synchrnized overhead due to locking and unlocking cost
    // use ArrayList in single threaded env
    // but still can be useful in certain situations where thread safety is a concern

    // key features: 
    // dynamic size
    // synchronized but a legacy class
    // growth factor: 2x but changable by prividing it inside constructor
    // unlike arrayList there is method to check capacity of vector
    public static void main(String[] args) {
        // four types of constructors
        List<Integer> list1 = new Vector<>();
        List<Integer> list2 = new Vector<>(Arrays.asList(1,2,3,4));
        List<Integer> list3 = new Vector<>(10);
        List<Integer> list4 = new Vector<>(10, 3);

        // add -> at end, at index
        // get -> by index
        // set -> index, element
        // remove -> by element or by index
        // size -> returns size
        // isEmpty -> returns true if empty
        // contains -> checks if element is present
        // clear -> remove all elements
        // capacity -> returns capacity
    }
}
