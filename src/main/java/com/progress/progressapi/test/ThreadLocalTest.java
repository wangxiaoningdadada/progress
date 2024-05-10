package com.progress.progressapi.test;

/**
 * description
 *
 * @author xiaoning.wang
 * @date 2024-04-28 16:08
 */
public class ThreadLocalTest {


    public static void main(String[] args) {

        ThreadLocal<Long> local1 = new ThreadLocal<>();
        ThreadLocal<String> local2 = new ThreadLocal<>();

        local1.set(1L);
        local2.set("local2");


        System.out.println(local1);
        System.out.println(local2);

        System.out.println(local1.get());
        System.out.println(local2.get());


    }
}
