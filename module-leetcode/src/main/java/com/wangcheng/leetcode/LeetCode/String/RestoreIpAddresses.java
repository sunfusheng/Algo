package com.wangcheng.leetcode.LeetCode.String;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;


/**
 * 【题目】
 * 93.复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author sunfusheng
 * @since 2020/5/5
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class RestoreIpAddresses {

    /**
     * 回溯法
     * <p>
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n)
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return null;
        }

        List<String> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtrack(s, 0, path, res);
        return res;
    }

    private static void backtrack(String s, int pos, List<String> path, List<String> res) {
        if (path.size() == 4 && pos == s.length()) {
            res.add(String.join(".", path));
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (pos + i > s.length()) {
                break;
            }
            String segment = s.substring(pos, pos + i);
            if (segment.length() == 1 && segment.startsWith("0") ||
                    segment.length() == 3 && Integer.parseInt(segment) > 255) {
                continue;
            }
            path.add(segment);
            backtrack(s, pos + i, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println("输入：" + s);
        System.out.println("输出：" + restoreIpAddresses(s));
    }
}
