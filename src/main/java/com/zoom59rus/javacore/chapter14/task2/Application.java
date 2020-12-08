package main.java.com.zoom59rus.javacore.chapter14.task2;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        System.out.print("Введите число: ");
        Scanner sc = new Scanner(System.in);
        Integer number = sc.nextInt();
        FizzBuzz fb = new FizzBuzz(number);

        ExecutorService es = Executors.newFixedThreadPool(4);

        CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i <= number ; i++) {
                fb.check();
            }
            return null;
        }, es);

    }
}
