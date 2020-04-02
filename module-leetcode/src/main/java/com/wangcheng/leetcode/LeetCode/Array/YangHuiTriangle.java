package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *【题目】
 * 118.杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 *【示例】
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * @author liwangcheng
 * @date 2020/4/2.
 */
public class YangHuiTriangle {

    public static List<List<Integer>> solution(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (0 >= numRows) {
            return triangle;
        }
        triangle.add(new ArrayList<>(1));
        triangle.get(0).add(1);
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> prevRow = triangle.get(rowNum - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = YangHuiTriangle.solution(5);
        LeetCodeUtil.logln("[");
        for (int i = 0, size = triangle.size(); i < size; i++) {
            LeetCodeUtil.logln("\t" + triangle.get(i));
        }
        LeetCodeUtil.logln("]");
    }
}
