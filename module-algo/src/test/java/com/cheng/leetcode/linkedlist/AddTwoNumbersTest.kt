package com.cheng.leetcode.linkedlist

import com.cheng.leetcode.easy.ListNode
import org.junit.Test

/**
 * @author liwangcheng
 * @date 2020/3/10.
 */
class AddTwoNumbersTest {

    private class ListNode(var v: Int) {
        var next: ListNode? = null
    }

    private fun solution1(l1: ListNode?, l2: ListNode?): ListNode? {
        var carry = 0
        val root = ListNode(0)
        var ll: ListNode? = root
        var vl1 = l1
        var vl2 = l2
        while (vl1 != null || vl2 != null) {
            val v1 = vl1?.v?:0
            val v2 = vl2?.v?:0
            val sum = carry + v1 + v2
            carry = sum / 10
            ll?.next = ListNode(sum % 10)
            ll = ll?.next
            vl1 = vl1?.next
            vl2 = vl2?.next
        }
        return root.next
    }

    @Test
    fun test() {
        val l1: ListNode? = ListNode(2)
        l1?.next = ListNode(4)
        l1?.next?.next = ListNode(3)

        val l2: ListNode? = ListNode(5)
        l2?.next = ListNode(6)
        l2?.next?.next = ListNode(4)

        var ll = solution1(l1, l2)
        while (ll != null) {
            print(ll.v)
            ll = ll.next
        }
    }
}