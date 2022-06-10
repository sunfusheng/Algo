package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 434.字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * <p>
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * <p>
 * 【示例】
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 *
 * @author liwangcheng
 * @date 2020/5/10.
 */
public class CountSegments {

    /**
     * 方法一：使用语言内置函数
     * <p>
     * 复杂度分析
     * 时间复杂度 : O(n)。
     * 这里用到的内置函数（无论是 Java 还是 Python）的时间复杂度或为 O(n)，
     * 或为 O(1) ，故整个算法可以在线性复杂度内完成。
     * 空间复杂度 : O(n)。
     * split 函数 (不管哪种语言) 返回长度为 O(n) 的数组/列表，
     * 故算法使用线性的额外空间。
     */
    public static int solution1(String str) {
        if (LeetCodeUtil.isEmpty(str)) {
            return 0;
        }
        str = str.trim();
        if (LeetCodeUtil.isEmpty(str)) {
            return 0;
        }
        String[] strArr = str.split("\\s+");
        return strArr.length;
    }

    /**
     * 方法二：原地法
     * 算法
     * 计算单词的数量，就等同于计数单词开始的下标个数。
     * 因此，只需要定义好下标的条件，就可以遍历整个字符串，
     * 检测每个下标。
     * 定义如下：若该下标前为空格（或者为初始下标），且自身不为空格，
     * 则其为单词开始的下标。该条件可以以常数时间检测。
     * 最后，返回满足条件的下标个数。
     * <p>
     * 复杂度分析
     * 时间复杂度: O(n)。
     * 对每个下标进行常数时间的检测。
     * 空间复杂度: O(1)。
     * 只使用了额外的几个整数，因此使用的空间为常数。
     */
    public static int solution2(String str) {
        if (LeetCodeUtil.isEmpty(str)) {
            return 0;
        }
        int segmentCount = 0;
        for (int i = 0, size = str.length(); i < size; i++) {
            if (str.charAt(i) != ' ' && (i == 0 || str.charAt(i - 1) == ' ')) {
                segmentCount++;
            }
        }
        return segmentCount;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(a) = " + solution1("a"));
        LeetCodeUtil.logln("solution1(Hello, qi ming) = " + solution1("Hello, qi ming"));
        LeetCodeUtil.logln("solution1(Hello, qi ming  ) = " + solution1("Hello, qi ming  "));
        LeetCodeUtil.logln("solution1(    foo    bar) = " + solution1("    foo    bar"));
        LeetCodeUtil.logln("solution2(a) = " + solution2("a"));
        LeetCodeUtil.logln("solution2(Hello, qi ming) = " + solution2("Hello, qi ming"));
        LeetCodeUtil.logln("solution2(Hello, qi ming  ) = " + solution2("Hello, qi ming  "));
        LeetCodeUtil.logln("solution2(    foo    bar) = " + solution2("    foo    bar"));
    }
}
