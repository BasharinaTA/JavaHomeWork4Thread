package com.company;

import java.util.concurrent.Semaphore;

public class Task2 {
    public static void main(String[] args) {
        CheckPoint checkPoint1 = new CheckPoint();

        for (int i = 1; i <= 12; i++) {
            int n = i;
            Runnable r = () -> {
                checkPoint1.entry(n);
                checkPoint1.weigh(n);
                checkPoint1.exit(n);
            };
            Thread t = new Thread(r);
            t.start();
        }
    }

   public static class CheckPoint {
        Semaphore semaphoreEntry = new Semaphore(5);
        Semaphore semaphoreWeight = new Semaphore(5);
        Semaphore semaphoreExit = new Semaphore(5);

        public void entry(int n) {
            try {
                semaphoreEntry.acquire();
                System.out.printf("Грузовик %d начал заезжать\n", n);
                Thread.sleep((int) (Math.random() * 5_000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.printf("Грузовик %d освободил въезд\n", n);
                semaphoreEntry.release();
            }
        }

        public void weigh(int n) {
            try {
                semaphoreWeight.acquire();
                System.out.printf(" Грузовик %d начал взвешивание\n", n);
                Thread.sleep((int) (Math.random() * 5_000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.printf(" Грузовик %d освободил весы\n", n);
                semaphoreWeight.release();
            }
        }

        public void exit(int n) {
            try {
                semaphoreExit.acquire();
                System.out.printf("     Грузовик %d начал выезжать\n", n);
                Thread.sleep((int) (Math.random() * 3_000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.printf("     Грузовик %d освободил выезд\n", n);
                semaphoreExit.release();
            }
        }
    }
}
