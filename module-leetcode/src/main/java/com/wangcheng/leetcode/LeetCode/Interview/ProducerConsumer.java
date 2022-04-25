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
    private static final Queue<Object> mList = new LinkedList<>();

    // 生产者1
    private static void produce1() throws InterruptedException {
        synchronized (mList) {
            if (mList.size() == MAX_SIZE) {
                println("仓库已满，暂停生产");
                mList.wait();
            }
            mList.add(new Object());
            println("生产了一个产品，现库存为：" + mList.size());
            mList.notifyAll();
        }
    }

    // 消费者1
    private static void consume1() throws InterruptedException {
        synchronized (mList) {
            if (mList.size() == 0) {
                println("仓库已空，暂停消费");
                mList.wait();
            }
            mList.remove();
            println("消费了一个产品，现库存为：" + mList.size());
            mList.notifyAll();
        }
    }

    /**
     * 生产者消费者模式二：await 和 signal
     */
    private static final ReentrantLock mLock = new ReentrantLock();
    private static final Condition mEmpty = mLock.newCondition();
    private static final Condition mFull = mLock.newCondition();

    // 生产者2
    private static void produce2() throws InterruptedException {
        mLock.lock();
        while (mList.size() == MAX_SIZE) {
            println("仓库已满，暂停生产");
            mFull.await();
        }
        mList.add(new Object());
        println("生产了一个产品，现库存为：" + mList.size());
        mEmpty.signalAll();
        mLock.unlock();
    }

    // 消费者2
    private static void consume2() throws InterruptedException {
        mLock.lock();
        while (mList.size() == 0) {
            println("仓库已空，暂停消费");
            mEmpty.await();
        }
        mList.remove();
        println("消费了一个产品，现库存为：" + mList.size());
        mFull.signalAll();
        mLock.unlock();
    }

    /**
     * 生产者消费者模式三：LinkedBlockingQueue
     */
    private static final LinkedBlockingQueue<Object> mBlockingQueue = new LinkedBlockingQueue<>(MAX_SIZE);

    // 生产者3
    private static void produce3() throws InterruptedException {
        if (mBlockingQueue.size() == MAX_SIZE) {
            println("仓库已满，暂停生产");
        }
        mBlockingQueue.put(new Object());
        println("生产了一个产品，现库存为：" + mBlockingQueue.size());
    }

    // 消费者3
    private static void consume3() throws InterruptedException {
        if (mBlockingQueue.size() == 0) {
            println("仓库已空，暂停消费");
        }
        mBlockingQueue.take();
        println("消费了一个产品，现库存为：" + mBlockingQueue.size());
    }

    private static void println(String tip) {
        System.out.println(Thread.currentThread().getName() + ": " + tip);
    }

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            while (true) {
                try {
                    produce1();
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
                    consume1();
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
