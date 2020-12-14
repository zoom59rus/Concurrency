package main.java.com.zoom59rus.javacore.chapter14.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class Application {
    public static void main(String[] args) {
        Foo foo = new Foo();
        ExecutorService es = ForkJoinPool.commonPool();

        es.execute(foo::third);
        es.execute(foo::second);
        es.execute(foo::first);


        es.shutdown();

    }
}
