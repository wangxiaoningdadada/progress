package com.progress.progressapi.test.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * description
 *
 * @author xiaoning.wang
 * @date 2024-04-17 17:00
 */
public class CompletableFutureUtil {

    public static <T> T getResult(CompletableFuture<T> future) {
        T t;
        try {
            t = future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

}
