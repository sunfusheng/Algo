package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *【题目】
 * 492.构造矩形
 * 作为一位web开发者，懂得怎样去规划一个页面的尺寸是很重要的。
 * 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L
 * 和宽度为 W 且满足以下要求的矩形的页面。要求：
 * <p>
 * 1. 你设计的矩形页面必须等于给定的目标面积。
 * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
 * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
 * <p>
 * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
 * <p>
 * 【示例】
 * 输入: 4
 * 输出: [2, 2]
 * 解释: 目标面积是 4，所有可能的构造方案有 [1,4], [2,2], [4,1]。
 * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1]
 * 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
 * 【说明】
 * 给定的面积不大于 10,000,000 且为正整数。
 * 你设计的页面的长度和宽度必须都是正整数。
 *
 * @author liwangcheng
 * @date 2020/5/18.
 */
public class ConstructRectangle {

    /**
     * 方法一：暴力破解
     *
     * 参考：
     * https://leetcode-cn.com/problems/construct-the-rectangle/solution/zhi-xing-yong-shi-437-ms-zai-suo-you-java-ti-jiao-/
     */
    public static int[] solution1(int area) {
        if (area == 1) {
            return new int[]{1, 1};
        }
        int w = 0;
        int l = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (l = 1; l <= area; l++) {
            if (area % l == 0 && l >= area / l) {
                w = area / l;
                map.put(l, w);
            }
        }
        int[] arr = new int[2];
        int count = area;
        Set<Integer> set = map.keySet();
        for (Integer i : set) {
            if (count >= Math.abs(i - map.get(i))) {
                arr[0] = i;
                arr[1] = map.get(i);
                count = Math.abs(i - map.get(i));
            }
        }
        return arr;
    }

    /**
     * 方法二：开方向下取
     */
    public static int[] solution2(int area) {
        int sqrt = (int) Math.sqrt(area);
        while (area % sqrt != 0) {
            sqrt--;
        }
        return new int[]{area / sqrt, sqrt};
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(4) = " + ConstructRectangle.solution1(4));
        LeetCodeUtil.logln("solution1(6) = " + ConstructRectangle.solution1(6));
        LeetCodeUtil.logln("solution2(4) = " + ConstructRectangle.solution2(4));
        LeetCodeUtil.logln("solution2(6) = " + ConstructRectangle.solution2(6));
    }
}
