package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 461.汉明距离
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 *【注意】
 * 0 ≤ x, y < 2^31.
 *【示例】
 * 输入: x = 1, y = 4
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * @author liwangcheng
 * @date 2020/5/13.
 */
public class HammingDistance {

    /**
     * 思路
     * 汉明距离广泛应用于多个领域。在编码理论中用于错误检测，在信息论中量化字符串之间的差异。
     * 两个整数之间的汉明距离是对应位置上数字不同的位数。
     * 根据以上定义，提出一种 XOR 的位运算，当且仅当输入位不同时输出为 1。
     * 计算 x 和 y 之间的汉明距离，可以先计算 x XOR y，然后统计结果中等于 1 的位数。
     * 现在，原始问题转换为位计数问题。位计数有多种思路，将在下面的方法中介绍。
     */

    /**
     * 方法一：内置位计数功能
     * 思路
     * 大多数编程语言中，都存在各种内置计算等于 1 的位数函数。
     * 如果这是一个项目中的问题，应该直接使用内置函数，而不是重复造轮子。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(1)。
     *  该算法有两个操作。计算 XOR 花费恒定时间。
     *  调用内置的 bitCount 函数。最坏情况下，该函数复杂度为 O(k)，
     *  其中 k 是整数的位数。在 Python 和 Java 中 Integer 是固定
     *  长度的。因此该算法复杂度恒定，与输入大小无关。
     * 空间复杂度：O(1)，使用恒定大小的空间保存 XOR 的结果。
     *  假设内置函数也使用恒定空间。
     */
    public static int solution1(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * 方法二：移位
     * 思路
     * 为了计算等于 1 的位数，可以将每个位移动到最左侧或最右侧，然后检查该位是否为 1。
     * 更准确的说，应该进行逻辑移位，移入零替换丢弃的位。
     * 这里采用右移位，每个位置都会被移动到最右边。移位后检查最右位的位是否为 1 即可。
     * 检查最右位是否为 1，可以使用取模运算（i % 2）或者 AND 操作（i & 1），这两个
     * 操作都会屏蔽最右位以外的其他位。
     *
     * 复杂度分析
     * 时间复杂度：O(1)，在 Python 和 Java 中 Integer 的大小是固定的，
     *  处理时间也是固定的。 32 位整数需要 32 次迭代。
     * 空间复杂度：O(1)，使用恒定大小的空间。
     */
    public static int solution2(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1) {
                distance++;
            }
            xor >>= 1;
        }
        return distance;
    }

    /**
     * 方法三：布赖恩·克尼根算法
     * 思路
     * 方法二是逐位移动，逐位比较边缘位置是否为 1。寻找一种更快的方法找出等于 1 的位数。
     * 是否可以像人类直观的计数比特为 1 的位数，跳过两个 1 之间的 0。例如：10001000。
     * 上面例子中，遇到最右边的 1 后，如果可以跳过中间的 0，直接跳到下一个 1，效率会高很多。
     * 这是布赖恩·克尼根位计数算法的基本思想。该算法使用特定比特位和算术运算移除等于 1 的最右比特位。
     *
     * 当在 number 和 number-1 上做 AND 位运算时，原数字 number 的最右边等于 1 的比特会被移除。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(1)。
     *  与移位方法相似，由于整数的位数恒定，因此具有恒定的时间复杂度。
     *  但是该方法需要的迭代操作更少。
     * 空间复杂度：O(1)，与输入无关，使用恒定大小的空间。
     */
    public static int solution3(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            distance++;
            xor &= (xor - 1);
        }
        return distance;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(1, 4) = " + HammingDistance.solution1(1, 4));
        LeetCodeUtil.logln("solution2(1, 4) = " + HammingDistance.solution2(1, 4));
        LeetCodeUtil.logln("solution3(1, 4) = " + HammingDistance.solution3(1, 4));
    }
}
