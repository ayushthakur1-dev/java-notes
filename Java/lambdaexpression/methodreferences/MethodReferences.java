
import java.util.Arrays;

public class MethodReferences {
    public static void main(String[] args) {
        // method reference to a static method
        Arrays.asList("Java", "C++", "C#", "Python").forEach(System.out::println);

        System.out.println();

        // method reference to a particular object
        Demo demo = new Demo(0);
        Arrays.asList(1,2,3,4,5).forEach(demo::show);

        System.out.println();

        // method reference to an arbitrary object
        String[] arr = {"john", "doe", "foo", "bar"};
        Arrays.sort(arr, String::compareToIgnoreCase); // compare using any string object at runtime 
        System.out.println(Arrays.toString(arr));

        System.out.println();

        // method reference to a constructor
        // we have to make a functional interface because java allows lambda expression and method reference
        // only to be assigned for them
        // here Demo:new is basically Demo(), calling the constructor of Demo class
        // which is equal to the lambda expression: () -> new Demo()

        FunctionalInterfaceForDemo demo2 = Demo::new;
        FunctionalInterfaceForDemo demo3 = x -> new Demo(x);
        
        // here demo2 and demo3 are objects made at runtime mapped to the hidden lambda function
        // we dont need to pass any argument in case of method reference, but how this works?
        // it do need arguments if they are asked in actual method, but we dont have to define them 
        // explicitly as it will take them while calling the method not while mapping it
        // so basically it is just short form of lambda expression

        Demo obj = demo2.getDemo(10);
        Demo obj1 = demo3.getDemo(20);
    }
}

class Demo {
    Demo(int x) {
        System.out.println(x);
    }

    public void show(int a) {
        System.out.println(a);
    }
}

interface FunctionalInterfaceForDemo {
    Demo getDemo(int x);
}