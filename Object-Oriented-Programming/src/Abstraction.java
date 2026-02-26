public class Abstraction {
}

// What? -> hiding the details
// How? -> using abstract classes and interfaces

abstract class A {
    // if you want 0->100% of abstraction, then abstract class is recommended
    // because it can contain both abstract and concrete methods
    public void sayHello() {
        System.out.println("Hello this is A");
    }
}

interface B {
    // it can only contain abstract methods, so it is used when 100% abstraction is required,
    // or we want our base class to inherit from multiple parents
    // as extending multiple classes is not allowed in java, but one can implement multiple interfaces

    // PS: after java8 interface can also have concrete methods, that are default methods
    // they were introduced for backward compatibility(?)
    // in java8 new features like stream, forEach usage with lambda expression,  were introduced, and if
    // one functionality is added to the interface all the classes implementing that interface would have to
    // provide an implementation and there was a risk to break existing code, so default methods were introduced
    // they allow the interface to provide a ready to use implementation, ensuring existing classes continue to compile
    // and run without modification
    default void sayHello() {
        System.out.println("Hello this is B");
    }
}
