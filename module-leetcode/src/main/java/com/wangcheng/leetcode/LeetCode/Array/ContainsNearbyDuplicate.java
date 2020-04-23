package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *【题目】
 * 219.存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 *【示例】
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 * @author liwangcheng
 * @date 2020/4/23.
 */
public class ContainsNearbyDuplicate {

    /**
     * 方法一:（线性搜索）
     * 思路
     * 将每个元素与它之前的 k 个元素中比较查看它们是否相等。
     * 算法
     * 这个算法维护了一个 k 大小的滑动窗口，然后在这个窗口里面搜索是否存在跟当前元素相等的元素。
     *
     * 复杂度分析
     * 时间复杂度：O(nmin(k,n))
     * 每次搜索都要花费 O(min(k,n)) 的时间，哪怕k比n大，一次搜索中也只需比较 n 次。
     * 空间复杂度：O(1)
     */
    public static boolean solution1(int[] nums, int k) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = Math.max(0, i - k); j < i; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法二:（二叉搜索树）
     * 思路
     * 通过自平衡二叉搜索树来维护一个 k 大小的滑动窗口。
     * 算法
     * 这个方法的核心在于降低方法一中搜索前 k 个元素所耗费的时间。
     * 伪代码是这样的：
     * - 遍历数组，对于每个元素做以下操作：
     *  - 在 BST 中搜索当前元素，如果找到了就返回 true。
     *  - 在 BST 中插入当前元素。
     *  - 如果当前 BST 的大小超过了 k，删除当前 BST 中最旧的元素。
     * - 返回 false。
     *
     * 复杂度分析
     * 时间复杂度：O(nlog(min(k,n)))
     * 我们会做 n 次 搜索，删除，插入 操作。每次操作将耗费对数时间，即为 log(min(k,n))。
     * 注意，虽然 k 可以比 n 大，但滑动窗口大小不会超过 n。
     * 空间复杂度：O(min(n,k))
     * 只有滑动窗口需要开辟额外的空间，而滑动窗口的大小不会超过 O(min(n,k))。
     *
     * 注意事项
     * 这个算法在 n 和 k 很大的时候依旧会超时。
     */
    public static boolean solution2(int[] nums, int k) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return false;
        }
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i-k]);
            }
        }
        return false;
    }

    /**
     * 方法三 （散列表）
     * 思路
     * 用散列表来维护这个kk大小的滑动窗口。
     *
     * 算法
     * 在之前的方法中，我们知道了对数时间复杂度的 搜索 操作是不够的。
     * 在这个方法里面，需要一个支持在常量时间内完成 搜索，删除，插入 操作的数据结构，
     * 那就是散列表。这个算法的实现跟方法二几乎是一样的。
     *
     * - 遍历数组，对于每个元素做以下操作：
     *  - 在散列表中搜索当前元素，如果找到了就返回 true。
     *  - 在散列表中插入当前元素。
     *  - 如果当前散列表的大小超过了 k， 删除散列表中最旧的元素。
     * - 返回 false。
     *
     * 复杂度分析
     * 时间复杂度：O(n)
     * 会做 n 次 搜索，删除，插入 操作，每次操作都耗费常数时间。
     * 空间复杂度：O(min(n,k))
     * 开辟的额外空间取决于散列表中存储的元素的个数，也就是滑动窗口的大小 O(min(n,k))。
     */
    public static boolean solution3(int[] nums, int k) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i-k]);
            }
        }
        return false;
    }

    /**
     * 方法四：HashMap辅助
     */
    public static boolean solution4(int[] nums, int k) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && Math.abs(i - map.get(nums[i])) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,1};
        int[] nums2 = new int[]{1,0,1,1};
        int[] nums3 = new int[]{1,2,3,1,2,3};
        LeetCodeUtil.logln(nums1);
        LeetCodeUtil.logln("solution1(3) = " + ContainsNearbyDuplicate.solution1(nums1, 3));
        LeetCodeUtil.logln("solution2(3) = " + ContainsNearbyDuplicate.solution2(nums1, 3));
        LeetCodeUtil.logln("solution3(3) = " + ContainsNearbyDuplicate.solution3(nums1, 3));
        LeetCodeUtil.logln("solution4(3) = " + ContainsNearbyDuplicate.solution4(nums1, 3));
        LeetCodeUtil.logln(nums2);
        LeetCodeUtil.logln("solution1(1) = " + ContainsNearbyDuplicate.solution1(nums2, 1));
        LeetCodeUtil.logln("solution2(1) = " + ContainsNearbyDuplicate.solution2(nums2, 1));
        LeetCodeUtil.logln("solution3(1) = " + ContainsNearbyDuplicate.solution3(nums2, 1));
        LeetCodeUtil.logln("solution4(1) = " + ContainsNearbyDuplicate.solution4(nums2, 1));
        LeetCodeUtil.logln(nums3);
        LeetCodeUtil.logln("solution1(2) = " + ContainsNearbyDuplicate.solution1(nums3, 2));
        LeetCodeUtil.logln("solution2(2) = " + ContainsNearbyDuplicate.solution2(nums3, 2));
        LeetCodeUtil.logln("solution3(2) = " + ContainsNearbyDuplicate.solution3(nums3, 2));
        LeetCodeUtil.logln("solution4(2) = " + ContainsNearbyDuplicate.solution4(nums3, 2));
    }
}
