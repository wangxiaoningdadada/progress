package com.progress.progressapi.lean.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * lambda表达式 可以看作一个匿名函数
 * 基本语法: (parameters) -> expression 或 (parameters) ->{ statements; }
 * 三部分:
 * 1.(parameters) :表达式参数，只有一个参数（）可以省略
 * 2.-> :被用于的意思
 * 3.{ statements; } :可以是表达式，也可以是代码块。可以返回一个值也可以什么都不返回。
 *
 * @author xiaoning.wang
 * @date 2024-04-18 10:10
 */
public class LambdaTest {

    public static void main(String[] args) {
        // createThread();
        // demo();


    }


    public static void demo() {
        Supplier<Integer> s = () -> 2;
        System.out.println(s.get());
        Function<Integer, Integer> f = a -> a * 2;
        System.out.println(f.apply(128));
        BiFunction<Integer, Integer, Integer> bf = (a, b) -> a + b;
        System.out.println(bf.apply(3, 5));
        Consumer c = a -> System.out.println(a);
        c.accept("qwe");
    }

    public static void createThread() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("helle world!");
        });
        t.start(); // 启动线程
        try {
            t.join(); // 等待线程执行完毕
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("出来了");
    }
}
