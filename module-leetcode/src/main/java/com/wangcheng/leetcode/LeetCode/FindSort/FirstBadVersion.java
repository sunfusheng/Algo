package com.wangcheng.leetcode.LeetCode.FindSort;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 278.第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，
 * 你的产品的最新版本没有通过质量检测。由于每个版本都是基于
 * 之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后
 * 所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来
 * 判断版本号 version 是否在单元测试中出错。实现一个函数
 * 来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 *【示例】
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * 所以，4 是第一个错误的版本。
 *
 * @author liwangcheng
 * @date 2020/5/1.
 */
public class FirstBadVersion extends VersionControl {

    /**
     * 方法一：线性扫描 [超出时间限制]
     * 最直接的方法是进行一次线性扫描，即对 [1..n] 都调用一次 isBadVersion
     *
     * 复杂度分析
     * 时间复杂度：O(n)。在最坏的情况下，最多可能会调用 isBadVersion n-1 次，
     *  因此总的时间复杂度为 O(n)。
     * 空间复杂度：O(1)。
     */
    public static int solution1(int n) {
        if (n < 0) {
            return n;
        }
        FirstBadVersion badVersion = new FirstBadVersion();
        for (int i = 0; i < n; i++) {
            if (badVersion.isBadVersion(i)) {
                return i;
            }
        }
        return n;
    }

    /**
     * 方法二：二分查找 [通过]
     * 不难看出，这道题可以用经典的二分查找算法求解。
     *
     * 复杂度分析
     * 时间复杂度：O(logn)。搜索空间每次减少一半，因此时间复杂度为 O(logn)。
     * 空间复杂度：O(1)。
     */
    public static int solution2(int n) {
        if (n < 0) {
            return n;
        }
        int left = 1;
        int right = n;
        int mid = 0;
        FirstBadVersion badVersion = new FirstBadVersion();
        while (left < right) {
            mid = left + (right - left) / 2;
            if (badVersion.isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(19) = " + FirstBadVersion.solution1(19));
        LeetCodeUtil.logln("solution2(19) = " + FirstBadVersion.solution2(19));
    }
}

class VersionControl {
    boolean isBadVersion(int version) {
        return version >= 4;
    }
}