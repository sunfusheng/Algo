package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 58.最后一个单词的长度
 * 给定一个仅包含大小写字母和空格' '的字符串s，
 * 返回其最后一个单词的长度。如果字符串从左向右滚动显示，
 * 那么最后一个单词就是最后出现的单词。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 * <p>
 *【示例】
 * 输入: "Hello World"
 * 输出: 5
 *
 * @author liwangcheng
 * @date 2020/3/24.
 */
public class LengthOfLastWord {

    private static final char SPACE = ' ';

    public static int solution(String str) {
        if (null == str || str.isEmpty()) {
            return 0;
        }
        int end = str.length() - 1;
        while(end >= 0 && str.charAt(end) == SPACE) {
            end--;
        }
        if(end < 0) {
            return 0;
        }
        int start = end;
        while(start >= 0 && str.charAt(start) != SPACE) {
            start--;
        }
        return end - start;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(hello world) = " + LengthOfLastWord.solution("hello world"));
        LeetCodeUtil.logln("solution(hello ) = " + LengthOfLastWord.solution("hello "));
        LeetCodeUtil.logln("solution(hello q) = " + LengthOfLastWord.solution("hello o"));
    }
}
