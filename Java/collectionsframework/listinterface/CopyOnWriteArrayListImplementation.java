package listinterface;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListImplementation {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        // copy on write means whenever a write operation like adding or removing an element
        // is performed instead of directly modifying the existing list a new copy of the list is
        // created and the modification is applied to that copy
        // this ensures that other threads reading the list while it's being modified are unaffected

        // fast and direct read operation since they happen on a stable list without interference from modifications
        // write operations: a new copy of list is created for every modification 
        // the reference to the list is then updated so that subsequent reads use this new list

        // use when there are read intensive operations 
        // as creating a new list for every write will take load on memory 
    }
}
