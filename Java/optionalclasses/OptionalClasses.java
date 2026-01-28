
import java.util.Optional;

public class OptionalClasses {
    public static void main(String[] args) {
        // careating optional objects
        Optional<String> obj1 = Optional.empty();
        // value is present only if we have created Optional with a non-null value 
        System.out.println(obj1.isPresent());  // false

        // we can also create optional object with of()
        String name = "ayush";
        Optional<String> obj2 = Optional.of(name);
        System.out.println(obj2.isPresent()); // true

        // however argument passed to .of() can't be null, otherwise we will get a null pointer exception
        // in case where we can except null values, we can use ofNullable() method:
        name = null;
        Optional<String> obj3 = Optional.ofNullable(name);
        System.out.println(obj3.isPresent()); // false

        // isPresent(): returns true if obj is not empty
        // isEmpty():returns true is obj is empty

        // ifPresent(): performs the action only if object contains a value
        obj2.ifPresent(arg -> System.out.println(arg));
        obj3.ifPresent(arg -> System.out.println(arg));

        System.out.println();

        // orElse(): sets default value if argument is null
        String name1 = Optional.ofNullable(name).orElse("John");
        System.out.println(name1);

        // orElseGet(): sets default value if argument is null
        String name2 = Optional.ofNullable(name).orElseGet(() -> "John");
        System.out.println(name2);

        System.out.println();

        // Wait!! -> then what is the difference between these two?
        System.out.println("Using orElse:");
        String defaultText = Optional.ofNullable(name1).orElse(getMyDefault());
        System.out.println(defaultText);

        System.out.println("Using orElseGet:");
        defaultText = Optional.ofNullable(name1).orElseGet(OptionalClasses::getMyDefault); 
        System.out.println(defaultText);

        // in case of null values there is no such differenee between these two, 
        // but when values are non null:
        // method inside orElse if getting invoked although returned value is not stored anywhere
        // but for orElseGet method is not invoked at all 

        // orElseThrow(): throws an exception instead of returning any value at all
        defaultText = Optional.ofNullable(defaultText).orElseThrow(IllegalArgumentException::new);

        // Java10 introduced a simplefied no-arg vrsion of orElseThrow() method, 
        // in case of an empty Optional it throws a NoSuchElementException
        defaultText = Optional.ofNullable("John").orElseThrow();

        // get(): to get the non-null value present inside else it will also throw NoSuchElementException
        defaultText = obj2.get(); 
        // however this is a major flaw of get(), Ideally, Optional should help us avoid such unforseen exceptions.
        // Therefore, this approach works against the objectives of Optional and will probably be deprecated in future releases
        // So, it's advisable to use other variants that enables us to prepare for and explicitly handle the null case.

        // conditional return with filter():
        boolean isJohn = obj3.filter(x -> x == "John").isPresent();
        // the filter method is normally used this way to reject wrapped values based on a predefined rule
        // we could use it to reject a wrong email format or password that is not strong enough

        // other methods: 
        // map():
        // flatmap():
        
        // jdk9 optional methods: 
        // or(): provides a supplier that creates an alternative optional
        // isPresentOrElse(): allows executing an action if the Optional is present or another action if not
        // stream(): method for converting and Optional to a stream

        // using Optional in a serializable class will result in a NotSerializableException.
    }

    public static String getMyDefault() {
        System.out.println("Getting Default Value...");
        return "Getting Default Value...";
    }
}