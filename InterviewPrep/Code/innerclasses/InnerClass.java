interface Anonymous {
    void show();
    void print();
}

public class InnerClass {
    // there are four main type of inner classes in java

    // 1. member class
    class Member {

    }

    // 2. static inner class
    static class StaticClass {

    }

    public static void main(String[] args) {
        // 3. local method class
        class LocalMethod {

        }

        // scope is withing this method only
        LocalMethod obj = new LocalMethod();
    }

    // 4. anonymous class
    // anonymous classes object is one time creation
    public Anonymous obj = new Anonymous() {
        public void show() {
            System.out.println("this is an anonymous class");
        }

        public void print() {
            System.out.println("Hello there!");
        }
    };
}



class Demo {
    InnerClass obj1 = new InnerClass();
    InnerClass.Member obj2 = obj1.new Member(); // member class object
    InnerClass.StaticClass obj3 = new InnerClass.StaticClass(); // static inner class obj
}