package com.wangcheng.leetcode.LeetCode.String;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 151.翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <p>
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * @author sunfusheng
 * @since 2020/5/14
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class ReverseWords {

    /**
     * 方法一：使用系统函数
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        if (s == null || s.trim().length() == 0) {
            return "";
        }

        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * 方法二：双端队列
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param s
     * @return
     */
    public static String reverseWords2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int left = 0;
        int right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') left++;
        while (right > left && s.charAt(right) == ' ') right--;

        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (word.length() != 0 && c == ' ') {
                deque.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            left++;
        }
        deque.offerFirst(word.toString());
        return String.join(" ", deque);
    }

    public static void main(String[] args) {
        String s1 = "the sky is blue";
        String s2 = "  hello world!  ";
        String s3 = "a good   example";

        System.out.println(s1 + " 翻转后1：" + reverseWords(s1));
        System.out.println(s2 + " 翻转后1：" + reverseWords(s2));
        System.out.println(s3 + " 翻转后1：" + reverseWords(s3));

        System.out.println(s1 + " 翻转后2：" + reverseWords2(s1));
        System.out.println(s2 + " 翻转后2：" + reverseWords2(s2));
        System.out.println(s3 + " 翻转后2：" + reverseWords2(s3));
    }
}
