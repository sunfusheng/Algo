package com.wangcheng.leetcode.LeetCode.String;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Map;
import java.util.stream.Collectors;

/**
 *【题目】
 * 409.最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 *【示例】
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 * 解释:
 * 可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 * @author liwangcheng
 * @date 2020/5/9.
 */
public class BuildLongestPalindrome {

    /**
     * 方法：贪心
     * 思路
     * 回文串是一个正着读和反着读都一样的字符串。以回文中心为分界线，
     * 对于回文串中左侧的字符 ch，在右侧对称的位置也会出现同样的字符。
     * 例如：在字符串 "abba" 中，回文中心是 "ab|ba" 中竖线的位置，
     * 而在字符串 "abcba" 中，回文中心是 "ab(c)ba" 中的字符 "c"
     * 本身。我们可以发现，在一个回文串中，只有最多一个字符出现了奇数次，
     * 其余的字符都出现偶数次。
     *
     * 那么我们如何通过给定的字符构造一个回文串呢？我们可以将每个字符
     * 使用偶数次，使得它们根据回文中心对称。在这之后，如果有剩余的字符，
     * 我们可以再取出一个，作为回文中心。
     *
     * 算法
     * 对于每个字符 ch，假设它出现了 v 次，我们可以使用该字符 v/2*2 次，
     * 在回文串的左侧和右侧分别放置 v/2 个字符 ch，其中 / 为整数除法。
     * 例如：若 "a" 出现了 5 次，那么我们可以使用 "a" 的次数为 4，
     * 回文串的左右两侧分别放置 2 个 "a"。
     *
     * 如果有任何一个字符 ch 的出现次数 v 为奇数（即 v % 2 == 1），
     * 那么可以将这个字符作为回文中心，注意只能最多有一个字符作为回文中心。
     * 在代码中，我们用 ans 存储回文串的长度，由于在遍历字符时，ans
     * 每次会增加 v / 2 * 2，因此 ans 一直为偶数。但在发现了第一个出现
     * 次数为奇数的字符后，我们将 ans 增加 1，这样 ans 变为奇数，在后面
     * 发现其它出现奇数次的字符时，我们就不改变 ans 的值了。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 为字符串 s 的长度。需要遍历每个字符一次。
     * 空间复杂度：O(S)，其中 S 为字符集大小。
     */
    public static int solution1(String str) {
        if (LeetCodeUtil.isEmpty(str)) {
            return 0;
        }
        int[] count = new int[128];
        for (char ch : str.toCharArray()) {
            count[ch]++;
        }
        int ans = 0;
        for (int i = 0; i < count.length; i++) {
            ans += count[i] / 2 * 2;
            if (count[i] % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 利用int数组计数
     *
     * PS：将一个数变成偶数：x - (x & 1)
     */
    public static int solution2(String s) {
        if (LeetCodeUtil.isEmpty(s)) {
            return 0;
        }
        int[] cnt = new int[58];
        for (char c : s.toCharArray()) {
            cnt[c - 'A'] += 1;
        }

        int ans = 0;
        for (int x: cnt) {
            // 字符出现的次数最多用偶数次
            ans += x - (x & 1);
        }
        // 如果最终的长度小于原字符串的长度，说明里面某个字符出现了奇数次，那么那个字符可以放在回文串的中间，所以额外再加一。
        return ans < s.length() ? ans + 1 : ans;
    }

    /**
     * 用Java8的流式风格
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int solution3(String s) {
        Map<Integer, Integer> count = s.chars()
                .boxed()
                .collect(Collectors.toMap(k -> k, v -> 1, Integer::sum));
        int ans = count.values().stream().mapToInt(i -> i - (i & 1)).sum();
        return ans < s.length() ? ans + 1 : ans;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(abccccdd) = " + BuildLongestPalindrome.solution1("abccccdd"));
        LeetCodeUtil.logln("solution2(abccccdd) = " + BuildLongestPalindrome.solution2("abccccdd"));
        LeetCodeUtil.logln("solution3(abccccdd) = " + BuildLongestPalindrome.solution3("abccccdd"));
    }
}
