package com.wangcheng.leetcode.LeetCode.Interview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 【题目】
 * 面试题 #.02. 生产者消费者模式
 * <p>
 * 生产者消费者模式要保证的是当缓冲区满的时候生产者不再生产对象；当缓冲区空时消费者不再消费对象。
 * 实现机制就是当缓冲区满时让生产者处于等待状态，当缓冲区为空时让消费者处于等待状态。
 * 当生产者生产了一个对象后会唤醒消费者，当消费者消费一个对象后会唤醒生产者。
 *
 * @author sunfusheng
 * @since 2020/5/17
 */
public class ProducerConsumer {

    private static final int MAX_SIZE = 10;

    /**
     * 生产者消费者模式一：wait 和 notify
     */
    private static final Queue<Object> list = new LinkedList<>();

    // 生产者1
    private static void produce1() throws InterruptedException {
        synchronized (list) {
            if (list.size() == MAX_SIZE) {
                println("仓库已满，暂停生产");
                list.wait();
            }
            list.add(new Object());
            println("生产了一个产品，现库存为：" + list.size());
            list.notifyAll();
        }
    }

    // 消费者1
    private static void consume1() throws InterruptedException {
        synchronized (list) {
            if (list.size() == 0) {
                println("仓库已空，暂停消费");
                list.wait();
            }
            list.remove();
            println("消费了一个产品，现库存为：" + list.size());
            list.notifyAll();
        }
    }

    /**
     * 生产者消费者模式二：await 和 signal
     */
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition notEmpty = lock.newCondition(); // 出队条件
    private static final Condition notFull = lock.newCondition(); // 入队条件

    // 生产者2
    private static void produce2() throws InterruptedException {
        lock.lock();
        while (list.size() == MAX_SIZE) {
            println("仓库已满，暂停生产");
            notEmpty.await();
        }
        list.add(new Object());
        println("生产了一个产品，现库存为：" + list.size());
        notFull.signalAll();
        lock.unlock();
    }

    // 消费者2
    private static void consume2() throws InterruptedException {
        lock.lock();
        while (list.size() == 0) {
            println("仓库已空，暂停消费");
            notFull.await();
        }
        list.remove();
        println("消费了一个产品，现库存为：" + list.size());
        notEmpty.signalAll();
        lock.unlock();
    }

    /**
     * 生产者消费者模式三：LinkedBlockingQueue
     */
    private static final LinkedBlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>(MAX_SIZE);

    // 生产者3
    private static void produce3() throws InterruptedException {
        if (blockingQueue.size() == MAX_SIZE) {
            println("仓库已满，暂停生产");
        }
        blockingQueue.put(new Object());
        println("生产了一个产品，现库存为：" + blockingQueue.size());
    }

    // 消费者3
    private static void consume3() throws InterruptedException {
        if (blockingQueue.size() == 0) {
            println("仓库已空，暂停消费");
        }
        blockingQueue.take();
        println("消费了一个产品，现库存为：" + blockingQueue.size());
    }

    private static void println(String tip) {
        System.out.println(Thread.currentThread().getName() + ": " + tip);
    }

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            while (true) {
                try {
                    produce2();
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.setName("producerThread");
        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    consume2();
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumerThread.setName("consumerThread");
        producerThread.start();
        consumerThread.start();
    }
}
