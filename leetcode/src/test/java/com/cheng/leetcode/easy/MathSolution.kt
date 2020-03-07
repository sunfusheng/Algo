package com.cheng.leetcode.easy

import org.junit.Test

/**
 *
 * @author liwangcheng
 * @date  2019-12-26 10:42.
 */
class MathSolution {

    @Test
    fun testReverse() {
        val i = -1023
        val v = reverse1(i)
        print("i = $i , v = $v")
    }

    fun reverse1(i: Int): Int {
        var rev = 0
        var x = i
        val max = Int.MAX_VALUE / 10
        val min = Int.MIN_VALUE / 10
        while (x != 0) {
            val pop = x % 10
            x /= 10
            if (rev > max || (rev == max && pop > 7)) return 0
            if (rev < min || (rev == min && pop < -8)) return 0
            rev = rev * 10 + pop
        }
        return rev
    }

    @Test
    fun testAtoi() {
        val s = "-123a"
        val i = atoi(s)
        print("s = $s, i = $i")
    }

    fun atoi(s: String) : Int {
        if (s.isEmpty()) {
            return 0
        }
        // 1.判断空格
        var i = 0
        while (i < s.length && s[i] == ' ') {
            i++
        }
        if (i == s.length) {
            return 0
        }
        // 2.判断符号
        var flag = 1
        var ch = s[i]
        if (ch == '+') {
            flag = 1
            i++
        }
        if (ch == '-') {
            flag = -1
            i++
        }
        // 3.转换为Int
        var res = 0
        val max = Int.MAX_VALUE / 10
        val min = Int.MIN_VALUE / 10
        while (i < s.length) {
            ch = s[i]
            if (ch < '0' || ch > '9') {
                break
            }
            res = res * 10 + (ch - '0')
            // 判断溢出
            val t = s[i+1]
            if (flag > 0 && i+1 < s.length && t >= '0' && t <= '9' && res > max) {
                return Int.MAX_VALUE
            }
            if (flag > 0 && i+1 < s.length && t >= '0' && t <= '9' && res == max && t-'0' > 7) {
                return Int.MAX_VALUE
            }
            if (flag < 0 && i+1 < s.length && t >= '0' && t <= '9' && -res < min) {
                return Int.MIN_VALUE
            }
            if (flag < 0 && i+1 < s.length && t >= '0' && t <= '9' && -res == min && -(t-'0') < -8) {
                return Int.MIN_VALUE
            }
            i++
        }
        return res * flag
    }

    @Test
    fun testIsPalindrome() {
        var i = 123
        var b = isPalindrome(i)
        println("$i isPalindrome $b")
        i = 121
        b = isPalindrome(i)
        println("$i isPalindrome $b")
    }

    /*
    判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    示例 1:
    输入: 121
    输出: true
    示例 2:
    输入: -121
    输出: false
    解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    示例 3:
    输入: 10
    输出: false
    解释: 从右向左读, 为 01 。因此它不是一个回文数。
     */
    fun isPalindrome(i: Int) : Boolean {
        // 反转一半数字
        // 为了避免数字反转可能导致的溢出问题，为什么不考虑只反转 int 数字的一半？
        // 毕竟，如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同。

        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        var x = i
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false
        }
        var revertedNumber = 0
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10
            x /= 10
        }
        return x == revertedNumber || x == revertedNumber / 10
    }
}