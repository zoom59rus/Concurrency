package main.java.com.zoom59rus.javacore.chapter14.task2;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.print("Введите число: ");
        Scanner sc = new Scanner(System.in);
        Integer number = sc.nextInt();
        FizzBuzz fb = new FizzBuzz(number);

        new Thread(fb::fizz, "A").start();
        new Thread(fb::buzz, "B").start();
        new Thread(fb::fizzbuzz, "C").start();
        new Thread(fb::number, "D").start();

    }
}