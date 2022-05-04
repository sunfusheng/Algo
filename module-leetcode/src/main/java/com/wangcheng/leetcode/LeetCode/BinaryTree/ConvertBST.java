package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Stack;

/**
 * 【题目】
 * 538.把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树
 * （Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它
 * 的节点值之和。
 * 例如：
 * 输入: 原始二叉搜索树:
 * ---- 5
 * -- /   \
 * - 2     13
 * <p>
 * 输出: 转换为累加树:
 * ----- 18
 * --- /   \
 * - 20     13
 *
 * @author liwangcheng
 * @date 2020/5/23.
 */
public class ConvertBST {

    /**
     * 方法 1：回溯
     * 想法
     * 一个反序中序遍历的方法是通过递归实现。通过调用栈回到之前的节点，
     * 可以轻松地反序遍历所有节点。
     * <p>
     * 算法
     * 在递归方法中，维护一些递归调用过程中可以访问和修改的全局变量。
     * 首先判断当前访问的节点是否存在，如果存在就递归右子树，递归回来
     * 的时候更新总和和当前点的值，然后递归左子树。如果分别正确地递归
     * root.right 和 root.left ，那么就能正确地用大于某个节点的值
     * 去更新此节点，然后才遍历比它小的值。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)
     * 一个二叉树是没有环的，所以 convertBST 对于每个节点来说不会被
     * 调用超过 1 次。除去递归调用以外， convertBST 做的工作是常数
     * 时间的，所以线性次调用 convertBST 的运行时间是线性时间的。
     * <p>
     * 空间复杂度：O(n)
     * 使用之前的结论 convertBST 会被调用线性次，可以知道整个算法的
     * 空间复杂度也是线性的。考虑最坏情况，一棵树只有右子树（或者只有
     * 左子树），调用栈会一直增长直到到达叶子节点，也就是包含 n 个节点。
     */
    private static int sum;

    public static TreeNode solution1(TreeNode root) {
        if (root != null) {
            solution1(root.right);
            sum += root.val;
            root.val = sum;
            solution1(root.left);
        }
        return root;
    }

    /**
     * 方法 2：使用栈迭代 [Accepted]
     * 想法
     * 如果不想使用递归，可以通过迭代和栈来实现函数调用栈的过程。
     * <p>
     * 算法
     * 一个描述迭代栈的方法就是通过递归的思想。首先初始化一个空的
     * 栈并把根节点作为当前节点。然后只要在栈中有未遍历节点或者
     * node 节点不为空，就将当前节点到最右边叶子路径上的点全部压入
     * 栈中。这与递归过程中我们总是先走右子树的思路是一致的，这个
     * 思路确保总是降序遍历所有节点的值。接下来，访问栈顶节点，并
     * 考虑它的左子树，这就像递归中先遍历当前节点再遍历它的左子树的
     * 思路。最后，栈为空并且 node 指向树中最小节点的左孩子 null，
     * 循环结束。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)
     * 最关键一点是每一个节点会被压入栈中恰好一次。每个点会被压入栈中
     * 至少 一次的结论是显而易见的，否则的话至少有一个点与根不连通。
     * 一个点被压入栈中一定是 node 在外面的 while 循环中初始时指向了
     * 该点，或者从某个点出发只往右边走走道了该点。同时注意到每次循环
     * 迭代的最后，node 指向一个点的左孩子并被压入栈中（后面会被弹出）。
     * 由于外层 while 循环总是从 node 指向 None 、根、一个已访问
     * 节点的左孩子开始的，所以不会有节点被重新访问。
     * <p>
     * 空间复杂度：O(n)
     * 如果假设上面的逻辑是正确的，那么每个节点只会被压入栈中恰好一次
     * 的结论表明栈最多只会包含 n 个节点。算法其他的部分只会用常数的
     * 空间，所以总空间是线性的。
     */
    public static TreeNode solution2(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || null != node) {
            while (null != node) {
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            sum += node.val;
            node.val = sum;

            node = node.left;
        }
        return root;
    }

    /**
     * TODO
     * 方法 3：反序中序 Morris 遍历
     * 参考：
     * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/solution/ba-er-cha-sou-suo-shu-zhuan-huan-wei-lei-jia-shu-3/
     *
     */
    /**
     * Get the node with the smallest value greater than this one.
     */
    private static TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }

    public static TreeNode solution3(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while (node != null) {
            /*
             * If there is no right subtree, then we can visit this node and
             * continue traversing left.
             */
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
            /*
             * If there is a right subtree, then there is at least one node that
             * has a greater value than the current one. therefore, we must
             * traverse that subtree first.
             */
            else {
                TreeNode succ = getSuccessor(node);
                /*
                 * If the left subtree is null, then we have never been here before.
                 */
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                }
                /*
                 * If there is a left subtree, it is a link that we created on a
                 * previous pass, so we should unlink it and visit this node.
                 */
                else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        LeetCodeUtil.logln("solution1() = " + solution1(root));
        root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        LeetCodeUtil.logln("solution21() = " + solution2(root));
        root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        LeetCodeUtil.logln("solution3() = " + solution3(root));
    }
}
