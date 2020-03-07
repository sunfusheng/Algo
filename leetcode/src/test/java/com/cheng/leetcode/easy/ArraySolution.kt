package com.cheng.leetcode.easy

import org.junit.Test

/**
 *
 * @author liwangcheng
 * @date  2019-12-20 16:00.
 */
class ArraySolution {

    /*
    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，
    并返回他们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

    示例:
    给定 nums = [2, 7, 11, 15], target = 9
    因为 nums[0] + nums[1] = 2 + 7 = 9
    所以返回 [0, 1]
     */
    @Test
    fun testTwoSum() {
//        var result = twoSum1(intArrayOf(3, 2, 4, 7, 11, 15), 9)
//        var result = twoSum2(intArrayOf(3, 2, 4, 7, 11, 15), 9)
        var result = twoSum3(intArrayOf(3, 2, 4, 7, 11, 15), 9)
        print("[")
        for (num in result) {
            print("$num,")
        }
        print("]")
    }

    fun twoSum1(nums: IntArray, target: Int): IntArray {
        val result = IntArray(2)
        for ((i, pre) in nums.withIndex()) {
            var j = i + 1
            while (j < nums.size) {
                var next = nums[j]
                if (pre + next == target) {
                    result[0] = i
                    result[1] = j
                    return result
                } else {
                    j++
                }
            }
        }
        return result
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
        val result = IntArray(2)
        val map = mutableMapOf<Int, Int>()
        for ((i, pre) in nums.withIndex()) {
            map[pre] = i
        }
        for ((i, pre) in nums.withIndex()) {
            var next = target - pre
            if (map.containsKey(next) && map[next] != i) {
                result[0] = i
                map[next]?.let { result[1] = it }
            }
        }
        return result
    }

    fun twoSum3(nums: IntArray, target: Int): IntArray {
        val result = IntArray(2)
        val map = mutableMapOf<Int, Int>()
        for ((i, pre) in nums.withIndex()) {
            val next = target - pre
            if (map.containsKey(next)) {
                result[0] = i
                map[next]?.let { result[1] = it }
                return result
            }
            map[pre] = i
        }
        return result
    }

    /*
    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
    并且它们的每个节点只能存储 一位 数字。
    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    示例：
    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807
     */
    @Test
    fun testAddTwoNumbers() {
        var l1: ListNode? = ListNode(2)
        l1?.next = ListNode(4)
        l1?.next?.next = ListNode(3)

        var l2: ListNode? = ListNode(5)
        l2?.next = ListNode(6)
        l2?.next?.next = ListNode(4)

        var ll = addTwoNumbers1(l1, l2)
        while (ll != null) {
            print(ll.v)
            ll = ll?.next
        }
    }

    fun addTwoNumbers1(l1: ListNode?, l2: ListNode?): ListNode? {
        var carry = 0
        val root = ListNode(0)
        var ll: ListNode? = root
        var vl1 = l1
        var vl2 = l2
        while (vl1 != null || vl2 != null) {
            var x = vl1?.v ?: 0
            var y = vl2?.v ?: 0
            var sum = carry + x + y
            carry = sum / 10
            ll?.next = ListNode(sum % 10)
            ll = ll?.next
            if (vl1 != null) vl1 = vl1.next
            if (vl2 != null) vl2 = vl2.next
        }
        if (carry > 0) {
            ll?.next = ListNode(carry)
        }
        return root.next
    }
}