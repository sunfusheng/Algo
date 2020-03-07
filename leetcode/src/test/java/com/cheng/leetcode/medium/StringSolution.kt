package com.cheng.leetcode.medium

import org.junit.Test
import java.lang.StringBuilder
import kotlin.math.max

/**
 *
 * @author liwangcheng
 * @date  2019-12-21 09:18.
 */
class StringSolution {


    /*
    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

    示例 1:
    输入: "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    @Test
    fun testLengthOfLongestSubstring() {
//        val res = lengthOfLongestSubstring1("abcabcbb")
//        val res = lengthOfLongestSubstring2("abcabcbb")
        val res = lengthOfLongestSubstring3("abcabcbb")
        print(res)
    }

    fun lengthOfLongestSubstring1(s: String): Int {
        val n = s.length
        var ans = 0
        for (i in 0 until n) {
            for (j in i + 1 until n + 1) {
                if (allUnique(s, i, j)) {
                    ans = max(ans, j - i)
                }
            }
        }
        return ans
    }

    private fun allUnique(s: String, i: Int, j: Int): Boolean {
        val set = mutableSetOf<Char>()
        for (i in 0 until j) {
            val ch = s[i]
            if (set.contains(ch)) {
                return false
            }
            set.add(ch)
        }
        return true
    }

    fun lengthOfLongestSubstring2(s: String): Int {
        val n = s.length
        val set = mutableSetOf<Char>()
        var ans = 0
        var i = 0
        var j = 0
        while (i < n && j < n) {
            if (!set.contains(s[j])) {
                set.add(s[j++])
                ans = max(ans, j - i)
            } else {
                // 窗口右移
                set.remove(s[i++])
            }
        }

        return ans
    }

    fun lengthOfLongestSubstring3(s: String): Int {
        val n = s.length
        val map = mutableMapOf<Char, Int>()
        var ans = 0
        var i = 0
        for (j in 0 until n) {
            if (map.containsKey(s[j])) {
                i = max(map[s[j]] ?: 0, i)
            }
            ans = max(ans, j - i + 1)
            map[s[j]] = j + 1
        }
        return ans
    }

    /*
    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

    示例 1：
    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：
    输入: "cbbd"
    输出: "bb"
     */
    @Test
    fun testLongestPalindrome() {
//        val len = longestPalindrome1("dabababad")
//        val len = longestPalindrome2("babad")
        val len = longestPalindrome3("babad")
        print("babad longestPalindrome is $len")
    }

    fun longestPalindrome1(s: String): Int {
        // 采用动态规划，列举回文串的起点或者终点来解决最长回文串问题，无需讨论串长度的奇偶性
        val n = s.length
        // pal[i][j] 表示 s[i...j] 是否是回文串
        val pal = Array<BooleanArray>(n){ BooleanArray(n)}
        var maxLen = 0
        for (i in 0 until n) { // i 作为终点
            var j = i // j作为起点
            while (j >= 0) {
                if (s[j] == s[i] &&
                    (i - j < 2 || pal[j+1][i-1])) { // 1.i==j 2.i、j相邻 3.去掉收尾字符的子串还是回文串
                    pal[j][i] = true
                    maxLen = Math.max(maxLen, i - j + 1)
                }
                j--
            }
        }
        return maxLen
    }

    // 中心扩展算法
    fun longestPalindrome2(s: String) : String {
        if (s.isEmpty()) {
            return ""
        }
        var start = 0
        var end = 0
        for (i in s.indices) {
            val len1 = expandAroundCenter(s, i, i) // 偶数回文情况
            val len2 = expandAroundCenter(s, i, i + 1) // 奇数回文情况
            val len = Math.max(len1, len2)
            if (len > end - start) {
                start = i - (len - 1) / 2
                end = i + len / 2
            }
        }
        return s.substring(start, end + 1)
    }

    private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
        var L = left
        var R = right
        while (L >= 0 && R < s.length && s[L] == s[R]) {
            L--
            R++
        }
        return R - L - 1
    }

    // Mancher算法
    fun longestPalindrome3(s: String) : Int {
        // 预处理
        val builder = StringBuilder()
        builder.append('#')
        s.forEach {
            builder.append(it)
            builder.append('#')
        }
        val str = builder.toString()
        val len = str.length
        val RL = IntArray(len)
        var MaxRight = 0
        var pos = 0
        var MaxLen = 0
        for (i in 0 until len) {
            if (i < MaxRight) {
                // 找到i关于pos的对称位置j
                val j = pos - (i - pos) // 2*pos - i
                RL[i] = Math.min(RL[j], MaxRight - i)
            } else {
                // 回文半径默认是1
                RL[i] = 1
            }
            // 尝试扩展回文半径，注意处理边界
            while (
                (i - RL[i] >= 0)
                && (i + RL[i] < len)
                && (str[i-RL[i]] == str[i+RL[i]])) {
                RL[i] += 1
            }
            // 更新MaxRight、pos
            if (RL[i] + i - 1 > MaxRight) {
                MaxRight = RL[i] + i - 1
                pos = i
            }
            // 更新最长回文串的长度
            MaxLen = Math.max(MaxLen, RL[i])
        }
        return MaxLen - 1
    }

    @Test
    fun testConvert() {
        val row = 3
//        val str = convert1("LEETCODEISHIRING", row)
        val str = convert2("LEETCODEISHIRING", 4)
        print("$str - $row")
    }

    /*
    将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
    比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
    L   C   I   R
    E T O E S I I G
    E   D   H   N
    之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
    请你实现这个将字符串进行指定行数变换的函数：
    string convert(string s, int numRows);
    示例 1:
    输入: s = "LEETCODEISHIRING", numRows = 3
    输出: "LCIRETOESIIGEDHN"
    示例 2:
    输入: s = "LEETCODEISHIRING", numRows = 4
    输出: "LDREOEIIECIHNTSG"
    解释:
    L     D     R
    E   O E   I I
    E C   I H   N
    T     S     G
     */
    
    /*
    方法一：按行排序
    思路
    通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行。
    
    算法
    我们可以使用 min(numRows, len(s))min(numRows,len(s)) 个列表来表示 Z 字形图案中的非空行。
    从左到右迭代 ss，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
    只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
     */
    fun convert1(s: String, numRows: Int): String {
        if (numRows <= 1) return s
        val rows = ArrayList<StringBuilder>()
        val n = Math.min(numRows, s.length)
        for (i in 0 until n) {
            rows.add(StringBuilder())
        }
        var curRow = 0
        var goingDown = false
        s.forEach { c ->
            rows[curRow].append(c)
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown
            }
            curRow += if (goingDown) 1 else -1
        }
        val ret = StringBuilder()
        rows.forEach{ row ->
            ret.append(row)
        }
        return ret.toString()
    }

    /*
    方法二：按行访问
    思路
    按照与逐行读取 Z 字形图案相同的顺序访问字符串。

    算法
    首先访问 行 0 中的所有字符，接着访问 行 1，然后 行 2，依此类推...
    对于所有整数 k，
        行 00 中的字符位于索引 k(2⋅numRows - 2) 处;
        行 numRows-1numRows−1 中的字符位于索引 k(2⋅numRows - 2) + numRows} - 1 处;
        内部的 行 i 中的字符位于索引 k(2⋅numRows-2)+i 以及 (k+1)(2⋅numRows-2)-i 处;
     */
    fun convert2(s: String, numRows: Int): String {
        if (numRows <= 1) return s
        val ret = StringBuilder()
        val n = s.length
        val cycleLen = 2 * numRows - 2
        for (i in 0 until numRows) {
            for (j in 0 until (n - i) step cycleLen) {
                ret.append(s[j + i])
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    ret.append(s[j + cycleLen - i])
                }
            }
        }
        return ret.toString()
    }

}