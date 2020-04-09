package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *【题目】
 * 169.多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 *【示例】  
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * @author liwangcheng
 * @date 2020/4/8.
 */
public class MajorityElement {

    /**
     * 方法一：哈希表
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * 空间复杂度：O(n)。
     */
    public static int solution1(int[] nums) {
        if (isEmpty(nums)) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                Integer value = map.get(key);
                map.put(key, value + 1);
            } else {
                map.put(key, 1);
            }
        }
        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (null == maxEntry || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }

    /**
     * 方法二：排序
     * 思路
     * 如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，
     * 那么下标为 ⌊ 2/n ⌋ 的元素（下标从 0 开始）一定是众数。
     *
     * 复杂度分析
     * 时间复杂度：O(nlogn)。将数组排序的时间复杂度为 O(nlogn)。
     * 空间复杂度：O(logn)。如果使用语言自带的排序算法，需要使用 O(logn) 的栈空间。
     */
    public static int solution2(int[] nums) {
        if (isEmpty(nums)) {
            return 0;
        }
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 方法三：随机化
     * 思路
     * 因为超过 ⌊2/n⌋ 的数组下标被众数占据了，这样随机挑选一个下标对应的元素并验证，
     * 有很大的概率能找到众数。
     * 算法
     * 由于一个给定的下标对应的数字很有可能是众数，随机挑选一个下标，
     * 检查它是否是众数，如果是就返回，否则继续随机挑选。
     * 说明
     * 这个随机化还是算了
     */

    /**
     * 方法四：分治
     * 思路
     * 如果数 a 是数组 nums 的众数，如果将 nums 分成两部分，那么 a 必定是至少一部分的众数。
     * 算法
     * 使用经典的分治算法递归求解，直到所有的子问题都是长度为 1 的数组。
     * 长度为 1 的子数组中唯一的数显然是众数，直接返回即可。
     * 如果回溯后某区间的长度大于 1，必须将左右子区间的值合并。
     * 如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。
     * 否则，需要比较两个众数在整个区间内出现的次数来决定该区间的众数。
     *
     * 复杂度分析
     * 时间复杂度：O(nlogn)。
     * 空间复杂度：O(logn)。
     */
    public static int solution4(int[] nums) {
        if (isEmpty(nums)) {
            return 0;
        }
        return majorityElementRec(nums, 0, nums.length-1);
    }

    private static int majorityElementRec(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return nums[lo];
        }
        int mid = (lo + hi) / 2;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);
        if (left == right) {
            return left;
        }
        int leftCount = countInRange(nums, left, lo, mid);
        int rightCount = countInRange(nums, right, mid + 1, hi);
        return leftCount > rightCount ? left : right;
    }

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 方法五：Boyer-Moore 投票算法
     * 思路
     * 如果把众数记为 +1，把其他数记为 −1，将它们全部加起来，显然和大于 0，
     * 从结果本身可以看出众数比其他数多。
     * 算法
     * Moore 算法的详细步骤：
     * 1.维护一个候选众数 candidate 和它出现的次数 count。
     * 初始时 candidate 可以为任意值，count 为 0；
     * 2.遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，
     * 如果 count 的值为 0，先将 x 的值赋予 candidate，随后判断 x：
     *  -如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
     *  -如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
     * 3.在遍历完成后，candidate 即为整个数组的众数。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。Boyer-Moore 算法只对数组进行了一次遍历。
     * 空间复杂度：O(1)。Boyer-Moore 算法只需要常数级别的额外空间。
     */
    public static int solution5(int[] nums) {
        if (isEmpty(nums)) {
            return 0;
        }
        int count = 0;
        int candidate = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    private static boolean isEmpty(int[] nums) {
        return null == nums || nums.length == 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 7, 7, 7, 7};
        LeetCodeUtil.logln("solution1 - " + MajorityElement.solution1(nums));
        LeetCodeUtil.logln("solution2 - " + MajorityElement.solution2(nums));
        LeetCodeUtil.logln("solution4 - " + MajorityElement.solution4(nums));
        LeetCodeUtil.logln("solution4 - " + MajorityElement.solution5(nums));
    }
}
