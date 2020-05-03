package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 326.3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *【示例】
 * 示例 1:
 * 输入: 27
 * 输出: true
 *
 * 示例 2:
 * 输入: 0
 * 输出: false
 *
 * 示例 3:
 * 输入: 9
 * 输出: true
 *
 * 示例 4:
 * 输入: 45
 * 输出: false
 *【进阶】
 * 你能不使用循环或者递归来完成本题吗？
 *
 * @author liwangcheng
 * @date 2020/5/3.
 */
public class IsPowerOfThree {

    /**
     * 方法一：循环迭代
     * 复杂度分析
     *
     * 时间复杂度：O(log_b(n))，在例子中是 O(logn)。除数是用对数表示的。
     * 空间复杂度：O(1)，没有使用额外的空间。
     */
    public static boolean solution1(int num) {
        if (num < 1) {
            return false;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        return num == 1;
    }

    /**
     * 方法二：基准转换
     * 将数字转换为以3为底的基数 ，并检查它是否为前导1，后跟所有 0。
     *   String baseChange = Integer.toString(number, base);
     * 上面的代码将 number 转换以 base 为底的基数，并以字符串形式返回结果。
     * 例如，
     * - integer.toString（5，2）=“101”
     * - integer.toString（5，3）=“12”。
     * 检查字符串中是否存在特定的正则表达式
     * boolean powerOfThree = baseChange.matches("^10*$")
     * 使用上面的正则表达式来检查字符串是否以1 ^1 开头，后跟 0 或 多个 0
     * 0* 并且不包含任何其他值 $。
     *
     * 复杂度分析
     * 时间复杂度：O(log_3n)。
     * 假设：
     * Integer.toString() - 基转换通常是作为一个重复的除法来实现的。
     * 复杂性应该类似于方法 1:O（log_3n）的复杂性。
     * String.matches() - 方法迭代整个字符串。n 以 3 为基数表示的位数是O（log_3n）。
     * 空间复杂度：O(log_3n)。使用两个附加变量。
     * 以 3 为基数表示数字的字符串（大小为 log_3）正则表达式的字符串（常量大小）
     */
    public static boolean solution2(int num) {
        return Integer.toString(num, 3).matches("^10*$");
    }

    /**
     * TODO
     * 方法三：运算法
     *
     * 复杂度分析
     * 时间复杂度：UnknownUnknown。这里主要消耗时间的运算是 Math.log，
     *  它限制了算法的时间复杂性。实现依赖于使用的语言和编译器。
     * 空间复杂度： O(1)，没有使用任何额外的内存。epsilon 变量可以是内联的。
     */
    public static boolean solution3(int num) {
        double epsilon = 0.0000001;
        return (Math.log(num) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
    }

    /**
     * TODO
     * 方法四：整数限制
     * 参考：
     * https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode/
     *
     * 复杂度分析
     * 时间复杂度：O(1)。只做了一次操作。
     * 空间复杂度：O(1)。没有使用额外空间。
     */
    public static boolean solution4(int num) {
        return num > 0 && 1162261467 % num == 0;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(27) = " + IsPowerOfThree.solution1(27));
        LeetCodeUtil.logln("solution1(9) = " + IsPowerOfThree.solution1(9));
        LeetCodeUtil.logln("solution1(45) = " + IsPowerOfThree.solution1(45));
        LeetCodeUtil.logln("solution2(27) = " + IsPowerOfThree.solution2(27));
        LeetCodeUtil.logln("solution2(9) = " + IsPowerOfThree.solution2(9));
        LeetCodeUtil.logln("solution2(45) = " + IsPowerOfThree.solution2(45));
        LeetCodeUtil.logln("solution3(27) = " + IsPowerOfThree.solution3(27));
        LeetCodeUtil.logln("solution3(9) = " + IsPowerOfThree.solution3(9));
        LeetCodeUtil.logln("solution3(45) = " + IsPowerOfThree.solution3(45));
    }
}
