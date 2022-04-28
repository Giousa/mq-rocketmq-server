package com.giousa.rocketmq.lock;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest2 {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public static void main(String[] args) {

        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        list.parallelStream().forEach(it -> {
            try {
                System.out.println("准备获取锁:  i = " + it);
                lock.writeLock().lockInterruptibly();
                System.out.println("获取锁  成功：i = " + it);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("获取锁  失败：i = " + it);
            } finally {
                lock.writeLock().unlock();
            }
        });

    }
}
