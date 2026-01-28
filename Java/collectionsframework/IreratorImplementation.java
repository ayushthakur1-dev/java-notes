
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IreratorImplementation {
    public static void main(String[] args) {
        // forEach loop is applicable on collections because the implement interable interface
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5,6,7));

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // benefits of using iterator:
        // in a non-thread safe collection if we modify anything while traversing using forEach 
        // or any other loop, ConcurrentModificationException will be thrown
        // but iterators can modify a structure even while traversing 
        iterator = list.iterator();
        while(iterator.hasNext()) {
            Integer number = iterator.next();
            if((number & 1) == 0) {
                iterator.remove();  
            }
        }

        System.out.println(list);

        // we can also use ListIterator that provides additional methods like:
        // hasPrevious(), previous(), nextIndex(), previousIndex()
        ListIterator<Integer> listIterator = list.listIterator();
        System.out.println(listIterator.previousIndex());
    }
}