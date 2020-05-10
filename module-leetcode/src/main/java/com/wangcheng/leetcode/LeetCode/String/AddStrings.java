package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 415.字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库，
 * 也不能直接将输入的字符串转换为整数形式。
 *
 * @author liwangcheng
 * @date 2020/5/10.
 */
public class AddStrings {

    /**
     * 双指针法
     */
    public static String solution(String str1, String str2) {
        if (LeetCodeUtil.isEmpty(str1)) {
            return str2;
        }
        if (LeetCodeUtil.isEmpty(str2)) {
            return str1;
        }
        StringBuilder res = new StringBuilder("");
        int carry = 0;
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? str1.charAt(i--) - '0' : 0;
            int n2 = j >= 0 ? str2.charAt(j--) - '0' : 0;
            carry += n1 + n2;
            res.append(carry % 10);
            carry /= 10;
        }
        if (carry > 0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(123, 456) = " + AddStrings.solution("123", "456"));
        LeetCodeUtil.logln("solution(789, 456) = " + AddStrings.solution("789", "456"));
    }
}
