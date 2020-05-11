package com.wangcheng.leetcode.LeetCode.String;

/**
 *【题目】
 * 443.压缩字符串
 * 给定一组字符，使用原地算法将其压缩。
 * 压缩后的长度必须始终小于或等于原数组长度。
 * <p>
 * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
 * 在完成原地修改输入数组后，返回数组的新长度。
 * <p>
 *【进阶】
 * 你能否仅使用O(1) 空间解决问题？
 *【示例】
 * 示例 1：
 * 输入：
 * ["a","a","b","b","c","c","c"]
 * 输出：
 * 返回6，输入数组的前6个字符应该是：["a","2","b","2","c","3"]
 * 说明：
 * "aa"被"a2"替代。"bb"被"b2"替代。"ccc"被"c3"替代。
 *
 * 示例 2：
 * 输入：
 * ["a"]
 * 输出：
 * 返回1，输入数组的前1个字符应该是：["a"]
 * 说明：
 * 没有任何字符串被替代。
 *
 * 示例 3：
 * 输入：
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：
 * 返回4，输入数组的前4个字符应该是：["a","b","1","2"]。
 * 说明：
 * 由于字符"a"不重复，所以不会被压缩。"bbbbbbbbbbbb"被“b12”替代。
 * 注意每个数字在数组中都有它自己的位置。
 *【注意】
 * 所有字符都有一个ASCII值在[35, 126]区间内。
 * 1 <= len(chars) <= 1000。
 *
 * @author liwangcheng
 * @date 2020/5/11.
 */
public class Compress {

    /**
     * TODO 比较经典
     * 方法：双指针
     * 直觉
     * 使用两个指针 read 和 write 分别标记读和写的位置。
     * 读写操作均从左到右进行：读入连续的一串字符，然后将压缩版写到数组中。
     * 最终，write 将指向输出答案的结尾。
     *
     * 算法
     * 保留指针 anchor，指向当前读到连续字符串的起始位置。
     * 从左到右进行读取。当读到最后一个字符，或者下一个下一个字符与当前不同时，
     * 则到达连续区块的结尾。
     *
     * 当到达连续区块的结尾时，就从 write 写入压缩的结果。
     * chars[anchor] 为字符，read - anchor + 1 （若大于 1）为长度。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 是 chars 的长度。
     * 空间复杂度：O(1)，三个指针的占用空间。
     */
    public static int solution(char[] chars) {
        if (null == chars || chars.length == 0) {
            return 0;
        }
        // write为写入指针
        int write = 0;
        // anchor标注一段连续字符的起始位置
        int anchor = 0;
        for (int read = 0; read < chars.length; read++) {
            // 当达到数组最后位置或连续字符的最后位置
            if (read == chars.length - 1 || chars[read+1] != chars[read]) {
                // 写入字符
                chars[write++] = chars[anchor];
                // 写入长度
                if (read > anchor) {
                    for (char ch : ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = ch;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }

}
