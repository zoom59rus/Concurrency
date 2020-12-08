package main.java.com.zoom59rus.javacore.chapter14.task1;

public class Foo {
    int count = 1;

    synchronized public void first() {
        //Почему не корректно работает через if else?
        while (count != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("first");
        count++;
        notifyAll();
    }

    synchronized public void second() {
        while (count != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("second");
        count++;
        notifyAll();
    }

    synchronized public void third() {
        while (count != 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("third");
        count++;
        notifyAll();
    }
}