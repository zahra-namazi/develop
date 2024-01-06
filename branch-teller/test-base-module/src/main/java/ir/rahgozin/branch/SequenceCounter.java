package ir.rahgozin.branch;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceCounter {
    private static AtomicInteger counter = new AtomicInteger();

    public static int getNextNumber() {
        return counter.addAndGet(1);
    }
}
