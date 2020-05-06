package com.wangcheng.leetcode.LeetCode.String;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *【题目】
 * 383.赎金信
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
 * 判断第一个字符串 ransom 能不能由第二个字符串 magazines 里
 * 面的字符构成。如果可以构成，返回 true；否则返回 false。
 * <p>
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，
 * 组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中
 * 使用一次。)
 * <p>
 * 注意：
 * 你可以假设两个字符串均只含有小写字母。
 * <p>
 *【示例】
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 *
 * @author liwangcheng
 * @date 2020/5/6.
 */
public class CanConstruct {

    /**
     * 方法一：排序与双指针
     * 参考：
     * https://leetcode-cn.com/problems/ransom-note/solution/383java-shuang-zhi-zhen-ha-xi-biao-shu-zu-dai-ti-h/
     */
    public static boolean solution1(String ransom, String magazine) {
        if (LeetCodeUtil.length(ransom) > LeetCodeUtil.length(magazine)) {
            return false;
        }
        char[] r = ransom.toCharArray();
        char[] m = magazine.toCharArray();
        Arrays.sort(r);
        Arrays.sort(m);
        int i = 0;
        int j = 0;
        while (i < r.length && j < m.length) {
            if (r[i] > m[j]) {
                j++;
            } else if (r[i] < m[j]) {
                return false;
            } else {
                i++;
                j++;
            }
        }
        return i == r.length;
    }

    /**
     * 方法二：哈希表计数
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean solution2(String ransom, String magazine) {
        if (LeetCodeUtil.length(ransom) > LeetCodeUtil.length(magazine)) {
            return false;
        }
        Map<Character, Integer> rMap = new HashMap<>();
        Map<Character, Integer> mMap = new HashMap<>();
        for (char ch : ransom.toCharArray()) {
            rMap.put(ch, rMap.getOrDefault(ch, 0) + 1);
        }
        for (char ch : magazine.toCharArray()) {
            mMap.put(ch, mMap.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : rMap.entrySet()) {
            if (entry.getValue() > mMap.getOrDefault(entry.getKey(), 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法三：用数组代替哈希表
     */
    public static boolean solution3(String ransom, String magazine) {
        if (LeetCodeUtil.length(ransom) > LeetCodeUtil.length(magazine)) {
            return false;
        }
        int[] countR = new int[26];
        int[] countM = new int[26];
        for (char ch : ransom.toCharArray()) {
            countR[ch - 'a']++;
        }
        for (char ch : magazine.toCharArray()) {
            countM[ch - 'a']++;
        }
        for (int i = 0; i < countR.length; i++) {
            if (countR[i] > countM[i]) {
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(a,b) = " + CanConstruct.solution1("a", "b"));
        LeetCodeUtil.logln("solution1(aa,ab) = " + CanConstruct.solution1("aa", "ab"));
        LeetCodeUtil.logln("solution1(aa,aab) = " + CanConstruct.solution1("aa", "aab"));
        LeetCodeUtil.logln("solution2(a,b) = " + CanConstruct.solution2("a", "b"));
        LeetCodeUtil.logln("solution2(aa,ab) = " + CanConstruct.solution2("aa", "ab"));
        LeetCodeUtil.logln("solution2(aa,aab) = " + CanConstruct.solution2("aa", "aab"));
        LeetCodeUtil.logln("solution3(a,b) = " + CanConstruct.solution3("a", "b"));
        LeetCodeUtil.logln("solution3(aa,ab) = " + CanConstruct.solution3("aa", "ab"));
        LeetCodeUtil.logln("solution3(aa,aab) = " + CanConstruct.solution3("aa", "aab"));
    }
}
