package com.wangcheng.leetcode.LeetCode.String;

/**
 * 8.字符串转换整数 (atoi)
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * 1、读入字符串并丢弃无用的前导空格
 * 2、检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 3、读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 4、将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 5、如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。
 * 具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 2^31 − 1 。
 * 6、返回整数作为最终结果。
 * <p>
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 * <p>
 * 示例 1：
 * 输入：s = "42"
 * 输出：42
 * <p>
 * 示例 2：
 * 输入：s = "   -42"
 * 输出：-42
 * <p>
 * 示例 3：
 * 输入：s = "4193 with words"
 * 输出：4193
 * <p>
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * <p>
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * <p>
 * 提示：
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 *
 * @author sunfusheng
 * @since 2020/5/20
 */
public class MyAtoi {

    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
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
