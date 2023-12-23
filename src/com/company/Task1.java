package com.company;

public class Task1 {
    public static void first() {
        System.out.println("First");
    }

    public static void second() {
        System.out.println("Second");
    }

    public static void third() {
        System.out.println("Third");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(Task1::first);
        Thread thread2 = new Thread(Task1::second);
        Thread thread3 = new Thread(Task1::third);

        thread3.start();
        thread3.join();

        thread2.start();
        thread2.join();

        thread1.start();
        thread1.join();
    }
}
