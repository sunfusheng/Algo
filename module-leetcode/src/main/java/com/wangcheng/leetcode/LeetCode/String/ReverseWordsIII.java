package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *【题目】
 * 557.反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，
 * 同时仍保留空格和单词的初始顺序。
 *【示例】
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 *【注意】
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有
 * 任何额外的空格。
 *
 * @author liwangcheng
 * @date 2020/5/26.
 */
public class ReverseWordsIII {

    /**
     * 方法 1：简单的解法
     * 将输入字符串中按照空白字符串分开，然后把所有单词
     * 放到一个字符串列表中，然后逐一遍历每一个字符串并
     * 把反转结果连接起来。最后，将删除了额外空白字符的
     * 字符串返回。
     *
     * 复杂度分析
     * 时间复杂度：O(n) 。其中 n 是字符串的长度。
     * 空间复杂度：O(n) 。使用了大小为 n 的 res。
     */
    public static String solution1(String str) {
        String[] words = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            builder.append(new StringBuilder(words[i]).reverse())
                    .append(" ");
        }
        return builder.toString().trim();
    }

    /**
     * 方法 2：不使用自带的 split 和 reverse
     * 可以自己写一个 split 和 reverse 函数。 split 函数
     * 将字符串按照 " " （空格）为分隔符将字符串分开并返回单
     * 词列表。 reverse 函数返回每个字符串反转后的字符串。
     *
     * 复杂度分析
     * 时间复杂度：O(n) 。其中 n 是字符串的长度。
     * 空间复杂度：O(n) 。使用了大小为 n 的 res。
     */
    public static String solution2(String str) {
        String[] words = split(str);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            builder.append(reverse(words[i])).append(" ");
        }
        return builder.toString();
    }

    private static String[] split(String str) {
        List<String> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                tmp.append(chars[i]);
            } else {
                list.add(tmp.toString());
                tmp.setLength(0);
            }
        }
        list.add(tmp.toString());
        String[] words = new String[list.size()];
        return list.toArray(words);
    }

    private static String reverse(String word) {
        StringBuilder builder = new StringBuilder();
        char[] chars = word.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            builder.append(chars[i]);
        }
        return builder.toString();
    }

    /**
     * 方法 3：使用 StringBuilder 和 reverse 方法
     * 这一方法中，不使用 split 方法，创建临时字符串 word 保存单词，
     * 在遍历过程中将字符逐一连接在 word 后面，直到遇到 ' '（空格）
     * 字符。当遇到 ' ' 字符时，将 word 反转后连接在结果字符串 result
     * 后面。在遍历完成以后，返回结果字符串 result。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。单遍循环的上限是 n，其中 n 是字符串的长度。
     * 空间复杂度：O(n)。result 和 word 最多为 n。
     */
    public static String solution3(String str) {
        final StringBuilder result = new StringBuilder();
        final StringBuilder word = new StringBuilder();
        for (int i = 0, len = str.length(); i < len; i++) {
            if (str.charAt(i) != ' ') {
                word.append(str.charAt(i));
            } else {
                result.append(word.reverse());
                result.append(" ");
                word.setLength(0);
            }
        }
        result.append(word.reverse());
        return result.toString();
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(Let's take LeetCode contest) = " + ReverseWordsIII.solution1("Let's take LeetCode contest"));
        LeetCodeUtil.logln("solution2(Let's take LeetCode contest) = " + ReverseWordsIII.solution2("Let's take LeetCode contest"));
        LeetCodeUtil.logln("solution3(Let's take LeetCode contest) = " + ReverseWordsIII.solution3("Let's take LeetCode contest"));
    }
}
