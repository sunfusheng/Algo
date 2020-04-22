package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *【题目】
 * 205.同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。
 * 两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 *
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 * @author liwangcheng
 * @date 2020/4/22.
 */
public class IsIsomorphic {

    /**
     * 方法一
     * 需要验证 s - > t 和 t -> s 两个方向。
     * 如果验证一个方向，是不可以的。
     * 举个例子，s = ab, t = cc，如果单看 s -> t ，那么 a -> c, b -> c 是没有问题的。
     */
    public static boolean solution1(String s, String t) {
        if (LeetCodeUtil.length(s) != LeetCodeUtil.length(t)) {
            return false;
        }
        return helper1(s, t) && helper1(t, s);
    }

    private static boolean helper1(String s, String t) {
        int n = s.length();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (map.containsKey(ch1)) {
                if (!map.get(ch1).equals(ch2)) {
                    return false;
                }
            } else {
                map.put(ch1, ch2);
            }
        }
        return true;
    }

    /**
     * 方法二
     * 找一个第三方来解决，即，按照字母出现的顺序，把两个字符串都映射到另一个集合中。
     * 举个现实生活中的例子：
     * 一个人说中文，一个人说法语，怎么判断他们说的是一个意思呢？
     * 把中文翻译成英语，把法语也翻译成英语，然后看最后的英语是否相同即可。
     */
    public static boolean solution2(String s, String t) {
        if (LeetCodeUtil.length(s) != LeetCodeUtil.length(t)) {
            return false;
        }
        return helper2(s).equals(helper2(t));
    }

    private static String helper2(String s) {
        int[] map = new int[128];
        int n = s.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (map[ch] != 0) {
                builder.append(map[ch]);
            } else {
                map[ch] = i + 1;
            }
        }
        return builder.toString();
    }

    /**
     * 方法三
     * 其实不需要将字符串完全转换，可以用两个 map 分别记录两个字符串每个字母的映射。
     * 将所有字母初始都映射到 0。记录过程中，如果发现了当前映射不一致，就可以立即返回 false 了。
     */
    public static boolean solution3(String s, String t) {
        if (LeetCodeUtil.length(s) != LeetCodeUtil.length(t)) {
            return false;
        }
        int[] map1 = new int[128];
        int[] map2 = new int[128];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (map1[ch1] != map2[ch2]) {
                return false;
            } else {
                if (map1[ch1] == 0) {
                    map1[ch1] = i + 1;
                    map2[ch2] = i + 1;
                }
            }
        }
        return true;
    }

}
