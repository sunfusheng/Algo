package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.BinaryTree.BinaryTreeTraverse
import com.sunfusheng.algo.Algo.BinaryTree.PrintEdgeNodes
import com.sunfusheng.algo.Algo.BinaryTree.SerializeDeserializeBinaryTree

/**
 * @author sunfusheng
 * @since 2020/5/3
 */
fun binaryTreeDataSource(dataSource: DataSource) {
    dataSource.binaryTreeChapter {
        item {
            className = BinaryTreeTraverse::class.simpleName
            subject = "用递归和非递归方式实现二叉树先序、中序、后序遍历"
            hardLevel = 3
        }
        item {
            className = PrintEdgeNodes::class.simpleName
            subject = "打印二叉树的边界节点"
            hardLevel = 2
        }
        item {
            className = SerializeDeserializeBinaryTree::class.simpleName
            subject = "二叉树的序列化和反序列化"
            hardLevel = 1
        }
    }
}