package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 405.数字转换为十六进制数
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，
 * 通常使用 补码运算 方法。
 *【注意】
 * 十六进制中所有字母(a-f)都必须是小写。
 * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，
 * 那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的
 * 第一个字符将不会是0字符。
 * 给定的数确保在32位有符号整数范围内。
 * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 *【示例】
 * 示例 1：
 * 输入:
 * 26
 * 输出:
 * "1a"
 *
 * 示例 2：
 * 输入:
 * -1
 * 输出:
 * "ffffffff"
 *
 * @author liwangcheng
 * @date 2020/5/8.
 */
public class ToHex {

    /**
     * 方法一：与运算+移位
     * 解题思路
     * 也可用于对2进制，8进制转换。
     * 1.维护相应的字符串2进制：“01”，8进制：“01234567”。
     * 2.修改相应的移位位数，16进制：4位，8进制：3位，2进制：1位
     *
     * 参考：
     * https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/solution/yu-yun-suan-yi-wei-jie-jian-ti-jie-da-lao-de-xiang/
     */
    public static String solution(int num) {
        if (num == 0){
            return "0";
        }
        String res = "";
        String hexString = "0123456789abcdef";
        while (num != 0 && res.length() < 8) {
            res = hexString.charAt(num&0xf) + res;
            num >>= 4;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(26) = " + ToHex.solution(26));
        LeetCodeUtil.logln("solution(-1) = " + ToHex.solution(-1));
    }
}
