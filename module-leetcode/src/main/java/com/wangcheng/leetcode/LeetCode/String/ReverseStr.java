package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 541.反转字符串 II
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的
 * 每隔 2k 个字符的前 k 个字符进行反转。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，
 * 其余字符保持原样。
 * <p>
 *【示例】
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 *【提示】
 * 该字符串只包含小写英文字母。
 * 给定字符串的长度和 k 在 [1, 10000] 范围内。
 *
 * @author liwangcheng
 * @date 2020/5/23.
 */
public class ReverseStr {

    /**
     * 暴力
     */
    public static String solution(String s, int k) {
        char[] chars = s.toCharArray();
        for (int start = 0; start < chars.length; start += 2 * k) {
            int i = start;
            int j = Math.min(start + k - 1, chars.length - 1);
            while (i < j) {
                char tmp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = tmp;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(abcdefg, 2) = " + ReverseStr.solution("abcdefg", 2));
    }
}
