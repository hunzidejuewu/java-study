package com.wangxiaohui.four;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionMainTest {


    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        System.out.println("main start 555");
        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread:" + " start!");
                Thread.sleep(1000L);
                System.out.println("Thread:" + " end!");
                // 给等待信号的发送信号
                condition.signalAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        });
        thread.start();
        // 等待信号
        try {
            lock.lock();
            condition.await();
        } finally {
            lock.unlock();
        }
        System.out.println("main end 555");
    }
}
