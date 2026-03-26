import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;

// it is a creational design pattern used to create objects without exposing which class is being
// instantiated, eg. Integer.valueOf() or String.valueOf()

class Factory {
    public static void main(String[] args) {
        RegistrationBasedShapeFactory.register("Circle", Circle::new);
        RegistrationBasedShapeFactory.register("Square", Square::new);

        Shape circle = RegistrationBasedShapeFactory.createShape("Circle");
    }
}

interface Shape{}

class Circle implements Shape{
}

class Square implements Shape {
}

// this simple factory pattern conflicts with open close principle to avoid that we
// can use registration based factory desgin pattern
class ShapeFactory {
    public static Shape createShape(String shape) {
        if(shape == null) throw new IllegalArgumentException("cannot ask for null");
        else if(shape.equalsIgnoreCase("circle")) {
            return new Circle();
        }
        else if(shape.equalsIgnoreCase("square")) {
            return new Circle();
        }
        else throw new IllegalArgumentException("request does not match");

//        return null;
    }
}

// somewhat similar concept is also used in Spring framework bean factory but it is much
// more complex as istead of simple supplier it stores metadata of class, uses reflection and
// many other concepts
class RegistrationBasedShapeFactory {
    private static Map<String, Supplier<Shape>> registry = new HashMap<>();

    public static void register(String key, Supplier<Shape> value) {
        if(key == null || value == null) throw new IllegalArgumentException("cannot add null registries");
        registry.put(key.toLowerCase(), value);
    }

    public static Shape createShape(String key) {
        if(key == null) throw new IllegalArgumentException("null key cannot be requested");

        Supplier supplier = registry.get(key.toLowerCase());
        if(supplier == null) throw new IllegalArgumentException("key does not exist");

        return (Shape)supplier.get();
    }

}