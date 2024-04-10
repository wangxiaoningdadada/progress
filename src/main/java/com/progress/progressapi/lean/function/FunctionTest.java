package com.progress.progressapi.lean.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * description
 *
 * @author xiaoning.wang
 * @date 2024-04-10 10:19
 */
public class FunctionTest {

    public static void main(String[] args) {

        /*Supplier<FunctionTest> supplier = FunctionTest::new;
        FunctionTest test = supplier.get();
        System.out.println(test);*/

        /*Consumer<String> consumer = str -> System.out.println("consumer:"+str);
        consumer.accept("haha");*/

        /*Function<Integer, String> function = i -> Integer.toString(i);
        System.out.println(function.apply(123));*/

        /*MyFunctionInterface f1 = (a, b) -> a + b;
        System.out.println(f1.add("123", "456"));*/

        /*MyFunctionInterface f2 = Util::concat;
        System.out.println(f2.handle("hello", "world"));*/

        MyFunctionInterface2<int[]> f3 = int[]::new;
        int[] a = f3.get(3);
        System.out.println(a.length);
    }
}
