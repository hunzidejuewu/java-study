package com.wangxiaohui.four;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerMainEndTest {


    public static void main(String[] args) {
        System.out.println("main start 111111111111");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 这里打标机
        AtomicInteger atomicInteger = new AtomicInteger();
        Integer max = 10;
        for (int i = 0; i < max; i++) {
            int flag = i;
            executorService.execute(() -> {
                try {
                    System.out.println("executorService:" + flag + " start!");
                    Thread.sleep(1000L);
                    System.out.println("executorService:" + flag + " end!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                // 一定要放到线程最后执行
                atomicInteger.incrementAndGet();
            });
        }
        while (true) {
            // 一直自旋
            if (atomicInteger.get() == 10) {
                break;
            }
        }
        System.out.println("main end 111111111111");
    }
}
