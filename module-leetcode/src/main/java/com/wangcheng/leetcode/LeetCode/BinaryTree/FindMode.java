package com.wangcheng.leetcode.LeetCode.BinaryTree;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *【题目】
 * 501.二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），
 * 找出 BST 中的所有众数（出现频率最高的元素）。
 * <p>
 * 假定 BST 有如下定义：
 * <p>
 * - 结点左子树中所含结点的值小于等于当前结点的值
 * - 结点右子树中所含结点的值大于等于当前结点的值
 * - 左子树和右子树都是二叉搜索树
 * <p>
 * 例如：
 * 给定 BST [1,null,2,2],
 * --- 1
 * ---- \
 * ----- 2
 * ---- /
 * --- 2
 * 返回[2].
 * 【提示】
 * 如果众数超过1个，不需考虑输出顺序
 * 【进阶】
 * 你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 * @author liwangcheng
 * @date 2020/5/19.
 */
public class FindMode {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 参考：
     * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/solution/501javazhong-xu-bian-li-chang-shu-kong-jian-xiang-/
     */

    private static List<Integer> items;
    private static int maxCount;
    private static int curCount;
    private static TreeNode pre;

    public static int[] solution1(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        items = new ArrayList<>();
        // 这里设置为1是因为 在递归中 BST中最左边的结点被跳过了，
        // 作为初状态 该结点值出现一次，记录的出现最多次数一开始也为1
        maxCount = 1;
        curCount = 1;
        midTraversal(root);
        // 最右端结点的处理，从递归中看，最后一个结点与前一个结点相等
        // 只更新了curCount的值；不相等则只考虑到倒数第二个结点
        if (curCount > maxCount) {
            return new int[]{pre.val};
        }
        if (curCount == maxCount) {
            items.add(pre.val);
        }
        int[] res = new int[items.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = items.get(i);
        }
        return res;
    }

    private static void midTraversal(TreeNode x) {
        if (x == null) {
            return;
        }
        midTraversal(x.left);
        if (pre != null) {
            // 说明上一个值的结点数量已经统计完成 要看出现次数的情况
            if (x.val != pre.val) {
                // 出现次数更多，清空之前的出现少的数，更新最大出现次数
                if (curCount > maxCount) {
                    maxCount = curCount;
                    items.clear();
                    items.add(pre.val);
                } else if (curCount == maxCount) {
                    // 不止一个众数
                    items.add(pre.val);
                }
                // 当前值与上一个结点值不同，重置计数
                curCount = 1;
            } else {
                // 当前值与上一个结点值相同，当前值的出现次数增加
                curCount++;
            }
        }
        pre = x;
        midTraversal(x.right);
    }

    private static Map<Integer, Integer> map;
    private static int count;
    private static int max;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int[] solution2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        map = new HashMap<>();
        count =  0;
        max = 0;
        midTraversal2(root);
        int[] res = new int[count];
        for(int key : map.keySet()){
            if(map.get(key) == max) {
                res[--count] = key;
            }
        }
        return res;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void midTraversal2(TreeNode x){
        if(x == null) {
            return;
        }
        midTraversal2(x.left);
        int temp = map.getOrDefault(x.val, 0) + 1;
        map.put(x.val, temp);
        if(temp > max){
            max = temp;
            count = 1;
        } else if(temp == max){
            count++;
        }
        midTraversal2(x.right);
    }

    public static void main(String[] args) {
        FindMode.TreeNode root = new FindMode.TreeNode(1);
        root.right = new FindMode.TreeNode(2);
        root.right.left = new FindMode.TreeNode(2);
        LeetCodeUtil.logln("solution1() = " + Arrays.toString(FindMode.solution1(root)));
        LeetCodeUtil.logln("solution2() = " + Arrays.toString(FindMode.solution2(root)));
    }
}
