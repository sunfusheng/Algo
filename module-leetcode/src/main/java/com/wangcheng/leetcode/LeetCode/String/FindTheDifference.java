package com.wangcheng.leetcode.LeetCode.String;

/**
 *【题目】
 * 389.找不同
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 *【示例】
 * 输入：
 * s = "abcd"
 * t = "abcde"
 * 输出：
 * e
 * 解释：
 * 'e' 是那个被添加的字母。
 *
 * @author liwangcheng
 * @date 2020/5/7.
 */
public class FindTheDifference {

    /**
     * 方法一：异或位运算
     */
    public static char solution1(String s, String t) {
        char a = 0;
        for (char ch : s.toCharArray()) {
            a ^= ch;
        }
        for (char ch : t.toCharArray()) {
            a ^= ch;
        }
        return a;
    }

    /**
     * 方法二：异或位运算
     */
    public static char solution2(String s, String t) {
        char res = 0;
        int lens = s.length();
        for (int i = 0; i < lens; i ++) {
            res ^= s.charAt(i) ^ t.charAt(i);
        }
        res ^= t.charAt(lens);
        return res;
    }

    /**
     * 方法三：数组记录
     */
    public static char solution3(String s, String t) {
        int[] record = new int[26];
        int lenS = s.length();
        int lenT = t.length();
        for (int i = 0; i < lenS; i++) {
            record[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < lenT; i++) {
            int tmp = t.charAt(i) - 'a';
            record[tmp]--;
            if (record[tmp] < 0) {
                return t.charAt(i);
            }
        }
        return ' ';
    }
}
