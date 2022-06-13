package com.wangcheng.leetcode.LeetCode.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17.电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 1 -> ""
 * 2 -> "abc"
 * 3 -> "def"
 * 4 -> "ghi"
 * 5 -> "jkl"
 * 6 -> "mno"
 * 7 -> "pqrs"
 * 8 -> "tuv"
 * 9 -> "wxyz"
 * <p>
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * @author sunfusheng
 * @since 2022/06/13
 */
public class LetterCombinations {

    /**
     * 思想：回溯法
     * <p>
     * 时间复杂度：O(3^m * 4^n)
     * 空间复杂度：O(m+n)
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        helper(map, digits, 0, new StringBuilder(), res);
        return res;
    }

    private void helper(Map<Character, String> map, String digits, int index,
                        StringBuilder path, List<String> res) {
        if (index == digits.length()) {
            res.add(path.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = map.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                path.append(letters.charAt(i));
                helper(map, digits, index + 1, path, res);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}
