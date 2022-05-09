package com.wangcheng.leetcode.LeetCode.String;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 【题目】
 * <p>
 * 有效 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是有效 IP 地址，
 * 但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * <p>
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，
 * 这些地址可以通过在 s 中插入 '.' 来形成。你 不能重新排序或删除 s 中的任何数字。你可以按 任何顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135", "255.255.111.35"]
 * <p>
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * <p>
 * 示例 3：
 * 输入：s = "101023"
 * 输出：["1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"]
 * <p>
 * 提示：
 * [1]. 1 <= s.length <= 20
 * [2]. s 仅由数字组成
 *
 * @author sunfusheng
 * @since 2020/5/5
 */
public class RestoreIpAddresses {

    /**
     * 回溯法
     * <p>
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n)
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }

        helper(s, 0, new LinkedList<>(), res);
        return res;
    }

    @SuppressLint("NewApi")
    private static void helper(String s, int pos, LinkedList<String> path, List<String> res) {
        if (pos == s.length() && path.size() == 4) {
            res.add(String.join(".", path));
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (pos + i > s.length()) {
                break;
            }
            String sub = s.substring(pos, pos + i);
            if (sub.length() > 1 && sub.startsWith("0")
                    || sub.length() == 3 && Integer.parseInt(sub) > 255) {
                continue;
            }
            path.add(sub);
            helper(s, pos + i, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println("输入1：" + s);
        System.out.println("输出1：" + restoreIpAddresses(s));

        s = "101023";
        System.out.println("输入2：" + s);
        System.out.println("输出2：" + restoreIpAddresses(s));
    }
}
