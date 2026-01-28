
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaExpression {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.addAll(List.of(1,2,3,4,5,6));
        list.addAll(Arrays.asList(7,5,3,2,6,4,7));

        list.forEach(i -> System.out.print(i + " "));
        System.out.println();
        list.removeIf(i -> (i & 1) == 1);
        System.out.println(list);

        // now we dont have to make anonymous classes
        Greet g = name -> System.out.println("Hello " + name); 
        g.sayHello("Ayush");
        System.out.println(g.getClass());

        Operations add = (a, b) -> a + b;
        Operations subtract = (a, b) -> {
            return a - b;
        };
        Operations multiply = (a, b) -> {return a * b;};
        Operations divide = (a, b) -> a / b;

        int a = 10; // effective final variable (not final but declared only once)
        int b = 20;
        b = 30;
        final int c = 40; // final variable

        // we can only refer final or effective final varibale inside a lambda function
        // if we try to change their values it will throw compilation error
        Operations check = (d, e) -> {
            System.out.println(a);
            System.out.println(c);
            // 'b' is not final or effective final variable so lambda cannot access it
            // System.out.println(b); -> b 
            return 0;
        };
        
        // even if value of 'a' is changed after the use in lambda function it will still throw error
        // a = (int)add.operate(10, 20); 

        b = (int)add.operate(10, 20);
    }
}

interface Greet {
    public void sayHello(String a);
}

interface Operations{ 
    public double operate(double a, double b);
}