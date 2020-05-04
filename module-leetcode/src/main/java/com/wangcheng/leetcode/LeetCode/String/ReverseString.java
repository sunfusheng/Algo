package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 344.反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。
 * 输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、
 * 使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 *【示例】
 * 示例 1：
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * 示例 2：
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 * @author liwangcheng
 * @date 2020/5/4.
 */
public class ReverseString {

    /**
     * 方法一：递归
     * 算法：
     * 实现递归函数 helper，它接受两个参数：left 左指针和 right 右指针。
     *
     * - 如果 left>=right，不做任何操作。
     * - 否则交换 s[left] 和 s[right] 和调用 helper(left + 1, right - 1)。
     * - 首次调用函数传递首尾指针反转整个字符串 return helper(0, len(s) - 1)。
     *
     * 复杂度分析
     * 时间复杂度：O(N)。执行了 N/2 次的交换。
     * 空间复杂度：O(N)，递归过程中使用的堆栈空间。
     */
    public static char[] solution1(char[] str) {
        if (LeetCodeUtil.isEmpty(str)) {
            return str;
        }
        helper(str, 0, str.length - 1);
        return str;
    }

    private static void helper(char[] str, int left, int right) {
        if (left >= right) {
            return;
        }
        char temp = str[left];
        str[left++] = str[right];
        str[right--] = temp;
        helper(str, left, right);
    }

    /**
     * 方法二：双指针法
     * 双指针法是使用两个指针，一个左指针 left，右指针 right，
     * 开始工作时 left 指向首元素，right 指向尾元素。交换两个
     * 指针指向的元素，并向中间移动，直到两个指针相遇。
     *
     * 算法：
     * - 将 left 指向首元素，right 指向尾元素。
     * - 当 left<right：
     *  - 交换 s[left] 和 s[right]。
     *  - left++
     *  - right++
     *
     * 复杂度分析
     * 时间复杂度：O(N)。执行了 N/2 次的交换。
     * 空间复杂度：O(1)。只使用了常数级空间。
     */
    public static char[] solution2(char[] str) {
        if (LeetCodeUtil.isEmpty(str)) {
            return str;
        }
        int left = 0;
        int right = str.length - 1;
        char temp;
        while (left < right) {
            temp = str[left];
            str[left++] = str[right];
            str[right--] = temp;
        }
        return str;
    }

    public static void main(String[] args) {
        char[] str = "Hello,World".toCharArray();
        LeetCodeUtil.logln("solution1() = " + ReverseString.solution1(str));
        LeetCodeUtil.logln("solution2() = " + ReverseString.solution2(str));
    }
}
