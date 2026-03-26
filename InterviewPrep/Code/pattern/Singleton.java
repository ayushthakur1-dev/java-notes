// it is a creational design pattern that is used to control creation of object,
// and make sure that only one instance of a particular class is available throughout
// our code base to be used as a shared resource
// eg. SLF4J -> simple logging fascade for java
class Singleton {
    public static void main(String[] args) {

    }
}

// lazy initialization, but not thread safe
class First {
    private static First INSTANCE;
    private First() {}

    public static First getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new First();
        }

        return INSTANCE;
    }
}

// eager initialization but thread safe
class Second {
    private static final Second INSTANCE = new Second();

    private Second() {}

    public static Second getInstance() {
        return INSTANCE;
    }
}

// lazy initialization, thread safe, but overhead in multithreaded environment
class Third {
    private static Third INSTANCE;

    private Third() {}

    synchronized public static Third getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Third();
        }

        return INSTANCE;
    }
}

// lazy initialization, thread safe -> but not relfection and serealization safe
class Fourth {
    private static class SingletonHeader {
        private static final Fourth INSTANCE = new Fourth();
    }

    private Fourth() {}

    public static Fourth getInstance() {
        return SingletonHeader.INSTANCE;
    }
}

// eager initialization but thread safe, reflection safe, serealization safe
enum Fifth {
    INSTANCE;
}

class Sixth {
    private static Sixth INSTANCE;

    private Sixth() {}

    public static Sixth getInstance() {
        if(INSTANCE == null) {
            synchronized (Sixth.class) {
                INSTANCE = new Sixth();
            }
        }
        return INSTANCE;
    }
}