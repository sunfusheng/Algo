package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *【题目】
 * 202.快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *【示例】
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 *
 * @author liwangcheng
 * @date 2020/4/21.
 */
public class IsHappy {

    /**
     * 方法一：用 HashSet 检测循环
     * 根据的探索，我们猜测会有以下三种可能。
     * 1.最终会得到 11。
     * 2.最终会进入循环。
     * 3.值会越来越大，最后接近无穷大。
     * PS:在代码中你不需要处理第三种情况，它永远不会发生
     * 算法：
     * 1.给一个数字 n，它的下一个数字是什么？
     * 2.按照一系列的数字来判断是否进入了一个循环。
     *
     * 复杂度分析
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(logn)。
     */
    public static boolean solution1(int n) {
        if (n == 1 || n == 0) {
            return true;
        }
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private static int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            n /= 10;
            sum += d * d;
        }
        return sum;
    }

    /**
     * 方法二：弗洛伊德循环查找算法
     * 通过反复调用 getNext(n) 得到的链是一个隐式的链表。
     * 隐式意味着没有实际的链表节点和指针，但数据仍然形成链表结构。
     * 起始数字是链表的头“节点”，链中的所有其他数字都是节点。
     * next 指针是通过调用 getNext(n) 函数获得。
     * 意识到实际有个链表，那么这个问题就可以转换为检测一个链表是否有环。
     *
     * 算法：
     * 不是只跟踪链表中的一个值，而是跟踪两个值，称为快跑者和慢跑者。
     * 在算法的每一步中，慢速在链表中前进 1 个节点，快跑者前进 2 个节点（对 getNext(n) 函数的嵌套调用）。
     * - 如果 n 是一个快乐数，即没有循环，那么快跑者最终会比慢跑者先到达数字 1。
     * - 如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇。
     *
     * 复杂度分析
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。
     */
    public static boolean solution2(int n) {
        if (n == 1 || n == 0) {
            return true;
        }
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    /**
     * 方法三：硬编码唯一循环
     * 前两种方法是你在面试中应该想到的。第三种方法不是你在面试中会写的，
     * 而是针对对数学好奇的人，因为它很有趣。
     * 下一个值可能比自己大的最大数字是什么？根据我们之前的分析，
     * 我们知道它必须低于 243。因此，我们知道任何循环都必须包含小于 243 的数字，
     * 用这么小的数字，编写一个能找到所有周期的强力程序并不困难。
     *
     * 如果这样做，您会发现只有一个循环：
     *   4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20。
     * 所有其他数字都在进入这个周期的链上，或者在进入 1 的链上。
     *
     * 因此，可以硬编码一个包含这些数字的散列集，如果达到其中一个数字，那么就知道在循环中。
     *
     * 复杂度分析
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。硬编码哈希集的大小是固定的。
     */
    private static Set<Integer> cycleMembers =
            new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));
    public static boolean solution3(int n) {
        if (n == 1 || n == 0) {
            return true;
        }
        while (n != 1 && !cycleMembers.contains(n)) {
            n = getNext(n);
        }
        return n == 1;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(9) = " + IsHappy.solution1(9));
        LeetCodeUtil.logln("solution1(99) = " + IsHappy.solution1(99));
        LeetCodeUtil.logln("solution1(999) = " + IsHappy.solution1(999));
        LeetCodeUtil.logln("solution1(4) = " + IsHappy.solution1(4));
        LeetCodeUtil.logln("solution1(37) = " + IsHappy.solution1(37));
        LeetCodeUtil.logln("solution1(145) = " + IsHappy.solution1(145));
        LeetCodeUtil.logln("solution2(9) = " + IsHappy.solution2(9));
        LeetCodeUtil.logln("solution2(99) = " + IsHappy.solution2(99));
        LeetCodeUtil.logln("solution2(999) = " + IsHappy.solution2(999));
        LeetCodeUtil.logln("solution2(4) = " + IsHappy.solution2(4));
        LeetCodeUtil.logln("solution2(37) = " + IsHappy.solution2(37));
        LeetCodeUtil.logln("solution2(145) = " + IsHappy.solution2(145));
        LeetCodeUtil.logln("solution3(9) = " + IsHappy.solution3(9));
        LeetCodeUtil.logln("solution3(99) = " + IsHappy.solution3(99));
        LeetCodeUtil.logln("solution3(999) = " + IsHappy.solution3(999));
        LeetCodeUtil.logln("solution3(4) = " + IsHappy.solution3(4));
        LeetCodeUtil.logln("solution3(37) = " + IsHappy.solution3(37));
        LeetCodeUtil.logln("solution3(145) = " + IsHappy.solution3(145));
    }
}
