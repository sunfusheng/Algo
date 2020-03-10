package com.cheng.leetcode.LeetCode.Array

import com.cheng.leetcode.format
import java.lang.IllegalArgumentException

/**
 * 【题目】
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 【示例】
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 返回 [0, 1]
 * 【要求】
 * 1、时间复杂度：O(n)
 * 2、空间复杂度：O(n)
 *
 * @author liwangcheng
 * @date 2020/3/10.
 */
class TwoSum {

    val nums = intArrayOf(2, 7, 11, 15)

    /**
     * 方法一：暴力法
     * 遍历每个元素x，查找是否存在一个值与target-x相等
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     */
    fun solution1(arr: IntArray, target: Int) : IntArray {
        val result = IntArray(2)
        for ((i, pre) in arr.withIndex()) {
            var j = i + 1
            while (j < arr.size) {
                val next = arr[j]
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

    /**
     * 方法二：两遍哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    fun solution2(arr: IntArray, target: Int) : IntArray {
        val result = IntArray(2)
        val map = mutableMapOf<Int, Int>()
        for ((i, pre) in arr.withIndex()) {
            map[pre] = i
        }
        for ((i, pre) in arr.withIndex()) {
            val next = target - pre
            if (map.containsKey(next) && map[next] != i) {
                result[0] = i
                result[1] = map[next]?:throw IllegalArgumentException("No tow sum solution")
                return result
            }
        }
        return result
    }

    /**
     * 方法三：一遍哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    fun solution3(arr: IntArray, target: Int) : IntArray {
        val result = IntArray(2)
        val map = mutableMapOf<Int, Int>()
        for ((i, pre) in arr.withIndex()) {
            val next = target - pre
            if (map.containsKey(next) && map[next] != i) {
                result[0] = i
                result[1] = map[next]?:throw IllegalArgumentException("No tow sum solution")
                return result
            }
            map[pre] = i
        }
        return result
    }
}

fun main() {
    val twoSum = TwoSum()
    twoSum.run {
        println("nums = [2, 7, 11, 15]")
        println("solution1(13) - ${solution1(nums, 13).format()}")
        println("solution2(9) - ${solution2(nums, 9).format()}")
        println("solution3(17) - ${solution3(nums, 17).format()}")
    }
}
