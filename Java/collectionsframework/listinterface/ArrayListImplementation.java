
import java.util.ArrayList;
import java.util.List;



public class ArrayListImplementation {
    // Internal Working: 
    // grows and shrinks dynamically
    // has an internal array of objects
    // default capacity is 10
    // when internal array is filled all the elements are copied to a new internal array

    // adding element:
    // check capacity, if there is enough space add the element
    // resize if necessary (usually 1.5x of current capacity) and then add the element

    // removing elements: 
    // check if index is within bounds,
    // if yes remove the element by shifting all the elements to right of it by 1 place to their left
    // reduce size by 1

    // NOTE: you can define initial capaicty before hand inside the constructor to save some overhead 

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        
        // add: 
        // add(element): adds element to the end
        // add(index, element): adds element to specified index and shift all the element 
        // from their one place to their right
        // addAll(collection): add all the elements at the end of ArrayList in one go

        // get:
        // get(index): returns element at index

        // remove:
        // remove(index): remove element by index
        // remove(element): remove element by element, keep in mind if you are removing an Integer
        // then you have to specify remove(Integer.valueOf(element)) or else it will treat it as index

        // set:
        // set(index, element): replace element at index with given element

        // contains:
        // contains(element): returns true or false if element is present inside list or not

        // toArray:
        // toArray(new Object[0]): convert ArrayList to ordinary array meaning now size will be fixed, but we can replace element
        // but why 0? -> when JVM will see that it has more element then this it will itself decide the size 
        // so it is not a hard and fast rule but just a convention

        // Arrays.asList() -> returns a fixed size list, elements can be replaced but size is fixed
        // List.of() -> returns an immutable list, now you can't even replace elements
    }
}