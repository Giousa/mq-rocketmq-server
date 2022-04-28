package com.giousa.rocketmq.lock;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {

    // 线程不安全的list
    private ArrayList<String> array = Lists.newArrayList();
    // 独占锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    // 添加元素
    public void add(String e) {
        // 加锁
        System.out.println("add方法 加锁");
        writeLock.lock();
        try {
            // 添加元素
            array.add(e);
        } finally {
            // 最后释放锁
            System.out.println("add方法 释放锁");
            writeLock.unlock();
        }
    }

    // 删除元素
    public void remove(String e) {
        writeLock.lock();
        try {
            array.remove(e);
        } finally {
            writeLock.unlock();
        }
    }

    // 获取数据
    public String get(int index) {
        // 加读锁
        System.out.println("get方法 加锁");
        readLock.lock();
        try {
            return array.get(index);
        } finally {
            System.out.println("get方法 释放锁");
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        Thread[] td = new Thread[30];
        LockTest reentrantLockList = new LockTest();
        // 创建10个线程添加元素
        for (int i = 0; i < 10; i++) {
            ts[i] = new Thread(() -> {
                reentrantLockList.add("el");
            });
            ts[i].start();
        }
        for (int i = 0; i < 10; i++) {
            ts[i].join();
        }

        // 读取元素
        for (int j = 0; j < 30; j++) {
            td[j] = new Thread(() -> {
                System.out.println(reentrantLockList.get(6));
            });
            td[j].start();
        }
        for (int j = 0; j < 30; j++) {
            td[j].join();
        }
    }
}
