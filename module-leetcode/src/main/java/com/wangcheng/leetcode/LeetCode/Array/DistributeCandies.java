package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.HashSet;

/**
 *【题目】
 * 575.分糖果
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，
 * 每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和
 * 一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 *【示例】
 * 示例 1:
 * 输入: candies = [1,1,2,2,3,3]
 * 输出: 3
 * 解析: 一共有三种种类的糖果，每一种都有两个。
 *      最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。
 *      这样使妹妹获得糖果的种类数最多。
 *
 * 示例 2 :
 * 输入: candies = [1,1,2,3]
 * 输出: 2
 * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种
 *      不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖
 *      果种类数最多。
 *【注意】
 * 数组的长度为[2, 10,000]，并且确定为偶数。
 * 数组中数字的大小在范围[-100,000, 100,000]内。
 *
 * @author liwangcheng
 * @date 2020/6/2.
 */
public class DistributeCandies {

    /**
     * TODO
     * 方法一：暴力法
     * 算法：
     * - 暴力法非常简单。可以生成代表糖果的给定 nums 数组的所有
     *  排列，并确定所生成数组前半部分中唯一元素的数目。
     * - 为了确定数组前半部分中唯一元素的数目，将所有需要的元素放
     *  在一个集合中，并计算集合中元素的数目。在生成的数组的前半部分
     *  中为所有可能的排列计算这样的唯一元素，并返回最大集合的大小。
     *
     * 复杂度分析
     * 时间复杂度：O(n!)。
     * 空间复杂度：O(n)，递归树的深度可以达到 n。
     */
    private static int maxKind;
    public static int solution1(int[] nums) {
        maxKind = 0;
        permute(nums, 0);
        return maxKind;
    }

    private static void permute(int[] nums, int l) {
        if (l == nums.length - 1) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length / 2; i++) {
                set.add(nums[i]);
            }
            maxKind = Math.max(maxKind, set.size());
        }
        for (int i = l; i < nums.length; i++) {
            swap(nums, i, l);
            permute(nums, l + 1);
            swap(nums, i, l);
        }
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    /**
     * 方法二：优化的暴力法
     * 算法：
     * - 在研究这种方法之前，首先需要观察一点。女孩能得到的唯一糖果
     *  的最大数量可以是 n/2，其中 n 是指糖果的数量。此外，如果独
     *  特的糖果数量低于 n/2 的话，为了使女孩能得到的独特的糖果数量
     *  最大化，会将所有独特的糖果分配给女孩。因此，在这种情况下，女
     *  孩得到的独特糖果数量等于给定 candies 数组中的独特糖果总数。
     * - 现在,需要在给定的 candies 数组中找到唯一糖果的总数。找到
     *  唯一糖果数量的一种方法是遍历给定的 candies 数组。每当遇到
     *  一个元素，比如 candies[j] 时，可以将所有与 candies[j]
     *  相同的元素标记为无效，并将唯一元素的计数增加 1。
     * - 最后，count 会提供给女孩所需数量的独特糖果。此外，要返回的
     *  值由：min(n/2 ,count) 给出。当 count 超过 n/2 时，可以
     *  停止对给定 candies 数组的遍历。
     *
     * 复杂度分析
     * 时间复杂度：O(n^2)。对于每一个新发现的元素，遍历 candies
     *  的所有元素。在最坏的情况下，对 candies 的每个元素都这样做。
     *  n 表示 candies 数组的大小。
     * 空间复杂度：O(1)。
     */
    public static int solution2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length && count < nums.length / 2; i++) {
            if (nums[i] != Integer.MIN_VALUE) {
                count++;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == nums[i]) {
                        nums[j] = Integer.MIN_VALUE;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 方法三：排序
     * 算法：
     * 可以对给定的 candies 数组进行排序，并通过比较排序数组的
     * 相邻元素来找出唯一的元素。对于找到的每个新元素（与前一个元
     * 素不同），需要更新 count。最后，可以将所需结果返回为
     * min(n/2,count)，如前面的方法所述。
     *
     * 复杂度分析
     * 时间复杂度：O(nlogn)。排序需要 O(nlogn) 的时间。
     * 空间复杂度：O(1)。
     */
    public static int solution3(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        for (int i = 1; i < nums.length && count < nums.length / 2; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 方法四：集合set
     * 算法：
     * 找到唯一元素数量的另一种方法是遍历给定 candies 数组的
     * 所有元素，并继续将元素放入集合中。通过集合的属性，它将只
     * 包含唯一的元素。最后，可以计算集合中元素的数量，例如
     * count。要返回的值将再次由 min(count,n/2) 给出，如前
     * 面的方法所述。其中 n 表示 candies 数组的大小。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。整个 candies 数组只遍历一次。这里，
     *  n 表示 candies 数组的大小。
     * 空间复杂度：O(n),在最坏的情况下，set 的大小为 n。
     */
    public static int solution4(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        return Math.min(set.size(), nums.length / 2);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,2,3,3};
        LeetCodeUtil.logln(Arrays.toString(nums));
        LeetCodeUtil.logln("solution1() = " + DistributeCandies.solution1(nums));
        LeetCodeUtil.logln("solution2() = " + DistributeCandies.solution2(nums));
        LeetCodeUtil.logln("solution3() = " + DistributeCandies.solution3(nums));
        LeetCodeUtil.logln("solution4() = " + DistributeCandies.solution4(nums));
        nums = new int[]{1,1,2,3};
        LeetCodeUtil.logln(Arrays.toString(nums));
        LeetCodeUtil.logln("solution1() = " + DistributeCandies.solution1(nums));
        LeetCodeUtil.logln("solution2() = " + DistributeCandies.solution2(nums));
        LeetCodeUtil.logln("solution3() = " + DistributeCandies.solution3(nums));
        LeetCodeUtil.logln("solution4() = " + DistributeCandies.solution4(nums));
    }
}
