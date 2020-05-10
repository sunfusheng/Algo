package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 437.路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，
 * 但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 *【示例】
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 * ------- 10
 * ------ /  \
 * ----- 5   -3
 * ---- / \    \
 * --- 3   2   11
 * -- / \   \
 * - 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 * 1. 5 -> 3
 * 2. 5 -> 2 -> 1
 * 3. -3 -> 11
 *
 * @author liwangcheng
 * @date 2020/5/10.
 */
public class PathSumIII {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归方式
     * 递归的一个重要思想就是两部分：
     * 1.找到最简单的子问题求解
     * 2.其他问题不考虑内在细节，只考虑整体逻辑
     *
     * 题目要求 路径不需要从根节点开始，也不需要在叶子节点结束，
     * 但是路径方向必须是向下的（只能从父节点到子节点）。
     * 这就要求我们只需要去求三部分即可：
     *  - 以当前节点作为头结点的路径数量
     *  - 以当前节点的左孩子作为头结点的路径数量
     *  - 以当前节点的右孩子作为头结点的路径数量
     * 将这三部分之和作为最后结果即可。
     *
     * 最后的问题是：应该如何去求以当前节点作为头结点的路径的数量？
     * 这里依旧是按照树的遍历方式模板，每到一个节点让sum-root.val，
     * 并判断sum是否为0，如果为零的话，则找到满足条件的一条路径。
     *
     * 参考：
     * https://leetcode-cn.com/problems/path-sum-iii/solution/437lu-jing-zong-he-iii-di-gui-fang-shi-by-ming-zhi/
     */
    public static int solution(TreeNode root, int sum) {
        return pathSum(root, sum);
    }

    private static int pathSum(TreeNode root, int sum) {
        if (null == root) {
            return 0;
        }
        int result = countPath(root, sum);
        int a = pathSum(root.left, sum);
        int b = pathSum(root.right, sum);
        return result + a + b;
    }

    private static int countPath(TreeNode root, int sum) {
        if (null == root) {
            return 0;
        }
        sum -= root.val;
        // sum == 0 表示找到一条路径
        int result = sum == 0 ? 1 : 0;
        return result + countPath(root.left, sum) + countPath(root.right, sum);
    }

    public static void main(String[] args) {
        PathSumIII.TreeNode root = new PathSumIII.TreeNode(10);
        root.left = new PathSumIII.TreeNode(5);
        root.right = new PathSumIII.TreeNode(-3);
        root.left.left = new PathSumIII.TreeNode(3);
        root.left.right = new PathSumIII.TreeNode(2);
        root.right.right = new PathSumIII.TreeNode(11);
        root.left.left.left = new PathSumIII.TreeNode(3);
        root.left.left.right = new PathSumIII.TreeNode(-2);
        root.left.right.right = new PathSumIII.TreeNode(1);
        LeetCodeUtil.logln("solution(8) = " + PathSumIII.solution(root, 8));
        LeetCodeUtil.logln("solution(7) = " + PathSumIII.solution(root, 7));
        LeetCodeUtil.logln("solution(6) = " + PathSumIII.solution(root, 6));
    }
}
