package queueinterface;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueImplementation {
    // thread-safe
    // used in cases where we need collaboration between different threads that is done through blocking
    public static void main(String[] args) {
        // a bounded blocking queue backed by a circular array
        // low memory overhead
        // uses a single lock for both enqueue and dequeue operations
        // more threads -> high waiting
        BlockingQueue<Integer> bQueue1 = new ArrayBlockingQueue<>(10);

        // optionally bounded(if capacity is not defined it will set capacity = Integer.MAX_VALUE)
        // queue backed by a linked list
        // high memory overhead
        // uses two separate locks for enqueue and dequeue operations
        // higher concurrency possible
        // more threads -> less waiting 
        BlockingQueue<Integer> bQueue2 = new LinkedBlockingQueue<>();

        // unbounded
        // binary heap as an array and can grow dynamically
        BlockingQueue<Integer> bQueue3 = new PriorityBlockingQueue<>();

        // it has capacity of almost one element
        // each insertion operation must wait for a corresponding remove operation by another thread and vice versa
        BlockingQueue<Integer> bQueue4 = new SynchronousQueue<>();

        // thread safe
        // unbounded
        // elements can only be taken from the queue when their delay has expired
        // useful for scheduling tasks to be executed after a certain delay
        // internally uses priority queue
        // have to provide Reference that implements Delayed interface
        BlockingQueue<DelayedTask> bQueue5 = new DelayQueue<>();
    }
}

class DelayedTask implements Delayed {
    private final String taskName;
    private final long startTime;

    DelayedTask(String taskName, long startTime) {
        this.taskName = taskName;
        this.startTime = startTime;
    }

    public long getStartTime() {
        return this.startTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remaining = startTime - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this.startTime < ((DelayedTask)o).getStartTime()) {
            return -1;
        }
        else if(this.startTime > ((DelayedTask)o).getStartTime()) {
            return 1;
        }
        return 0;
    }    
}