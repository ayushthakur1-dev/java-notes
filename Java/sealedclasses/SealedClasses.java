public class SealedClasses {
    public static void main(String[] args) {
        
    }
}

// creation of sealed classes: sealed class must have a subclass
sealed class Vehicle permits Car, Truck {
    protected final String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

// all the extending class must be in same module
// a permitted subclass must define a modifier, so further entension can be controlled: final, sealed or non sealed
final class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
    }
}

// non sealed clas must have a sealed parent class, and they are open for extension
non-sealed class Truck extends Vehicle {
    Truck(String registrationNumber) {
        super(registrationNumber);
    }
}

// creation of sealed interfaces
sealed interface Animal permits Dog {
    public String speak();
}

// record are by default final so they don't need any extra keyword but they can't extend any class as they already extends java.lang.Record
// but they work fine with interfaces
record Dog() implements Animal {
    private static String sound = "Bark";

    @Override
    public String speak() {
        return this.sound;
    }
}

// sealed classes are also supported by reflections