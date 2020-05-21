package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 520.检测大写字母
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 * <p>
 * 定义，在以下情况时，单词的大写用法是正确的：
 * <p>
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写，比如 "Google"。
 * 否则，定义这个单词没有正确使用大写字母。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: "USA"
 * 输出: True
 *
 * 示例 2:
 * 输入: "FlaG"
 * 输出: False
 *【注意】
 * 输入是由大写和小写拉丁字母组成的非空单词。
 *
 * @author liwangcheng
 * @date 2020/5/21.
 */
public class DetectCapitalUse {

    /**
     * 思路
     * 遍历一遍字符串,记录大写字母个数和大写字母索引
     *
     * 算法
     * 声明cnt记录大写字母的个数;
     * 声明index记录大写字母的位置索引;
     * 当cnt==word.length,即所有字母都大写,返回true;
     * 或者,当cnt==1,且index==0,即只有首个字母是大写时,返回true;
     * 或者,当cnt==0,即所有字母均小写时,返回true;
     * 当cnt>1且cnt<word.length时,返回false;
     * 当cnt==1,但index!=0,即有一个大写字母,但该大写字母不在首位,返回false;
     */
    public static boolean solution(String word) {
        int cnt = 0;
        int index = 0;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] - 'A' <= 25) {
                cnt++;
                index = i;
            }
        }
        if (cnt == chars.length || ((cnt == 1) && index == 0) || cnt == 0) {
            return true;
        }
        if ((cnt > 1 && cnt != chars.length) || (cnt == 1 && index != 0)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(hello) = " + DetectCapitalUse.solution("hello"));
        LeetCodeUtil.logln("solution(Hello) = " + DetectCapitalUse.solution("Hello"));
        LeetCodeUtil.logln("solution(hEllo) = " + DetectCapitalUse.solution("hEllo"));
        LeetCodeUtil.logln("solution(HELLO) = " + DetectCapitalUse.solution("HELLO"));
    }
}
