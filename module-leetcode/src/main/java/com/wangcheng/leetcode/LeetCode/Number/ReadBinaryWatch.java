package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *【题目】
 * 401.二进制手表
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * <p>
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * <p>
 * 案例:
 * <p>
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * <p>
 *
 *【注意事项】
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 *
 * @author liwangcheng
 * @date 2020/5/8.
 */
public class ReadBinaryWatch {

    /**
     * 方法一：暴力破解
     * 直接遍历 0:00 -> 12:00 每个时间有多少1
     * PS：二进制中为1，则LED灯亮
     * 参考：
     * https://leetcode-cn.com/problems/binary-watch/solution/cjian-jian-dan-dan-de-ji-xing-dai-ma-jie-jue-wen-t/
     */
    public static List<String> solution1(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (count1(i) + count1(j) == num) {
                    list.add(i + ":" + (j < 10 ? "0" : "") + j);
                }
            }
        }
        return list;
    }

    private static int count1(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            res++;
        }
        return res;
    }

    /**
     * TODO
     * 方法二：回溯算法
     * 参考：
     * https://leetcode-cn.com/problems/binary-watch/solution/czong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-d/
     *
     * 解题步骤：
     * 1.读懂题意，把题目尽可能抽象成“子集、排列、组合”类型问题
     * 2.回溯六步走
     * ①画出递归树，找到状态变量(回溯函数的参数)，这一步非常重要※
     * ②根据题意，确立结束条件
     * ③找准选择列表(与函数参数相关),与第一步紧密关联※
     * ④判断是否需要剪枝
     * ⑤作出选择，递归调用，进入下一层
     * ⑥撤销选择
     */
    public static List<String> solution2(int num) {
        List<String> res = new LinkedList<>();
        dfs(num, res, 0, 0, 1, 1, new LinkedList<Integer>(), new LinkedList<Integer>());
        return res;
    }

    /**
     * h：小时数，m：分钟数
     * hstart、mstart：从hstart、mstart开始继续遍历小时和分钟
     * hours：选中的小时，minutes：选中的分钟
     */
    private static void dfs(int num, List<String> res, int h, int m, int hstart, int mstart, LinkedList<Integer> hours, LinkedList<Integer> minutes) {
        if (hours.size() + minutes.size() == num) {
            if (h < 12 && m < 60) {
                res.add(String.format("%d:%02d", h, m));
            }
            return;
        }
        for (int i = hstart; i <= 8; i <<= 1) {
            hours.addLast(i);
            dfs(num, res, h + i, m, i << 1, mstart, hours, minutes);
            hours.removeLast();
        }
        for (int i = mstart; i <= 32; i <<= 1) {
            minutes.addLast(i);
            // hstart 直接设置为16（>=12)，避免重复计算
            dfs(num, res, h, m + i, 16, i << 1, hours, minutes);
            minutes.removeLast();
        }
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(1) = " + ReadBinaryWatch.solution1(1));
        LeetCodeUtil.logln("solution2(1) = " + ReadBinaryWatch.solution2(1));
    }
}
