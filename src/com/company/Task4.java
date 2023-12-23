package com.company;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Task4 {
    public static void main(String[] args) throws InterruptedException {
        TwoCounters obj = new TwoCounters();
        Runnable r = () -> {
            for (int i = 0; i < 1_000; i++) {
                obj.incrementLong();
                obj.incrementInteger();
            }
        };

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        Thread thread3 = new Thread(r);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(obj.getTypeInteger());
        System.out.println(obj.getTypeLong());
    }

    public static class TwoCounters {
        private final AtomicLong typeLong = new AtomicLong();
        private final AtomicInteger typeInteger = new AtomicInteger();

        public AtomicLong getTypeLong() {
            return typeLong;
        }

        public AtomicInteger getTypeInteger() {
            return typeInteger;
        }

        public void incrementLong() {
            typeLong.getAndIncrement();
        }

        public void incrementInteger() {
            typeInteger.getAndIncrement();
        }
    }
}