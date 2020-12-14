package main.java.com.zoom59rus.javacore.chapter14.task2;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        System.out.print("Введите число: ");
        Scanner sc = new Scanner(System.in);
        Integer number = sc.nextInt();
        CountDownLatch cdl = new CountDownLatch(number);
        FizzBuzz fb = new FizzBuzz(number, cdl);

        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(fb::number);
        es.execute(fb::fizz);
        es.execute(fb::fizzbuzz);
        es.execute(fb::buzz);

        try {
            cdl.await();
            es.shutdown();
            System.exit(0);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}