package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 482.密钥格式化
 * 有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。
 * 其中， N 个 '-' 将字符串分成了 N+1 组。
 * <p>
 * 给你一个数字 K，请你重新格式化字符串，除了第一个分组以外，
 * 每个分组要包含 K 个字符；而第一个分组中，至少要包含 1 个
 * 字符。两个分组之间需要用 '-'（破折号）隔开，并且将所有的
 * 小写字母转换为大写字母。
 * <p>
 * 给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。
 * <p>
 *【示例】
 * 示例 1：
 * 输入：S = "5F3Z-2e-9-w", K = 4
 * 输出："5F3Z-2E9W"
 * 解释：字符串 S 被分成了两个部分，每部分 4 个字符；
 *      注意，两个额外的破折号需要删掉。
 *
 * 示例 2：
 * 输入：S = "2-5g-3-J", K = 2
 * 输出："2-5G-3J"
 * 解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，
 * 第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
 *【提示】
 * 1.S 的长度可能很长，请按需分配大小。K 为正整数。
 * 2.S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-'
 * 3.S 非空
 *
 * @author liwangcheng
 * @date 2020/5/17.
 */
public class LicenseKeyFormatting {

    /**
     * 方法一：借助长度对K取余数
     * 关键点在于确定第一个分组的字符串，借助长度对K取余数
     * 思路
     * 1.根据“-”将字符串分割并拼接成一个全大写的字符串。
     * 2.s.length对K取余计算出头分组的长度（保证后边的分组长度等于K）。
     * 3.以+4为循环新增并拼接结果字符串。
     * 参考：
     * https://leetcode-cn.com/problems/license-key-formatting/solution/guan-jian-dian-zai-yu-que-ding-di-yi-ge-fen-zu-de-/
     */
    public static String solution1(String S, int K) {
        StringBuilder builder = new StringBuilder();
        StringBuilder res = new StringBuilder();
        String[] split = S.split("-");
        for (String s : split) {
            builder.append(s.toUpperCase());
        }
        // 第一个分组的位数
        int i = builder.length() % K;
        if (i != 0) {
            res.append(builder.substring(0, i));
        }
        for (int j = i, len = builder.length(); j < len - K + 1; j+=K) {
            if (res.length() > 0) {
                res.append("-");
            }
            res.append(builder.substring(j, j+K));
        }
        return res.toString();
    }

    /**
     * 方法二：逆序遍历
     */
    public static String solution2(String S, int K) {
        char[] chars = S.toCharArray();
        char[] result = new char[S.length() + S.length()/K];
        int length = 0;
        int i = chars.length - 1;
        int j = result.length - 1;
        for (; i >= 0;) {
            if (chars[i] == '-') {
                i--;
                continue;
            }
            if (chars[i]>='a' && chars[i]<='z') {
                chars[i] -= 32;
            }
            if (length != K) {
                result[j] = chars[i];
                length++;
                if (i == 0) {
                    j--;
                    break;
                } else {
                    i--;
                    j--;
                }
            } else {
                length = 1;
                result[j] = '-';
                result[j - 1] = chars[i];
                j -= 2;
                i--;
            }
        }
        return new String(result, j+1, result.length-j-1);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(5F3Z-2e-9-w, 4) = " + LicenseKeyFormatting.solution1("5F3Z-2e-9-w", 4));
        LeetCodeUtil.logln("solution2(5F3Z-2e-9-w, 4) = " + LicenseKeyFormatting.solution2("5F3Z-2e-9-w", 4));
        LeetCodeUtil.logln("solution1(2-5g-3-J, 2) = " + LicenseKeyFormatting.solution1("2-5g-3-J", 2));
        LeetCodeUtil.logln("solution2(2-5g-3-J, 2) = " + LicenseKeyFormatting.solution2("2-5g-3-J", 2));
    }
}
