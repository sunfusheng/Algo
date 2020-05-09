package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *【题目】
 * 412.Fizz Buzz
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 *【示例】
 * n = 15,
 * 返回:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 *
 * @author liwangcheng
 * @date 2020/5/9.
 */
public class FizzBuzz {

    /**
     * 方法一： 模拟法
     * 思路
     * 就像你每次玩 FizzBuzz 那样，你只需要判断这个数是能被 3 整除？
     * 还是能被 5 整除？ 或者是都能被整除。
     *
     * 算法
     * - 初始化一个空的答案列表。
     * - 遍历 1 ... N。
     * - 对于每个数，判断它能不能同时被 3 和 5 整除，如果可以就把 FizzBuzz 加入答案列表。
     * - 如果不行，判断它能不能被 3 整除，如果可以，把 Fizz 加入答案列表。
     * - 如果还是不行，判断它能不能被 5 整除，如果可以，把 Buzz 加入答案列表。
     * - 如果以上都不行，把这个数加入答案列表。
     *
     * 复杂度分析
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public static List<String> solution1(int num) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            boolean divisibleBy3 = (i % 3 == 0);
            boolean divisibleBy5 = (i % 5 == 0);
            if (divisibleBy3 && divisibleBy5) {
                ans.add("FizzBuzz");
            } else if (divisibleBy3) {
                ans.add("Fizz");
            } else if (divisibleBy5) {
                ans.add("Buzz");
            } else {
                ans.add(Integer.toString(num));
            }
        }
        return ans;
    }

    /**
     * 方法二：字符串连接
     * 算法
     * 放弃使用之前的联合判断，取而代之依次判断是否能被给定的数整除。
     *
     * 复杂度分析
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public static List<String> solution2(int num) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            boolean divisibleBy3 = (i % 3 == 0);
            boolean divisibleBy5 = (i % 5 == 0);
            String item = "";
            if (divisibleBy3) {
                item = "Fizz";
            }
            if (divisibleBy5) {
                item += "Buzz";
            }
            if (item.equals("")) {
                item = String.valueOf(i);
            }
            ans.add(item);
        }
        return ans;
    }

    /**
     * 方法三：用散列表
     * 把映射关系放在 散列表 里面。
     * 算法
     * - 把所有的映射关系放在散列表 fizzBuzzHash 中，这个散列表形如 { 3: 'Fizz', 5: 'Buzz' }。
     * - 遍历 1 ... N。
     * - 对于每个数字，遍历 fizzBuzzHash 中的键，检查是否能被它整除。
     * - 如果这个数能被键整除，就把当前键映射的值加到到答案字符串后面去。对于散列表的每个键值对，都这样操作。
     * - 最后将答案字符串加入答案列表。
     *
     * 复杂度分析
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public static List<String> solution3(int n) {
        List<String> ans = new ArrayList<String>();

        Map<Integer, String> fizzBizzDict =
                new HashMap<Integer, String>() {
                    {
                        put(3, "Fizz");
                        put(5, "Buzz");
                    }
                };

        for (int num = 1; num <= n; num++) {
            String numAnsStr = "";
            for (Integer key : fizzBizzDict.keySet()) {
                if (num % key == 0) {
                    numAnsStr += fizzBizzDict.get(key);
                }
            }
            if (numAnsStr.equals("")) {
                numAnsStr += Integer.toString(num);
            }
            ans.add(numAnsStr);
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(15) = " + FizzBuzz.solution1(15));
        LeetCodeUtil.logln("solution2(15) = " + FizzBuzz.solution2(15));
        LeetCodeUtil.logln("solution3(15) = " + FizzBuzz.solution3(15));
    }
}
