package org.zach.threadpool;

import java.util.concurrent.*;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/27 4:45 下午
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                10,
                3000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        Future<String> callbackResult = executor.submit(() -> {
            System.out.println("开始执行任务...");
            return "result";
        });

        System.out.println(callbackResult.get());

    }

}
