package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 551.学生出勤记录 I
 * 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
 * 'A' : Absent，缺勤
 * 'L' : Late，迟到
 * 'P' : Present，到场
 * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),
 * 那么这个学生会被奖赏。
 * <p>
 * 你需要根据这个学生的出勤记录判断他是否会被奖赏。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: "PPALLP"
 * 输出: True
 *
 * 示例 2:
 * 输入: "PPALLL"
 * 输出: False
 *
 * @author liwangcheng
 * @date 2020/5/25.
 */
public class CheckRecord {

    /**
     * 方法 1： 简单的解法
     * 思路：
     * 解决这个问题最简单的方法就是统计字符串中 A 的数目并检查 LLL 是否
     * 是给定字符串的一个子串。如果 A 的数目比 2 少且 LLL 不是给定字符
     * 串的一个子串，那么返回 true，否则返回 false。
     *
     * Java 中 indexOf 方法可以用来检查一个串是否是另一个串的子串。
     * 如果找不到子串，那么返回 -1，否则返回这个字符串第一次出现的位置。
     *
     * 复杂度分析
     * 时间复杂度： O(n)。遍历字符串一遍和 indexOf 需要花费 O(n) 的时间。
     * 空间复杂度： O(1)。只使用了常数的空间。
     */
    public static boolean solution1(String str) {
        int count = 0;
        for (int i = 0, len = str.length(); i < len; i++) {
            if (str.charAt(i) == 'A') {
                count++;
            }
        }
        return count < 2 && !str.contains("LLL");
    }

    /**
     * 方法 2：优化的解法
     * 算法
     * 上述方法的一个优化是当 A 的数目有 2 个的时候就中断循环。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。遍历字符串一次和 indexOf 方法需要花费 O(n) 的时间。
     * 空间复杂度：O(1)。只使用了常数的空间。
     */
    public static boolean solution2(String str) {
        int count = 0;
        for (int i = 0, len = str.length(); i < len && count < 2; i++) {
            if (str.charAt(i) == 'A') {
                count++;
            }
        }
        return count < 2 && !str.contains("LLL");
    }

    /**
     * 方法 3：不需要 indexOf 的单遍循环方法
     * 算法
     * 可以遍历这个字符串一次不使用 indexOf 来解决这个问题。
     * 在单遍循环中，统计 A 的数目并检查子字符串 LLL 是否是一个子串。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。单遍循环的上限是字符串的长度。
     * 空间复杂度：O(1)。只使用了常数级别的空间。
     */
    public static boolean solution3(String str) {
        int count = 0;
        for (int i = 0, len = str.length(); i < len && count < 2; i++) {
            if (str.indexOf(i) == 'A') {
                count++;
            }
            if (i <= len - 3 && str.charAt(i) == 'L'
            && str.charAt(i + 1) == 'L' && str.charAt(i + 2) == 'L') {
                return false;
            }
        }
        return count < 2;
    }

    /**
     * 方法 4：使用正则表达式
     * 思路：
     * 下面是此解法中用到的正则表达式符号：
     *  . : 匹配任何除了换行以外的单个字符。
     *  * : 匹配 0 个或者 大于 0 个 * 符号前面的表达式。
     *  .* : 匹配任何字符串
     *  a|b : 要么匹配 a 要么匹配 b
     * matches 方法被用来检查是否有字符串匹配给定的正则表达式。
     *
     * 包含 2 个或更多 A 的正则表达式为 .*A.*A.*，包含子字符串
     * LLL 对应的正则表达式为 .*LLL.*。可以把两个正则表达式
     * 用 ∣ 形成一个正则表达式，来表示或者包含超过 1 个 A 或者
     * 包含 3 个连续的 L 的字符串。那么正则表达就变成 .*(A.*A|LLL).*。
     * 只有当字符串不能匹配正则表达式的时候返回 true。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。 matches 方法花费的时间最多为 O(n)。
     * 空间复杂度：O(1)。不需要使用额外的空间。
     */
    public static boolean solution4(String str) {
        return !str.matches(".*(A.*A|LLL).*");
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(PPALLP) = " + CheckRecord.solution1("PPALLP"));
        LeetCodeUtil.logln("solution1(PPALLL) = " + CheckRecord.solution1("PPALLL"));
        LeetCodeUtil.logln("solution2(PPALLP) = " + CheckRecord.solution2("PPALLP"));
        LeetCodeUtil.logln("solution2(PPALLL) = " + CheckRecord.solution2("PPALLL"));
        LeetCodeUtil.logln("solution3(PPALLP) = " + CheckRecord.solution3("PPALLP"));
        LeetCodeUtil.logln("solution3(PPALLL) = " + CheckRecord.solution3("PPALLL"));
        LeetCodeUtil.logln("solution4(PPALLP) = " + CheckRecord.solution4("PPALLP"));
        LeetCodeUtil.logln("solution4(PPALLL) = " + CheckRecord.solution4("PPALLL"));
    }
}
