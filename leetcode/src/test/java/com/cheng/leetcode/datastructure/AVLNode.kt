package com.cheng.leetcode.datastructure

/**
 *
 * @author liwangcheng
 * @date  2019-12-27 17:24.
 */
class AVLNode {
    /**
     * 保存节点数据
     */
    var data: Int = 0
    /**
     * 保存节点深度
     */
    var depth: Int = 0
    /**
     * 是否平衡
     */
    var balance: Int = 0
    var parent: AVLNode? = null
    var left: AVLNode? = null
    var right: AVLNode? = null

    constructor(data: Int) {
        this.data = data
        this.depth = 1
        this.balance = 0
        this.left = null
        this.right = null
    }
}