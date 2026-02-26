public class Polymorphism {
}

// Types: compile time(method overloading) and runtime(method overriding)
class BaseClass {
    // overloading
    public void sayHello() {
        System.out.println("hello");
    }

    public void sayHello(String str) {
        System.out.println("Hello " + str);
    }
}

class ChildClass {
    // overriding
    public void sayHello(String str) {
        System.out.println(str + " hello");
    }

    // NOTE: static methods are not overloaded because child class can access parent's
    // class static methods, but they are not inherited, so if same method signature is defined in
    // child class then it will be called method hiding
}