public class Encapsulation {
}

// encapsulation means wrapping data and methods together and controlling the access of that data
// how can I wrap them together? -> by creating a class
class Animal {
    // how can I control data accessing? -> using access modifiers
    // they control who can see, or use a class, method or variable

    // private: can be accessed within the same class only
    // cannot be used on outer class

    // default behavior(no access modifier), package private: can be accessed within same package only

    // protected: can be accessed withing same package or by the subclass in another package
    // cannot be used on outer class

    // public: can be access from anywhere
    // one .java source file can contain many classes
    // but only one of them can be public
    // and if the public is declared then the file name should match with it

    // but why this rule is enforced by java?
    // Java enforces one public class per file primarily to keep source organization deterministic.
    // It allows developers and tools to locate a type immediately from its fully qualified name.
    // This simplifies compilation, dependency management, and maintenance.
    // The restriction is less about JVM loading efficiency, since the JVM loads individual .class files anyway.
}
