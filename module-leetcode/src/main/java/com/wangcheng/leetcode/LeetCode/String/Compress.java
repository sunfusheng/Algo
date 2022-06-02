package com.wangcheng.leetcode.LeetCode.String;

/**
 * 【题目】
 * 443.压缩字符串
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * <p>
 * 从一个空字符串 s 开始。对于 chars 中的每组连续重复字符 ：
 * 1、如果这一组长度为 1 ，则将字符追加到 s 中。
 * 2、否则，需要向 s 追加字符，后跟这一组的长度。
 * 3、压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。
 * 4、需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * 5、请在修改完输入数组后 ，返回该数组的新长度。
 * <p>
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 * <p>
 * 示例 1：
 * 输入：chars = ["a","a","b","b","c","c","c"]
 * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * 解释："aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * <p>
 * 示例 2：
 * 输入：chars = ["a"]
 * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 * 解释：唯一的组是“a”，它保持未压缩，因为它是一个字符。
 * <p>
 * 示例 3：
 * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 * 解释：由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
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
     * <p>
     * 算法
     * 保留指针 anchor，指向当前读到连续字符串的起始位置。
     * 从左到右进行读取。当读到最后一个字符，或者下一个字符与当前不同时，则到达连续区块的结尾。
     * <p>
     * 当到达连续区块的结尾时，就从 write 写入压缩的结果。
     * chars[anchor] 为字符，read - anchor + 1 （若大于 1）为长度。
     * <p>
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
            if (read == chars.length - 1 || chars[read + 1] != chars[read]) {
                // 写入字符
                chars[write++] = chars[anchor];
                // 写入长度
                if (read > anchor) {
                    for (char c : ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }

}
