package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *【题目】
 * 500.键盘行
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
 *【示例】
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 *【注意】
 * 你可以重复使用键盘上同一字符。
 * 你可以假设输入的字符串将只包含字母。
 *
 * @author liwangcheng
 * @date 2020/5/19.
 */
public class FindWords {

    public static String[] solution(String[] words) {
        if (LeetCodeUtil.isEmpty(words)) {
            return null;
        }
        String[] voc = new String[]{"QWERTYUIOPqwertyuiop","ASDFGHJKLasdfghjkl","ZXCVBNMzxcvbnm"};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int flag = -1;
            if (voc[0].indexOf(words[i].charAt(0)) >= 0) {
                flag = 0;
            }
            if (voc[1].indexOf(words[i].charAt(0)) >= 0) {
                flag = 1;
            }
            if (voc[2].indexOf(words[i].charAt(0)) >= 0) {
                flag = 2;
            }
            if (flag < 0) {
                continue;
            }
            for (int j = 0; j < words[i].length(); j++) {
                if (voc[flag].indexOf(words[i].charAt(j)) < 0) {
                    flag = -1;
                    break;
                }
            }
            if (flag >= 0) {
                list.add(i);
            }
        }
        int size = list.size();
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = words[list.get(i)];
        }
        return strings;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution([\"Hello\", \"Alaska\", \"Dad\", \"Peace\"]) = " + Arrays.toString(FindWords.solution(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }
}
