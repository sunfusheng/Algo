package com.wangcheng.leetcode.LeetCode.FindSort;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;

/**
 * TODO
 *【题目】
 * 475.供暖器
 * 冬季已经来临。你的任务是设计一个有固定加热半径的供暖器
 * 向所有房屋供暖。
 * <p>
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以
 * 覆盖所有房屋的最小加热半径。
 * <p>
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的
 * 最小加热半径。
 * <p>
 *【说明】
 * 给出的房屋和供暖器的数目是非负数且不会超过 25000。
 * 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
 * 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
 * 所有供暖器都遵循你的半径标准，加热的半径也一样。
 *【示例】
 * 示例 1:
 * 输入: [1,2,3],[2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果将加热半径设为1，
 * 那么所有房屋就都能得到供暖。
 *
 * 示例 2:
 * 输入: [1,2,3,4],[1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。需要将加热半径设为1，
 * 这样所有房屋就都能得到供暖。
 *
 * @author liwangcheng
 * @date 2020/5/14.
 */
public class FindRadius {

    /**
     * 思路一：暴力
     * 开始对houses和heaters进行排序，对于每个房子，
     * 在供暖器中查找最近一个可以为其供暖的供暖器的位置，
     * 如果后一个供暖器的位置和房屋位置绝对值小于前一个
     * 供暖器与房屋位置绝对值，则继续向后找供暖器，否则
     * 当前供暖器即为要找的最近位置，然后在与之前的加热
     * 半径中求最大值。
     *
     * 参考：
     * https://leetcode-cn.com/problems/heaters/solution/shuang-zhi-zhen-he-er-fen-cha-zhao-jie-fa-by-tangw/
     */
    public static int solution1(int[] houses, int[] heaters) {
        // 1. 排序
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int res = 0;
        int i = 0;
        // 2. 双指针计算最大半径
        // 下面的代码最难懂的地方是 Math.abs(heaters[i] - house) >= Math.abs(heaters[i + 1] - house)
        // 我们举一个例子来说明这行代码的意思：
        // houses：1，2，3，4
        // heaters：1，4
        // 对于 house 1，heater 1 比 heater 4 更接近 house1，所以不将 i 移动到 i + 1
        // 对于 house 2，heater 1 比 heater 4 更接近 house2，所以不将 i 移动到 i + 1
        // 对于 house 3，heater 4 比 heater 1 更接近 house3，所以将 i 移动到 i + 1
        // 对于 house 4，依次类推
        for (int house : houses) {
            while (i < heaters.length - 1 &&
                    Math.abs(heaters[i] - house) >= Math.abs(heaters[i + 1] - house)) {
                i++;
            }
            // 更新最大半径
            res = Math.max(res, Math.abs(heaters[i] - house));
        }
        return res;
    }

    /**
     * 思路二：二分
     * 对每个房屋，在供暖器中进行二分查找第一个大于等于房屋位置
     * 的供暖器位置，如果找到，则可以求出之间差值dist1，如果这
     * 个数不是首个供暖器位置，可以求出和前一个供暖器的位置差值
     * dist2，最后在两者之间求最小值，并更新加热半径。
     *
     * 参考：
     * https://leetcode-cn.com/problems/heaters/solution/475-gong-nuan-qi-liang-chong-fang-fa-bao-li-er-fen/
     */
    public static int solution2(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int res = 0;

        for (int house : houses) {
            // 二分搜索当前 house 在 heaters 中的位置
            int index = binarySearch(heaters, house);

            // 说明没找到，index 等于当前的 house 应该在 heaters 的位置的负数
            if (index < 0) {
                // 计算出当前的 house 应该在 heaters 数组中的位置
                index = -(index + 1);
                // 计算当前 house 离左边最近的 heater 的距离
                int leftDist = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
                // 计算当前 house 离右边最近的 heater 的距离
                int rightDist = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
                // 当前 house 需要的 heater 的半径取 leftDist 和 rightDist 的最小值
                res = Math.max(res, Math.min(leftDist, rightDist));
            }
        }

        return res;
    }

    private static int binarySearch(int[] heaters, int house) {
        int low = 0;
        int hight = heaters.length - 1;
        while (low <= hight) {
            int mid = (low + hight) >>> 1;
            if (heaters[mid] < house) {
                low = mid + 1;
            } else if (heaters[mid] > house) {
                hight = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);
    }

    public static void main(String[] args) {
        int[] houses = new int[]{1,2,3};
        int[] heaters = new int[]{2};
        LeetCodeUtil.logln("solution1([1,2,3],[2]) = " + FindRadius.solution1(houses, heaters));
        LeetCodeUtil.logln("solution2([1,2,3],[2]) = " + FindRadius.solution2(houses, heaters));
        houses = new int[]{1,2,3,4};
        heaters = new int[]{1,4};
        LeetCodeUtil.logln("solution1([1,2,3,4],[1,4]) = " + FindRadius.solution1(houses, heaters));
        LeetCodeUtil.logln("solution2([1,2,3,4],[1,4]) = " + FindRadius.solution2(houses, heaters));
    }
}
