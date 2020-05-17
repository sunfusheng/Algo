package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 459.重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
 * 给定的字符串只含有小写英文字母，并且长度不超过10000。
 *【示例】
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 *
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 *
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 *
 * @author liwangcheng
 * @date 2020/5/13.
 */
public class RepeatedSubstringPattern {

    /**
     * 1.将原字符串给出拷贝一遍组成新字符串；
     * 2.掐头去尾留中间；
     * 3.如果还包含原字符串，则满足题意。
     * 参考：
     * https://leetcode-cn.com/problems/repeated-substring-pattern/solution/jian-dan-ming-liao-guan-yu-javaliang-xing-dai-ma-s/
     */
    public static boolean solution(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(abab) = " + RepeatedSubstringPattern.solution("abab"));
        LeetCodeUtil.logln("solution(aba) = " + RepeatedSubstringPattern.solution("aba"));
        LeetCodeUtil.logln("solution(abcabcabcabc) = " + RepeatedSubstringPattern.solution("abcabcabcabc"));
    }
}