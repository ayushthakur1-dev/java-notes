public class ThreadMethods {
    // we have already seen overriden method run(),
    // start(), currentThread(), getName(), getState(), sleep(), and join()

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo("demo");

        // DEMON THREADS: 
        // thread that runs in background are knwon as demon threads, jvm does not wait for them to finish execution
        // eg. Garbage Collector
        // we can also set a user thread as demon thread by using setDemon(true)

        ThreadDemon demon = new ThreadDemon();
        demon.setDaemon(true);
        demon.start();
        // desipite of having infinite our execution will stop

        // Priority of threads 
        // Thread.MIN_PRIORITY = 1, Thread.NORM_PRIORITY = 5, Thread.MAX_PRIORITY = 10
        // setting priority of a thread does not apply any strict rule on them but it will give
        // hint to the scheduler that which process is more important 
        // it will be more noticable in single core processor because currently parallel execution is possible
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
        System.out.println(demo.getName() + " " + demo.getPriority());
        
        Thread.currentThread().setPriority(3);
        demo.setPriority(Thread.MAX_PRIORITY);
        
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
        System.out.println(demo.getName() + " " + demo.getPriority());
        
        System.out.println();

        demo.start();
        demo.interrupt(); // intrupts the state of thread if it is sleeping or blocked 


        for(int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
            
            // give hint to the scheduler that other thread should get chance of running
            // but again it is not strict rule
            Thread.currentThread().yield();
        }

        try {
            demo.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        demo.interrupt(); // terminated state will not be interrupted 
    }
}

class ThreadDemo extends Thread {
    ThreadDemo(String name) { // we can also set name for a thread
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } 
        
        for(int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class ThreadDemon extends Thread {
    ThreadDemon() {
        super("demon");
    }

    @Override 
    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }
}