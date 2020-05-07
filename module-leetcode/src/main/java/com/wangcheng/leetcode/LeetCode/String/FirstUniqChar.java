package com.wangcheng.leetcode.LeetCode.String;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *【题目】
 * 387.字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，
 * 并返回它的索引。如果不存在，则返回 -1。
 *【案例】
 * s = "leetcode"
 * 返回 0.
 * s = "loveleetcode",
 * 返回 2.
 * <p>
 * 注意事项：可以假定该字符串只包含小写字母。
 * <p>
 *
 * @author liwangcheng
 * @date 2020/5/7.
 */
public class FirstUniqChar {

    /**
     * 线性时间复杂度解法
     *
     * 复杂度分析
     * 时间复杂度： O(N)
     *  只遍历了两遍字符串，同时散列表中查找操作是常数时间复杂度的。
     * 空间复杂度： O(N)
     *  用到了散列表来存储字符串中每个元素出现的次数。
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int solution(String str) {
        if (LeetCodeUtil.isEmpty(str)) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            map.put(charArr[i], map.getOrDefault(charArr[i], 0) + 1);
        }
        for (int i = 0; i < charArr.length; i++) {
            if (map.get(charArr[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(leetcode) = " + FirstUniqChar.solution("leetcode"));
        LeetCodeUtil.logln("solution(loveleetcode) = " + FirstUniqChar.solution("loveleetcode"));
    }
}
