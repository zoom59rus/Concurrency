package main.java.com.zoom59rus.javacore.chapter14.task1;

public class Application {
    public static void main(String[] args) {
        Foo foo = new Foo();

        new Thread(foo::first).start();
        new Thread(foo::second).start();
        new Thread(foo::third).start();

    }
}
