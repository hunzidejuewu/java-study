package com.wangxiaohui.four;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BlockingQueueMainTest {

//    　offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,
//      则返回true,否则返回false.（本方法不阻塞当前执行方法的线程）
//      offer(E o, long timeout, TimeUnit unit),可以设定等待的时间，如果在指定的时间内，还不能往队列中
//　　　加入BlockingQueue，则返回失败。
//      put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断
//　　　直到BlockingQueue里面有空间再继续.获取数据：
//      poll(time):取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,
//      取不到时返回null;
//　　  poll(long timeout, TimeUnit unit)：从BlockingQueue取出一个队首的对象，如果在指定时间内，
//      队列一旦有数据可取，则立即返回队列中的数据。否则知道时间超时还没有数据可取，返回失败。
//      take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到
//　　　BlockingQueue有新的数据被加入;
//　　  drainTo():一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数），
//      通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。
    public static void main(String[] args) throws Exception {
        System.out.println("main start 333333333333");
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        int max = 10;
        for (int i = 0; i < max; i++) {
            int flag = i;
            executorService.execute(() -> {
                try {
                    System.out.println("executorService:" + flag + " start!");
                    Thread.sleep(1000L);
                    System.out.println("executorService:" + flag + " end!");
                    blockingQueue.put(flag);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
        while (true) {
            if (blockingQueue.size() == 10) {
                List<Integer> queue = new ArrayList<>();
                blockingQueue.drainTo(queue);
                break;
            }
        }
        System.out.println("main end 333333333333");
    }
}
