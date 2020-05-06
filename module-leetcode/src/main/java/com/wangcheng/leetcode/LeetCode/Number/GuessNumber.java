package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 374.猜数字大小
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，
 * 它会返回 3 个可能的结果（-1，1 或 0）：
 * <p>
 * -1 : 我的数字比较小
 *  1 : 我的数字比较大
 *  0 : 恭喜！你猜对了！
 * <p>
 *【示例】
 * 输入: n = 10, pick = 6
 * 输出: 6
 *
 * @author liwangcheng
 * @date 2020/5/6.
 */
public class GuessNumber extends GuessGame {

    /**
     * 方法 1：暴力
     * 从 1 到 n-1 检查每一个数字，并调用 guess 函数。
     * 如果输入数字返回 0 说明它是答案。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。从 1 到 n 扫描检查所有的数字。
     * 空间复杂度：O(1)。不需要使用额外空间。
     */
    public int solution1(int n) {
        for (int i = 1; i < n; i++) {
            if (guess(i) == 0) {
                return i;
            }
        }
        return n;
    }

    /**
     * 方法 2：使用二分查找
     * 算法
     * 可以直接使用二分查找来找到需要的数字。从中间的数字开始，
     * 将数字传递到 guess 函数里。如果返回 -1，说明中间数字
     * 比答案大，就查找更小的那一半。类似的，如果返回 1，查找
     * 更大的一半，直到找到答案。
     *
     * 复杂度分析：
     * 时间复杂度：O(log_2n)。为二分查找的时间复杂度。
     * 空间复杂度：O(1)。没有使用额外的空间。
     */
    public int solution2(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left)/2;
            int res = guess(mid);
            if (res == 0) {
                return mid;
            } else if (res > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return n;
    }

    /**
     * 方法 3：三分查找
     * 算法
     * 在二分查找中，选择中间元素作为分隔点。而在三分查找中，
     * 选择两个分隔点（比方记作 m1 和 m2），那么给定范围会
     * 被分成 3 个相等长度的部分。如果答案 num 比 m1 小，
     * 那么对 m1 左边的区间做三分查找。如果 num 在 m1 和
     * m2 中间，对中间区域进行三分查找。否则对 m2 右边的
     * 区域进行三分查找。
     *
     * 复杂度分析
     * 时间复杂度：O(log_3n)。为三分查找所需的时间复杂度。
     * 空间复杂度：O(1)。没有使用额外的空间。
     */
    public int solution3(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            if (res1 == 0) {
                return mid1;
            } else if (res2 == 0) {
                return mid2;
            } else if (res1 > 0) {
                high = mid1 - 1;
            } else if (res2 < 0) {
                low = mid2 + 1;
            } else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        GuessNumber guessNumber = new GuessNumber();
        LeetCodeUtil.logln("solution1(11) = " + guessNumber.solution1(11));
        LeetCodeUtil.logln("solution1(6) = " + guessNumber.solution1(6));
        LeetCodeUtil.logln("solution2(11) = " + guessNumber.solution2(11));
        LeetCodeUtil.logln("solution2(6) = " + guessNumber.solution2(6));
        LeetCodeUtil.logln("solution3(11) = " + guessNumber.solution3(11));
        LeetCodeUtil.logln("solution3(6) = " + guessNumber.solution3(6));
    }
}

class GuessGame {
    private static final int NUM = 7;
    /**
     * Forward declaration of guess API.
     * @param num your guess
     * @return -1 if num is lower than the guess number
     *		   1 if num is higher than the guess number
     *         otherwise return 0
     */
     int guess(int num) {
         int res = num - NUM;
         if (res > 0) {
             return 1;
         } else if (res < 0) {
             return -1;
         } else {
             return 0;
         }
     }
}
