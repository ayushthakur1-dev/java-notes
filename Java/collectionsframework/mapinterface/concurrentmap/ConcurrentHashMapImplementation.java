package mapinterface.concurrentmap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapImplementation {
    // java7 -> segment based locking
    // by default it gets divided into 16 segments 
    // unlike the hashTable which locks the whole structure on both read and write operations
    // here only the segment on which write operation is being performed will be locked
    // it does not require locking while reading unless there is a write operation happening on
    // the same segment

    // java8 -> no segmentation -> now it uses compare and swap 
    // which means no locking until resizing or collision is happening

    // how compare and swap works? -> EXAMPLE:
    // lets say we have a variable x = 50
    // now Thread A wants to change its value to 60
    // first it will check the value of x that is 50
    // then it will try to change the value to 60, but while changing it will again check the value of x
    // if it is still 50 it means no other thread is working on it, so it wil change the value to 60
    // but while changing if value of x does not match to it's last seen value it means some other thread
    // is also working on it, in that case Thread A will fall back for short period of random time 
    // and retry after that with same method
    // so next time it will again compare the value of x with its last seen value, which was now 60
    // and if it is still same, it is safe to change its value
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
    }
}