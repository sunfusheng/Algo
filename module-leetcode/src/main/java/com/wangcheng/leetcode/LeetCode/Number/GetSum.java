package com.wangcheng.leetcode.LeetCode.Number;

/**
 *【题目】
 * 371.两整数之和
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 *【示例】
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 *
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * @author liwangcheng
 * @date 2020/5/6.
 */
public class GetSum {

    /**
     * 在二进制的计算中是通过位操作来得到结果的低位和进位，
     * 对于不同的情况，用表格来表示一下，两个数字分别为a和b
     *      a	b	低位	进位
     *      0	0	0	0
     *      1	0	1	0
     *      0	1	1	0
     *      1	1	0	1
     * 从上面的表格就可以发现，低位 = a^b，进位 = a&b。
     * 这样的计算可能要持续多次，回想一下在十进制的计算中，
     * 如果进位一直大于0，就得往后面进行计算，在这里也是一
     * 样，只要进位不是0，就得一直重复计算低位和进位的操作
     * （需要在下一次计算之前要把进位向左移动一位，这样进位
     * 才能和更高位进行运算）。这个时候的a和b就是刚才计算的
     * 低位和进位。
     *
     * 参考：
     * https://leetcode-cn.com/problems/sum-of-two-integers/solution/0msfu-xian-ji-suan-ji-zui-ji-ben-de-jia-fa-cao-zuo/
     */
    public static int solution(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int lower;
        int carrier;
        while (true) {
            // 计算低位
            lower = a ^ b;
            // 计算进位
            carrier = a & b;
            if (carrier == 0) {
                break;
            }
            a = lower;
            b = carrier << 1;
        }
        return lower;
    }
}
