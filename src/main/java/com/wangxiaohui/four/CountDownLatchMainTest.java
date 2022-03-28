package com.wangxiaohui.four;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchMainTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start 22222222");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        int max = 10;
        for (int i = 0; i < max; i++) {
            int flag = i;
            executorService.execute(() -> {
                try {
                    System.out.println("executorService:" + flag + " start!");
                    Thread.sleep(1000L);
                    System.out.println("executorService:" + flag + " end!");
                    // 等待数 减1
                    countDownLatch.countDown();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
        // 等待数 归0
        countDownLatch.await();
        System.out.println("main end 22222222");
    }
}
