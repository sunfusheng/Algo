package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 504.七进制数
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 *【示例】
 * 示例 1:
 * 输入: 100
 * 输出: "202"
 *
 * 示例 2:
 * 输入: -7
 * 输出: "-10"
 *【注意】
 * 输入范围是 [-1e7, 1e7] 。
 *
 * @author liwangcheng
 * @date 2020/5/19.
 */
public class ConvertToBase7 {

    public static String solution(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        String pre = "";
        if (num < 0) {
            pre = "-";
            num = 0 - num;
        }
        while (num != 0) {
            builder.append(num % 7);
            num /= 7;
        }
        return pre + builder.reverse().toString();
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(100) = " + ConvertToBase7.solution(100));
        LeetCodeUtil.logln("solution(-7) = " + ConvertToBase7.solution(-7));
    }
}
