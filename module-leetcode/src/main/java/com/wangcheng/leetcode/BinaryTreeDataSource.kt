package com.wangcheng.leetcode

import com.wangcheng.leetcode.LeetCode.BinaryTree.*

/**
 * @author liwangcheng
 * @date 2020/5/1.
 */
fun binaryTreeDataSource(dataSource: DataSource) {
    dataSource.binaryTreeChapter {
        item {
            className = InorderTraversal::class.simpleName
            subject = "94.二叉树的中序遍历"
            hardLevel = 2
        }
        item {
            className = BSTNum::class.simpleName
            subject = "96.不同的二叉搜索树"
            hardLevel = 2
        }
        item {
            className = SameTree::class.simpleName
            subject = "100.相同的树"
            hardLevel = 1
        }
        item {
            className = IsSymmetric::class.simpleName
            subject = "101.对称二叉树"
            hardLevel = 1
        }
        item {
            className = LevelOrder::class.simpleName
            subject = "102.二叉树的层序遍历"
            hardLevel = 2
        }
        item {
            className = ZigzagLevelOrder::class.simpleName
            subject = "103.二叉树的锯齿形层次遍历"
            hardLevel = 2
        }
        item {
            className = MaxDepth::class.simpleName
            subject = "104.二叉树的最大深度"
            hardLevel = 1
        }
        item {
            className = LevelOrderBottom::class.simpleName
            subject = "107.二叉树的层次遍历 II"
            hardLevel = 1
        }
        item {
            className = SortedArrayToBST::class.simpleName
            subject = "108.将有序数组转换为二叉搜索树"
            hardLevel = 1
        }
        item {
            className = IsBalanced::class.simpleName
            subject = "110.平衡二叉树"
            hardLevel = 1
        }
        item {
            className = MinDepth::class.simpleName
            subject = "111.二叉树的最小深度"
            hardLevel = 1
        }
        item {
            className = HasPathSum::class.simpleName
            subject = "112.路径总和"
            hardLevel = 2
        }
        item {
            className = PreOrderTraversal::class.simpleName
            subject = "144.二叉树的前序遍历"
            hardLevel = 2
        }
        item {
            className = PostOrderTraversal::class.simpleName
            subject = "145.二叉树的后序遍历"
            hardLevel = 3
        }
        item {
            className = RightSideView::class.simpleName
            subject = "199.二叉树的右视图"
            hardLevel = 2
        }
        item {
            className = InvertTree::class.simpleName
            subject = "226.翻转二叉树"
            hardLevel = 1
        }
        item {
            className = LowestCommonAncestor::class.simpleName
            subject = "235.二叉搜索树的最近公共祖先"
            hardLevel = 1
        }
        item {
            className = LowestCommonAncestorII::class.simpleName
            subject = "236.二叉树的最近公共祖先"
            hardLevel = 2
        }
        item {
            className = BinaryTreePaths::class.simpleName
            subject = "257.二叉树的所有路径"
            hardLevel = 1
        }
        item {
            className = SumOfLeftLeaves::class.simpleName
            subject = "404.左叶子之和"
            hardLevel = 1
        }
        item {
            className = PathSumIII::class.simpleName
            subject = "437.路径总和 III"
            hardLevel = 2
        }
        item {
            className = DeleteBinarySearchTreeNode::class.simpleName
            subject = "450.删除二叉搜索树中的节点"
            hardLevel = 2
        }
        item {
            className = FindMode::class.simpleName
            subject = "501.二叉搜索树中的众数"
            hardLevel = 2
        }
        item {
            className = GetMinimumDifference::class.simpleName
            subject = "530.二叉搜索树的最小绝对差"
            hardLevel = 1
        }
        item {
            className = ConvertBST::class.simpleName
            subject = "538.把二叉搜索树转换为累加树"
            hardLevel = 2
        }
        item {
            className = DiameterOfBinaryTree::class.simpleName
            subject = "543.二叉树的直径"
            hardLevel = 1
        }
    }
}
