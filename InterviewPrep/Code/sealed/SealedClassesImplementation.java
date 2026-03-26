public class SealedClassesImplementation {
    public static void main(String[] args) {

    }
}

sealed class Vehicle permits Car, Bike, Truck{

}

final class Car extends Vehicle {

}

sealed class Bike extends Vehicle permits Honda {

}

final class Honda extends Bike {

}

non-sealed class Truck extends Vehicle {

}

