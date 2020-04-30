package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *【题目】
 * 268.缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，
 * 找出 0 .. n 中没有出现在序列中的那个数。
 *【示例】
 * 示例 1:
 * 输入: [3,0,1]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 *【说明】
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 *
 * @author liwangcheng
 * @date 2020/4/30.
 */
public class MissingNumber {

    /**
     * 方法一：排序
     * 分析
     * 如果数组是有序的，那么就很容易知道缺失的数字是哪个了。
     * 算法
     * 首先对数组进行排序，随后可以在常数时间内判断两种特殊情况：
     * 0 没有出现在数组的首位，以及 n 没有出现在数组的末位。
     * 如果这两种特殊情况都不满足，那么缺失的数字一定在 0 和 n
     * 之间（不包括两者）。此时可以在线性时间内扫描这个数组，
     * 如果某一个数比它前面的那个数大了超过 1，那么这两个数之间的
     * 那个数即为缺失的数字。
     *
     * 复杂度分析
     * 时间复杂度：O(nlogn)。由于排序的时间复杂度为 O(nlogn)，
     *  扫描数组的时间复杂度为 O(n)，因此总的时间复杂度为 O(nlogn)。
     * 空间复杂度：O(1) 或 O(n)。空间复杂度取决于使用的排序算法，
     *  根据排序算法是否进行原地排序（即不使用额外的数组进行临时存储），
     *  空间复杂度为 O(1) 或 O(n)。
     */
    public static int solution1(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        Arrays.sort(nums);
        if (nums[nums.length-1] != nums.length) {
            return nums.length;
        }
        if (nums[0]!= 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i-1] > 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法二：哈希表
     * 分析
     * 可以直接查询每个数是否在数组中出现过来找出缺失的数字。
     * 如果使用哈希表，那么每一次查询操作都是常数时间的。
     * 算法
     * 将数组中的所有数插入到一个集合中，这样每次查询操作的
     * 时间复杂度都是 O(1) 的。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。集合的插入操作的时间复杂度都是 O(1)，
     *  一共插入了 n 个数，时间复杂度为 O(n)。集合的查询操作的
     *  时间复杂度同样是 O(1)，最多查询 n+1 次，时间复杂度为 O(n)。
     *  因此总的时间复杂度为 O(n)。
     * 空间复杂度：O(n)。集合中会存储 n 个数，因此空间复杂度为 O(n)。
     */
    public static int solution2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int expectedNumCount = nums.length + 1;
        for (int i = 0; i < expectedNumCount; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * TODO
     * 方法三：位运算
     * 分析
     * 由于异或运算（XOR）满足结合律，并且对一个数进行两次完全相同的
     * 异或运算会得到原来的数，因此可以通过异或运算找到缺失的数字。
     * 算法
     * 数组中有 n 个数，并且缺失的数在 [0..n] 中。因此可以先得到
     * [0..n] 的异或值，再将结果对数组中的每一个数进行一次异或运算。
     * 未缺失的数在 [0..n] 和数组中各出现一次，因此异或后得到 0。
     * 而缺失的数字只在 [0..n] 中出现了一次，在数组中没有出现，
     * 因此最终的异或结果即为这个缺失的数字。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。这里假设异或运算的时间复杂度是常数的，
     *  总共会进行 O(n) 次异或运算，因此总的时间复杂度为 O(n)。
     * 空间复杂度：O(1)。算法中只用到了 O(1) 的额外空间，用来存储答案。
     */
    public static int solution3(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    /**
     * 方法四：数学
     * 分析
     * 可以用 高斯求和公式 求出 [0..n] 的和，减去数组中所有数的和，
     * 就得到了缺失的数字。—— ∑ i = n(n+1)/2
     * 算法
     * 在线性时间内可以求出数组中所有数的和，并在常数时间内求出前
     * n+1 个自然数（包括 0）的和，将后者减去前者，就得到了缺失的数字。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。求出数组中所有数的和的时间复杂度为 O(n)，
     *  高斯求和公式的时间复杂度为 O(1)，因此总的时间复杂度为 O(n)。
     * 空间复杂度：O(1)。算法中只用到了 O(1) 的额外空间，用来存储答案。
     */
    public static int solution4(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return expectedSum - sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,3};
        LeetCodeUtil.logln("solution1(" + Arrays.toString(nums) + ") = " + MissingNumber.solution1(nums));
        LeetCodeUtil.logln("solution2(" + Arrays.toString(nums) + ") = " + MissingNumber.solution2(nums));
        LeetCodeUtil.logln("solution3(" + Arrays.toString(nums) + ") = " + MissingNumber.solution3(nums));
        LeetCodeUtil.logln("solution4(" + Arrays.toString(nums) + ") = " + MissingNumber.solution4(nums));
        nums = new int[]{9,6,4,2,3,5,7,0,1};
        LeetCodeUtil.logln("solution1(" + Arrays.toString(nums) + ") = " + MissingNumber.solution1(nums));
        LeetCodeUtil.logln("solution2(" + Arrays.toString(nums) + ") = " + MissingNumber.solution2(nums));
        LeetCodeUtil.logln("solution3(" + Arrays.toString(nums) + ") = " + MissingNumber.solution3(nums));
        LeetCodeUtil.logln("solution4(" + Arrays.toString(nums) + ") = " + MissingNumber.solution4(nums));
    }
}
