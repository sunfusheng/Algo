package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 171.Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * 例如，
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * <p>
 *【示例】
 * 示例 1:
 * 输入: "A"
 * 输出: 1
 *
 * 示例 2:
 * 输入: "AB"
 * 输出: 28
 *
 * 示例 3:
 * 输入: "ZY"
 * 输出: 701
 *
 * @author liwangcheng
 * @date 2020/4/9.
 */
public class TitleToNumber {

    public static int solution(String str) {
        if (null == str || str.isEmpty()) {
            return 0;
        }
        int ans = 0;
        for (int i = 0, size = str.length(); i < size; i++) {
            int num = str.charAt(i) - 'A' + 1;
            ans = ans*26 + num;
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(A) = " + TitleToNumber.solution("A"));
        LeetCodeUtil.logln("solution(AB) = " + TitleToNumber.solution("AB"));
        LeetCodeUtil.logln("solution(ZY) = " + TitleToNumber.solution("ZY"));
    }
}
