package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.TreeSet;

/**
 *【题目】
 * 414.第三大的数
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，
 * 则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 *【示例】
 * 示例 1:
 * 输入: [3, 2, 1]
 * 输出: 1
 * 解释: 第三大的数是 1.
 *
 * 示例 2:
 * 输入: [1, 2]
 * 输出: 2
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 *
 * 示例 3:
 * 输入: [2, 2, 3, 1]
 * 输出: 1
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 *
 * @author liwangcheng
 * @date 2020/5/9.
 */
public class ThirdMax {

    /**
     * 思路一： 借用TreeSet（红黑树）
     * 比较好想的思路
     * 维护一个只有3个元素的TreeSet，如果大于三个元素就
     * 把Set中的最小最小值remove掉。
     * 最后如果Set中元素小于3就返回Set最大值，否则返回最小值。
     * 时间复杂度： O(n * log3) == O(n)
     */
    public static int solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer elem : nums) {
            set.add(elem);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        // set.last() 里面最大的元素
        return set.size() < 3 ? set.last() : set.first();
    }

    /**
     * 思路二：三个变量
     * 用三个变量来存放第一大，第二大，第三大的元素的变量，
     * 分别为one, two, three；
     * 遍历数组，若该元素比one大则往后顺移一个元素，
     * 比two大则往往后顺移一个元素，比three大则赋值给three；
     * 最后得到第三大的元素，若没有则返回第一大的元素。
     */
    public static int solution2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int one = nums[0];
        int two = Integer.MIN_VALUE;
        int three = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            int now = nums[i];
            if (now == one || now == two || now == three) {
                continue;
            }
            if (now > one) {
                three = two;
                two = one;
                one = now;
            } else if (now > two) {
                three = two;
                two = now;
            } else if (now > three) {
                three = now;
            }
        }
        if (three == Integer.MIN_VALUE) {
            return one;
        }
        return three;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1([3,2,1]) = " + ThirdMax.solution1(new int[]{3,2,1}));
        LeetCodeUtil.logln("solution1([2,1])" + ThirdMax.solution1(new int[]{2,1}));
        LeetCodeUtil.logln("solution1([2,2,3,1])" + ThirdMax.solution1(new int[]{2,2,3,1}));
        LeetCodeUtil.logln("solution2([3,2,1]) = " + ThirdMax.solution2(new int[]{3,2,1}));
        LeetCodeUtil.logln("solution2([2,1])" + ThirdMax.solution2(new int[]{2,1}));
        LeetCodeUtil.logln("solution2([2,2,3,1])" + ThirdMax.solution2(new int[]{2,2,3,1}));
    }
}
