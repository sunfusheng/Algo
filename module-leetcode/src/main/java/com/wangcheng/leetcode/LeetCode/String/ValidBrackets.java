package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Stack;

/**
 * 【题目】
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 【示例】
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * @author liwangcheng
 * @date 2020/3/19.
 */
public class ValidBrackets {

    private static final char[] OPEN_ARR = new char[]{'(', '[', '{'};
    private static final char[] CLOSE_ARR = new char[]{')', ']', '}'};

    /**
     * 思路
     * 借助栈结构特性，遇到开括号压栈，闭括号弹栈，到最后判断栈是否为空
     * @param str
     * @return
     */
    public static boolean solution(String str) {
        if (null == str || str.isEmpty()) {
            return false;
        }
        if (indexOpenBracket(str.charAt(0)) < 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0, len = str.length(); i < len; i++) {
            char ch = str.charAt(i);
            if (indexOpenBracket(ch) >= 0) {
                stack.push(ch);
            } else if (indexCloseBracket(ch) >= 0) {
                char topCh = stack.pop();
                if (indexOpenBracket(topCh) != indexCloseBracket(ch)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private static int indexOpenBracket(char ch) {
        for (int i = 0; i < OPEN_ARR.length; i++) {
            if (ch == OPEN_ARR[i]) {
                return i;
            }
        }
        return -1;
    }

    private static int indexCloseBracket(char ch) {
        for (int i = 0; i < CLOSE_ARR.length; i++) {
            if (ch == CLOSE_ARR[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(')(') = " + ValidBrackets.solution(")("));
        LeetCodeUtil.logln("solution('()') = " + ValidBrackets.solution("()"));
        LeetCodeUtil.logln("solution('([{}])') = " + ValidBrackets.solution("([{}])"));
        LeetCodeUtil.logln("solution('([)') = " + ValidBrackets.solution("([)"));
        LeetCodeUtil.logln("solution('(])') = " + ValidBrackets.solution("(])"));
        LeetCodeUtil.logln("solution('({])') = " + ValidBrackets.solution("({])"));
    }
}
