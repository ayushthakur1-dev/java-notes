public class MultiThreading {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()); // main
        }
        
        // To create a new thread in Java, you can either extend the Thread class 
        // or implement the Runnable Interface

        ThreadDemo1 demo1 = new ThreadDemo1();
        System.out.println(demo1.getName() + " " + demo1.getState()); // NEW
        demo1.start(); // initiates a new thread made from extending Thread class
        System.out.println(demo1.getName() + " " + demo1.getState()); // RUNNABLE

        try {
            demo1.sleep(10);
            System.out.println(demo1.getName() + " " + demo1.getState()); // TIMED_WAITING
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        ThreadDemo2 demo2 = new ThreadDemo2(Thread.currentThread());
        // initiating a new thread made from implementing Runnable Interface
        Thread t1 = new Thread(demo2); 
        t1.start();
        try {
            t1.join(); // holds all the operations until t1 is finished getting executed
            // so main thread will only take control after t1 is terminated
            System.out.println(t1.getName() + " " + t1.getState());  // TERMINATED
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // after executing this code you can notice that the flow of code is not completely sequential 
        // and is unpredictable because threads are being scheduled by jVM and OS schedular. 
    }
}

// first way to make a thread
class ThreadDemo1 extends Thread { // new thread
    /**
     * run method is overridden to define the code that constitutes the new thread
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState() + "(execution starts)"); // RUNNABLE?
        // Java does not have any offical state named as RUNNING
        // if a thread is ready to run or being executed, in both cases it will be set as RUNNABLE
        
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());

            if(i == 5) {
                // have to handle it as it is checked exception
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState() + "(execution resumes)");
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

// second way to make a thread
class ThreadDemo2 implements Runnable {
    Thread main;

    ThreadDemo2(Thread main) {
        this.main = main;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    
        checkMainThreadStatus(main); // main will be waiting until this thread is terminated due to join()
    }

    public void checkMainThreadStatus(Thread main) {
        System.out.println(main.getName() + " " + main.getState()); // TIMED_WAITING
    }
}