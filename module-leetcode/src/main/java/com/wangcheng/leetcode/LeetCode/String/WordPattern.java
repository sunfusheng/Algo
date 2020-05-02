package com.wangcheng.leetcode.LeetCode.String;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *【题目】
 * 290.单词规律
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，
 * 例如， 
 * pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 【示例】
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 【说明】
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 *
 * @author liwangcheng
 * @date 2020/5/2.
 */
public class WordPattern {

    public static boolean solution1(String pattern, String str) {
        String[] arr = str.split(" ");
        if (pattern.length() != arr.length) {
            return false;
        }
        int len = pattern.length();
        for (int i = 0; i < len; i++) {
            if (pattern.indexOf(pattern.charAt(i)) != indexOf(arr, arr[i])) {
                return false;
            }
        }
        return true;
    }

    private static int indexOf(String[] arrays, String searchString) {
        int ans = -1;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].equals(searchString)) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static boolean solution2(String pattern, String str) {
        String[] array1 = str.split(" ");
        if (array1.length != pattern.length()) {
            return false;
        }
        String[] array2 = pattern.split("");
        // 两个方向的映射
        return wordPatternHelper(array1, array2) && wordPatternHelper(array2, array1);
    }

    /**
     * array1 到 array2 的映射
     */
    private static boolean wordPatternHelper(String[] array1, String[] array2) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            String key = array1[i];
            if (map.containsKey(key)) {
                if (!map.get(key).equals(array2[i])) {
                    return false;
                }
            } else {
                map.put(key, array2[i]);
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean solution3(String pattern, String str) {
        String[] strArr = str.split(" ");
        if (strArr.length != pattern.length()) {
            return false;
        }
        char[] charArr = pattern.toCharArray();
        Map<String, Integer> strMap = new HashMap<>();
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            String s = strArr[i];
            char ch = charArr[i];
            // 当前的映射值是否相同
            int a = strMap.getOrDefault(s, -1);
            int b = charMap.getOrDefault(ch, -1);
            if (a != b) {
                return false;
            } else {
                strMap.put(s, i);
                charMap.put(ch, i);
            }
        }
        return true;
    }

    public static boolean solution4(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            if (!Objects.equals(index.put(String.valueOf(pattern.charAt(i)), i),
                    index.put(words[i], i))) {
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1() = " + WordPattern.solution1("abba", "dog cat cat dog"));
        LeetCodeUtil.logln("solution1() = " + WordPattern.solution1("abba", "dog cat cat fish"));
        LeetCodeUtil.logln("solution1() = " + WordPattern.solution1("aaaa", "dog cat cat dog"));
        LeetCodeUtil.logln("solution1() = " + WordPattern.solution1("abba", "dog dog dog dog"));
        LeetCodeUtil.logln("solution2() = " + WordPattern.solution2("abba", "dog cat cat dog"));
        LeetCodeUtil.logln("solution2() = " + WordPattern.solution2("abba", "dog cat cat fish"));
        LeetCodeUtil.logln("solution2() = " + WordPattern.solution2("aaaa", "dog cat cat dog"));
        LeetCodeUtil.logln("solution2() = " + WordPattern.solution2("abba", "dog dog dog dog"));
        LeetCodeUtil.logln("solution3() = " + WordPattern.solution3("abba", "dog cat cat dog"));
        LeetCodeUtil.logln("solution3() = " + WordPattern.solution3("abba", "dog cat cat fish"));
        LeetCodeUtil.logln("solution3() = " + WordPattern.solution3("aaaa", "dog cat cat dog"));
        LeetCodeUtil.logln("solution3() = " + WordPattern.solution3("abba", "dog dog dog dog"));
        LeetCodeUtil.logln("solution4() = " + WordPattern.solution4("abba", "dog cat cat dog"));
        LeetCodeUtil.logln("solution4() = " + WordPattern.solution4("abba", "dog cat cat fish"));
        LeetCodeUtil.logln("solution4() = " + WordPattern.solution4("aaaa", "dog cat cat dog"));
        LeetCodeUtil.logln("solution4() = " + WordPattern.solution4("abba", "dog dog dog dog"));
    }
}
