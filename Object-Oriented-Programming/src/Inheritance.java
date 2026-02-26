public class Inheritance {
}

// What? -> inheriting properties or functionality of parent
// How -> by extending existing class or implementing interface

abstract class Aa {
    public static void aStatic() {
        System.out.println("This is static method of Aa");
    }

    public void sayHello() {
        System.out.println("Hello this is Aa");
    }

    public abstract void common();
}

interface Bb {
    public static void bStatic() {
        System.out.println("this is static method of Bb");
    }

    default void sayHello() {
        System.out.println("Hello this is Bb");
    }

    default void sayBye() {
        System.out.println("Bye from Bb");
    }

    default void bMethod() {
        System.out.println("this is Bb method");
    }

    void common();
}

interface Cc {
    default void sayHello() {
        System.out.println("Hello this is Cc");
    }

    default void sayBye() {
        System.out.println("Bye from Cc");
    }

    default void common() {
        System.out.println("common");
    }
}

class Demod extends Aa implements Bb, Cc {
    // Rule of Inheritance:
    // Class > Interface default method >> Interface abstract method
    // and if two interfaces have same method signature present we have to override it to resolve ambiguity,
    // even if there is a concrete implementation present

    // if parent contains static method, it is accessible via child, but it doesn't mean that child
    // inherits that method, as static still belongs to base class only
    // and if method with same signature is defined inside child class, it will be called method hiding
    public static void aStatic() {
        System.out.println("This is static method of Demo");
    }

    // if there are two same methods in abstract class and interface
    // then abstract class method would be inherited
    // even if a method's concrete implementation is present in an interface, and abstract class has an abstract
    // method with same signature we have to override it
    @Override
    public void common() {

    }

    // if there is a default method in an interface, we don't have to override it,
    // but if that same method also exists in another interface we have to override it
    // to resolve ambiguity
    @Override
    public void sayBye() {
        Bb.super.sayBye();
    }
}