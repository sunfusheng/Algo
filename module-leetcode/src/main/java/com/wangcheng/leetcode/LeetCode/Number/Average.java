package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * Java求两个数平均值
 * 注意：
 * 两个数相加有可能超过了int的范围，但是他们的平均值肯定不会超过范围
 *
 * @author liwangcheng
 * @date 2020/5/1.
 */
public class Average {

    /**
     * 错误示例
     */
    public static int solution(int a, int b) {
        return (a + b) / 2;
    }

    /**
     * 正确写法
     */
    public static int solution1(int a, int b) {
        return ((a&b) + ((a^b) >> 1));
    }

    public static int solution2(int a, int b) {
        return b+(a-b)/2;
    }

    public static int solution3(int a, int b) {
        return b+((a-b)>>1);
    }

    public static int solution4(int a, int b) {
        return (a+b)>>>1;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution() = " + Average.solution(Integer.MAX_VALUE, Integer.MAX_VALUE-19));
        LeetCodeUtil.logln("solution1() = " + Average.solution1(Integer.MAX_VALUE, Integer.MAX_VALUE-19));
        LeetCodeUtil.logln("solution2() = " + Average.solution2(Integer.MAX_VALUE, Integer.MAX_VALUE-19));
        LeetCodeUtil.logln("solution3() = " + Average.solution3(Integer.MAX_VALUE, Integer.MAX_VALUE-19));
        LeetCodeUtil.logln("solution4() = " + Average.solution4(Integer.MAX_VALUE, Integer.MAX_VALUE-19));
    }
}
