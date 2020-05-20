package com.wangcheng.leetcode.LeetCode.String;

/**
 * 【题目】
 * 8.字符串转换整数 (atoi)
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
 * 则你的函数不需要进行转换，即无法进行有效转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * <p>
 * 提示：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
 * 如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
 * <p>
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 * <p>
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * <p>
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * <p>
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * <p>
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−2^31) 。
 *
 * @author sunfusheng
 * @since 2020/5/20
 */
public class MyAtoi {

    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int len = chars.length;
        int index = 0;
        while (index < len && chars[index] == ' ') {
            index++;
        }
        if (index == len) {
            return 0;
        }

        boolean negative = false;
        if (chars[index] == '-') {
            negative = true;
            index++;
        } else if (chars[index] == '+') {
            index++;
        } else if (!Character.isDigit(chars[index])) {
            return 0;
        }

        int res = 0;
        while (index < len && Character.isDigit(chars[index])) {
            int digit = chars[index] - '0';
            if (res > (Integer.MAX_VALUE - digit) / 10) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + digit;
            index++;
        }
        return negative ? -res : res;
    }

    public static void main(String[] args) {
        String str = "42";
        System.out.println("输入1：" + str);
        System.out.println("输出1：" + myAtoi(str));
        str = "   -42";
        System.out.println("输入2：" + str);
        System.out.println("输出2：" + myAtoi(str));
        str = "4193 with words";
        System.out.println("输入3：" + str);
        System.out.println("输出3：" + myAtoi(str));
        str = "words and 987";
        System.out.println("输入4：" + str);
        System.out.println("输出4：" + myAtoi(str));
        str = "91283472332";
        System.out.println("输入5：" + str);
        System.out.println("输出5：" + myAtoi(str));
        str = "-91283472332";
        System.out.println("输入6：" + str);
        System.out.println("输出6：" + myAtoi(str));
    }
}
