package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashSet;
import java.util.Set;

/**
 *【题目】
 * 345.反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *【示例】
 * 示例 1:
 * 输入: "hello"
 * 输出: "holle"
 *
 * 示例 2:
 * 输入: "leetcode"
 * 输出: "leotcede"
 *【说明】
 * 元音字母 a、e、i、o、u，不包含字母"y"。
 *
 * @author liwangcheng
 * @date 2020/5/4.
 */
public class ReverseVowels {

    private static final Set<Character> VOWEL_SET = new HashSet<>();
    static {
        VOWEL_SET.add('a');
        VOWEL_SET.add('e');
        VOWEL_SET.add('i');
        VOWEL_SET.add('o');
        VOWEL_SET.add('u');
        VOWEL_SET.add('A');
        VOWEL_SET.add('E');
        VOWEL_SET.add('I');
        VOWEL_SET.add('O');
        VOWEL_SET.add('U');
    }
    public static String solution1(String str) {
        if (LeetCodeUtil.isEmpty(str)) {
            return str;
        }
        char[] charArr = str.toCharArray();
        int left = 0;
        int right = charArr.length - 1;
        char temp;
        while (left < right) {
            char l = charArr[left];
            char r = charArr[right];
            if (!isVowel(l)) {
                left++;
                continue;
            }
            if (!isVowel(r)) {
                right--;
                continue;
            }
            temp = l;
            charArr[left++] = r;
            charArr[right--] = temp;
        }
        return String.valueOf(charArr);
    }

    private static boolean isVowel(char ch) {
        return VOWEL_SET.contains(ch);
    }

    public static String solution2(String s) {
        if(s.length() < 2)  {
            return s;
        }
        char[] chars = s.toCharArray();
        int i= 0;
        int j = chars.length -1 ;
        while (i < j) {
            if (isVowel(chars[i])) {
                if (isVowel(chars[j])) {
                    if (chars[i] != chars[j]) {
                        char temp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = temp;
                    }
                    ++i;
                    --j;
                } else {
                    --j;
                }
            } else {
                ++i;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(hello) = " + ReverseVowels.solution1("hello"));
        LeetCodeUtil.logln("solution1(leetcode) = " + ReverseVowels.solution1("leetcode"));
        LeetCodeUtil.logln("solution2(hello) = " + ReverseVowels.solution2("hello"));
        LeetCodeUtil.logln("solution2(leetcode) = " + ReverseVowels.solution2("leetcode"));
    }
}
