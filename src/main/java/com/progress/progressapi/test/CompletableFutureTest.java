package com.progress.progressapi.test;

import com.progress.progressapi.test.util.CompletableFutureUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * description
 *
 * @author xiaoning.wang
 * @date 2024-04-17 16:59
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.completedFuture("Hello");
        CompletableFuture<Integer> future2 = CompletableFuture.completedFuture(42);
        CompletableFuture<List<String>> future3 = CompletableFuture.completedFuture(Arrays.asList("a", "b", "c"));

        System.out.println(CompletableFutureUtil.getResult(future1));
        System.out.println(CompletableFutureUtil.getResult(future2));
        System.out.println(CompletableFutureUtil.getResult(future3));
    }
}
