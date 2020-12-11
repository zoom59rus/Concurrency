package main.java.com.zoom59rus.javacore.chapter14.task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class FizzBuzz {
    private BlockingQueue<Integer> numbers;
    private AtomicBoolean a;
    private AtomicBoolean b;

    private Semaphore threadA = new Semaphore(1);
    private Semaphore threadB = new Semaphore(0);
    private Semaphore threadC = new Semaphore(0);
    private Semaphore threadD = new Semaphore(0);

    public FizzBuzz(int n) {
        this.numbers = new ArrayBlockingQueue<>(n);
        for (int i = 1; i <= n; ) {
            numbers.add(i++);
        }
        this.a = new AtomicBoolean(false);
        this.b = new AtomicBoolean(false);
    }

    public void fizz() {
        try {
            threadA.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(a.get() && !b.get()){
            if(numbers.size() == 1){
                System.out.print("fizz");
                return;
            }else System.out.print("fizz, ");

            try {
                numbers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.set(false);
            b.set(false);
            threadB.release();
            fizz();
        }

        if(numbers.peek() % 3 == 0){
            a = new AtomicBoolean(true);
        }else {
            a = new AtomicBoolean(false);
        }
        threadB.release();
        fizz();
    }


    public void buzz() {
        try {
            threadB.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(numbers.peek() % 5 == 0){
            b = new AtomicBoolean(true);
            if(!a.get()){
                if(numbers.size() == 1){
                    System.out.print("buzz");
                    return;
                }else System.out.print("buzz, ");
                try {
                    numbers.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.set(false);
                b.set(false);
                threadA.release();
                buzz();
            }
            threadC.release();
            buzz();
        }else if (!a.get()){
            threadD.release();
            buzz();
        }else {
            threadA.release();
            buzz();
        }
    }

    public void fizzbuzz() {
        try {
            threadC.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(numbers.size() == 1){
            System.out.print("fizzbuzz");
            return;
        }else System.out.print("fizzbuzz, ");
        try {
            numbers.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.set(false);
        b.set(false);
        threadA.release();
        fizzbuzz();
    }

    public void number() {
        try {
            threadD.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if(numbers.size() == 1){
                System.out.print(numbers.take());
                return;
            }else System.out.print(numbers.take() + ", ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.set(false);
        b.set(false);
        threadA.release();
        number();
    }
}