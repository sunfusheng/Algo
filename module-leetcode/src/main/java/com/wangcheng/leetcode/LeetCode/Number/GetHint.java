package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 299.猜数字游戏
 * 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：
 * 你写下一个数字让你的朋友猜。每次他猜测后，你给他一个提示，
 * 告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），
 * 有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。
 * 你的朋友将会根据提示继续猜，直到猜出秘密数字。
 * <p>
 * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，
 * 用 A 表示公牛，用 B 表示奶牛。
 * <p>
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: secret = "1807", guess = "7810"
 * 输出: "1A3B"
 * 解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
 *
 * 示例 2:
 * 输入: secret = "1123", guess = "0111"
 * 输出: "1A1B"
 * 解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
 *【说明】
 * 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
 *
 * @author liwangcheng
 * @date 2020/5/3.
 */
public class GetHint {

    /**
     * 方法一
     * 可以通过两个 HashMap 统计相等的数字有多少个。
     * 只需要分别记录 secret 和 guess 中每个数字的个数，
     * 然后取两者较小的就是相等数字的个数了。
     *
     * 比如 secret 中有 3 个 2，guess 中有4 个 2，
     * 那么两者相等的数字就至少有 3 个。
     *
     * 可以做两点优化：
     * 第一点，因为 map 中的 key 只有 0 到 9，所以可以用数组取代 map 。
     * 第二点，我们可以把第二步用 map 统计个数的代码放到第一个 for 循环中，
     * 当位置不相等的时候再统计个数，这样最后就不用 B - A了。
     */
    public static String solution1(String secret, String guess) {
        int A = 0;
        int[] mapS = new int[10];
        int[] mapG = new int[10];
        for (int i = 0, size = secret.length(); i < size; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
            } else {
                mapS[secret.charAt(i) - '0']++;
                mapG[guess.charAt(i) - '0']++;
            }
        }
        int B = 0;
        for (int i = 0; i < mapS.length; i++) {
            B += Math.min(mapS[i], mapG[i]);
        }
        return A + "A" + B + "B";
    }

    /**
     * TODO
     * 参考：
     * https://leetcode-cn.com/problems/bulls-and-cows/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--55/
     */
    public static String solution2(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                bulls++;
            } else {
                // 当前数小于 0, 说明之前在 guess 中出现过, 和 secret 当前的数匹配
                if (numbers[s] < 0) {
                    cows++;
                }
                // 当前数大于 0, 说明之前在 secret 中出现过, 和 guess 当前的数匹配
                if (numbers[g] > 0) {
                    cows++;
                }
                // secret 中的数, 计数加 1
                numbers[s]++;
                // guess 中的数, 计数减 1
                numbers[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(1807, 7810) = " + GetHint.solution1("1807", "7810"));
        LeetCodeUtil.logln("solution1(1123, 0111) = " + GetHint.solution1("1123", "0111"));
        LeetCodeUtil.logln("solution2(1807, 7810) = " + GetHint.solution2("1807", "7810"));
        LeetCodeUtil.logln("solution2(1123, 0111) = " + GetHint.solution2("1123", "0111"));
    }
}
