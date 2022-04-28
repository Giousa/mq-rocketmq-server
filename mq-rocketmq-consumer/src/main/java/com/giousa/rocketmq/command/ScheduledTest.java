package com.giousa.rocketmq.command;

import org.apache.rocketmq.common.ThreadFactoryImpl;
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTest {


    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl("NSScheduledThread"));

    public static void main(String[] args) {

        System.out.println("定时器准备：" + (new Date()));

        /**
         * 5：延时5秒后，开始执行
         * 10：间隔10秒，执行1次
         */
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("SECONDS 每隔10秒..." + (new Date()) + " 线程：" + Thread.currentThread().getName());
            }
        }, 5, 10, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("MINUTES 每隔1分钟..." + (new Date()) + " 线程：" + Thread.currentThread().getName());
            }
        }, 1, 1, TimeUnit.MINUTES);


        /**
         * jvm杀死后，执行此函数
         */
        InternalLogger log = InternalLoggerFactory.getLogger("logger_name");
        log.info("InternalLogger");
        Runtime.getRuntime().addShutdownHook(new ShutdownHookThread(log, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                System.out.println("now it is Shutdown");
                return null;
            }
        }));
    }
}
