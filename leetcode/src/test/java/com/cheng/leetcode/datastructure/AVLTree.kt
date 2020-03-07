package com.cheng.leetcode.datastructure

/**
 *
 * @author liwangcheng
 * @date  2019-12-27 17:28.
 */
class AVLTree {

    fun insert(root: AVLNode, data: Int) {
        // 如果插入的数据小于根节点，往左边递归插入
        if (data < root.data) {
            if (root.left != null) {
                insert(root.left!!, data)
            } else {
                root.left = AVLNode(data)
                root.left?.parent = root
            }
        }
        // 如果插入的数据小于根节点，往左边递归插入
        else {
            if (root.right != null) {
                insert(root.right!!, data)
            } else {
                root.right = AVLNode(data)
                root.right?.parent = root
            }
        }
        // 插入之后，计算平衡因子
        root.balance = calcBalance(root)
        // 左子树高，应该右旋
        if (root.balance >= 2) {
            // 右孙高，先左旋
            if (root.left?.balance == -1) {
                leftRotate(root.left)
            }
            rightRotate(root)
        }
        // 右子树高，左旋
        if (root.balance <= -2) {
            // 左孙高，先右旋
            if (root.right?.balance == 1) {
                rightRotate(root.right)
            }
            leftRotate(root)
        }
        // 调整之后，重新计算平衡因子和数的深度
        root.balance = calcBalance(root)
        root.depth = calcDepth(root)
    }

    /**
     * 右旋
     */
    fun rightRotate(p: AVLNode?) {
        if (null == p) {
            return
        }
        // 一次旋转涉及到的节点包括祖父、父亲、右儿子
        val pParent = p.parent
        val pLeftSon = p.left
        val pRightGrandSon = pLeftSon?.right
        // 左子变父
        pLeftSon?.parent = pParent
        if (pParent != null) {
            if (p == pParent.left) {
                pParent.left = pLeftSon
            } else if (p == pParent.right) {
                pParent.right = pLeftSon
            }
        }
        pLeftSon?.right = p
        p.parent = pLeftSon
        // 右孙变左孙
        p.left = pRightGrandSon
        if (pRightGrandSon != null) {
            pRightGrandSon.parent = p
        }
        p.depth = calcDepth(p)
        p.balance = calcBalance(p)
        pLeftSon?.depth = calcDepth(pLeftSon)
        pLeftSon?.balance = calcBalance(pLeftSon)
    }

    /**
     * 左旋
     */
    fun leftRotate(p : AVLNode?) {
        // 一次旋转涉及到的结点包括祖父、父亲、左儿子
        val pParent = p?.parent
        val pRightSon = p?.right
        val pLeftGroundSon = pRightSon?.left
        // 右子变父
        pRightSon?.parent = pParent
        if (pParent != null) {
            if (p == pParent.right) {
                pParent.right = pRightSon
            } else if (p == pParent.left) {
                pParent.left = pRightSon
            }
        }
        pRightSon?.left = p
        p?.parent = pRightSon
        // 左孙变右孙
        p?.right = pLeftGroundSon
        if (pLeftGroundSon != null) {
            pLeftGroundSon.parent = p
        }
        p?.depth = calcDepth(p)
        p?.balance = calcBalance(p)
        pRightSon?.depth = calcDepth(pRightSon)
        pRightSon?.balance = calcBalance(pRightSon)
    }

    /**
     * 计算平衡
     */
    fun calcBalance(p: AVLNode?) : Int {
        var leftDepth : Int = 0
        var rightDepth : Int = 0
        // 左子树深度
        leftDepth = p?.left?.depth?:0
        // 右子树深度
        rightDepth = p?.right?.depth?:0
        return leftDepth - rightDepth
    }

    /**
     * 计算深度
     */
    fun calcDepth(p: AVLNode?) : Int {
        var depth : Int = 0
        depth = p?.left?.depth?:0
        if (depth < p?.right?.depth?:0) {
            depth = p?.right?.depth?:0
        }
        depth++
        return depth
    }
}