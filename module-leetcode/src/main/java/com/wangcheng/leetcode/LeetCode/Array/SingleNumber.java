package com.wangcheng.leetcode.LeetCode.Array;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *【题目】
 * 136.只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 *【示例】
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author liwangcheng
 * @date 2020/4/6.
 */
public class SingleNumber {

    /**
     * 方法 1：列表操作
     * 算法
     * 1.遍历 nums 中的每一个元素
     * 2.如果某个 nums 中的数字是新出现的，则将它添加到列表中
     * 3.如果某个数字已经在列表中，删除它
     *
     * 复杂度分析
     * 时间复杂度：O(n^2)。遍历 nums 花费 O(n) 的时间。还要在列表中遍历判断是否存在这个数字，
     * 花费 O(n) 的时间，所以总循环时间为 O(n^2) 。
     * 空间复杂度：O(n) 。需要一个大小为 n 的列表保存所有的 nums 中元素。
     */
    public static int solution1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            Integer num = nums[i];
            if (list.contains(num)) {
                list.remove(num);
            } else {
                list.add(num);
            }
        }
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return 0;
    }

    /**
     * 方法 2：哈希表
     * 算法
     * 用哈希表避免每次查找元素是否存在需要的 O(n) 时间。
     *
     * 复杂度分析
     *
     * 时间复杂度： O(n⋅1)=O(n)。for 循环的时间复杂度是 O(n) 的。
     * 空间复杂度： O(n)。hash_table 需要的空间与 nums 中元素个数相等。
     */
    public static int solution2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer num = nums[i];
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        if (!set.isEmpty()) {
            return set.iterator().next();
        }
        return 0;
    }

    /**
     * TODO
     * 方法 3：数学
     * 概念
     * 2 * (a + b + c) - (a + a + b + b + c) = c
     *
     * 复杂度分析
     * 时间复杂度：O(n + n) = O(n)。sum 会调用 next 将 nums 中的元素遍历一遍。
     * 可以把上述代码看成 sum(list(i, for i in nums)) ，这意味着时间复杂度为 O(n)，
     * 因为 nums 中的元素个数是 n 个。
     * 空间复杂度： O(n + n) = O(n)。 set 需要的空间跟 nums 中元素个数相等。
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int solution3(int[] nums) {
        Integer[] numArray = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        int sum = 0;
        for (int i = 0; i < numArray.length; i++) {
            sum += numArray[i];
        }
        Set<Integer> set = new HashSet<>(Arrays.asList(numArray));
        int setSum = set.stream().mapToInt(Integer::intValue).sum();
        return 2 * setSum - sum;
    }

    /**
     * 方法 4：位操作
     * 概念
     * 如果对 0 和二进制位做 XOR 运算，得到的仍然是这个二进制位
     * a ⊕ 0 = a
     * 如果对相同的二进制位做 XOR 运算，返回的结果是 0
     * a ⊕ a = 0
     * XOR 满足交换律和结合律
     * a ⊕ b ⊕ a = (a ⊕ a) ⊕ b = 0 ⊕ b = b
     * 所以只需要将所有的数进行 XOR 操作，得到那个唯一的数字。
     */
    public static int solution4(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        int[] nums1 = new int[]{2,2,1};
        int[] nums2 = new int[]{4,1,2,1,2};
        LeetCodeUtil.logln("solution1(nums1) = " + SingleNumber.solution1(nums1));
        LeetCodeUtil.logln("solution1(nums2) = " + SingleNumber.solution1(nums2));
        LeetCodeUtil.logln("solution2(nums1) = " + SingleNumber.solution2(nums1));
        LeetCodeUtil.logln("solution2(nums2) = " + SingleNumber.solution2(nums2));
        LeetCodeUtil.logln("solution3(nums1) = " + SingleNumber.solution3(nums1));
        LeetCodeUtil.logln("solution3(nums2) = " + SingleNumber.solution3(nums2));
        LeetCodeUtil.logln("solution4(nums1) = " + SingleNumber.solution4(nums1));
        LeetCodeUtil.logln("solution4(nums2) = " + SingleNumber.solution4(nums2));
    }
}
