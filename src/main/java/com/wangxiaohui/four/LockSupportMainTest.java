package com.wangxiaohui.four;

import java.util.concurrent.locks.LockSupport;

public class LockSupportMainTest {

    public static void main(String[] args) {
        System.out.println("main start 4444");
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread:" + " start!");
                Thread.sleep(1000L);
                System.out.println("Thread:" + " end!");
                // 启动主线程
                LockSupport.unpark(mainThread);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();

        // 暂停当前线程
        LockSupport.park();
        System.out.println("main end 4444");
    }
}
