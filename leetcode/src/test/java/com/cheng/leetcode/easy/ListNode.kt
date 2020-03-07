package com.cheng.leetcode.easy

/**
 *
 * @author liwangcheng
 * @date  2019-12-20 16:55.
 */
class ListNode(var v: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return "$v -> ${next?.v}"
    }
}