package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 125.验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，将空字符串定义为有效的回文串。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 * @author liwangcheng
 * @date 2020/4/6.
 */
public class IsPalindrome {

    public static boolean solution(String str) {
        if (null == str || str.isEmpty()) {
            return true;
        }
        str = str.toLowerCase();
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            char lC = str.charAt(left);
            char rC = str.charAt(right);
            if (isDigitalOrLetter(lC) && isDigitalOrLetter(rC)) {
                if (lC == rC) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            } else if (!isDigitalOrLetter(lC)) {
                left++;
            } else if (!isDigitalOrLetter(rC)) {
                right--;
            }
        }
        return true;
    }

    private static boolean isDigitalOrLetter(char ch) {
        return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z');
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(A man, a plan, a canal: Panama) = " + IsPalindrome.solution("A man, a plan, a canal: Panama"));
        LeetCodeUtil.logln("solution(race a car) = " + IsPalindrome.solution("race a car"));
    }
}
