package main.java.com.zoom59rus.javacore.chapter14.task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FizzBuzz {
    private BlockingQueue<Integer> numbers;

    public FizzBuzz(int n) {
        this.numbers = new ArrayBlockingQueue<>(n);
        for (int i = 1; i <= n;) {
            numbers.add(i++);
        }
    }

    public void check(){
        int num = 0;
        try {
            num = numbers.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(num % 3 == 0){
            if(num % 5 == 0){
                fizzbuzz();
                return;
            }
            fizz();
        } else if(num % 5 == 0){
            buzz();
        } else {
            number(num);
        }
    }

    public void fizz() {
        System.out.print("fizz, ");
    }
    public void buzz() {
        System.out.print("buzz, ");
    }
    public void fizzbuzz() {
        System.out.print("fizzbuzz, ");
    }
    public void number(int n) {
        System.out.print(n + ", ");
    }
}