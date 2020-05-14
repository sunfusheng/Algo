package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 476.数字的补数
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 *【示例】
 * 示例 1:
 * 输入: 5
 * 输出: 2
 * 解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 *
 * 示例 2:
 * 输入: 1
 * 输出: 0
 * 解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 *【注意】
 * 给定的整数保证在 32 位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 *
 * @author liwangcheng
 * @date 2020/5/14.
 */
public class FindComplement {

    /**
     * 方法一：异或运算
     * 思路：
     * 举个例子，101（5)^111=010
     * 所以只要拿到一个位数和num一样且所有位都为1的数就好了
     */
    public static int solution1(int num) {
        int temp = num;
        int help = 1;
        while (temp > 0) {
            help <<= 1;
            temp >>= 1;
        }
        // 所有位变为1
        help -= 1;
        return num ^ help;
    }

    /**
     * 方法二：位运算
     * 思路分析：
     * - 要对该数的二进制表示取反，首先需要知道每一位二进制位的
     *  数字是多少。这个做法就是常规的，用 temp = num & 1;
     *  得到当前位的数字。不断移位的操作也是常规的while循环和
     *  无符号右移num >>>= 1。
     *
     * - 但是怎么将数字取反后放到合适的位置。可以看一个例子。
     *  以 110010为例子
     *   第一位是0，该位取反为1   000001
     *   第二位是1，该位取反为0   000000
     *   第三位是0，该位取反为1   000100
     *   第四位是0，该位取反为1   001000
     *   第五位是1，该位取反为0   000000
     *   第六位是1，该位取反为0   000000
     *   相加                  001101 即为110010的按位取反
     * - 所以我们发现，当第i位数字为0时，就应该将1左移i-1位，
     *  成为最后结果的加数之一。当然第i位数字为1时，取反为0，就没
     *  必要进行移位相加了（反正都是0不影响）
     * - 所以我们用一个变量move来表示需要左移多少位。
     * - 注意到题目说每个数都没有前导零位，所以循环结束的标志就是num == 0。
     *
     * 复杂度分析
     * 时间复杂度为O(n)。
     * 空间复杂度为O(1)。
     *
     * 参考：
     * https://leetcode-cn.com/problems/number-complement/solution/476java-wei-yun-suan-yi-wei-cao-zuo-by-ustcyyw/
     */
    public static int solution2(int num) {
        int move = 0;
        int res = 0;
        while (num != 0) {
            int temp = num & 1;
            if (temp == 0) {
                res += (1 << move);
            }
            move++;
            num >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(1) = " + FindComplement.solution1(1));
        LeetCodeUtil.logln("solution1(2) = " + FindComplement.solution1(2));
        LeetCodeUtil.logln("solution1(3) = " + FindComplement.solution1(3));
        LeetCodeUtil.logln("solution1(4) = " + FindComplement.solution1(4));
        LeetCodeUtil.logln("solution1(5) = " + FindComplement.solution1(5));
        LeetCodeUtil.logln("solution2(1) = " + FindComplement.solution2(1));
        LeetCodeUtil.logln("solution2(2) = " + FindComplement.solution2(2));
        LeetCodeUtil.logln("solution2(3) = " + FindComplement.solution2(3));
        LeetCodeUtil.logln("solution2(4) = " + FindComplement.solution2(4));
        LeetCodeUtil.logln("solution2(5) = " + FindComplement.solution2(5));
    }
}
