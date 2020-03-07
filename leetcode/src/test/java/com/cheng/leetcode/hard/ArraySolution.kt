package com.cheng.leetcode.hard

import org.junit.Test
import kotlin.math.min

/**
 *
 * @author liwangcheng
 * @date  2019-12-21 15:48.
 */
class ArraySolution {

    @Test
    fun testFindMediaSortedArrays() {
        findMediaSortedArrays(intArrayOf(1, 3), intArrayOf(2))
    }

    /*
    给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
    请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
    你可以假设 nums1 和 nums2 不会同时为空。

    示例 1:
    nums1 = [1, 3]
    nums2 = [2]
    则中位数是 2.0
    示例 2:
    nums1 = [1, 2]
    nums2 = [3, 4]
    则中位数是 (2 + 3)/2 = 2.5
     */
    /**
     * 解题思路
     * 1.首先，让我们在任意位置i将A（长度为m）数组划分为两个部分
     *            leftA            |                rightA
     *   A[0],A[1],...      A[i-1] |  A[i],A[i+1],...A[m-1]
     * 由于A有m个元素，所以有m+1种划分方式（i = 0 ~ m）
     * 我们知道len(leftA) = i, len(rightA) = m - i
     * 注意：当i = 0时，leftA是空集，而当i = m时，rightA是空集
     *
     * 2.采用同样的方式，将B也划分为两部分：
     *            leftB            |                rightB
     *   B[0],B[1],...      B[j-1] |   B[j],B[j+1],...B[n-1]
     * 我们知道len(leftB) = j, len(rightB) = n - j
     * 将leftA和leftB放入一个集合，将rightA和rightB放入一个集合。
     * 再把这两个集合分别命名为leftPart和rightPart
     *             leftPart         |                rightPart
     *   A[0],A[1],...      A[i-1] |  A[i],A[i+1],...A[m-1]
     *   B[0],B[1],...      B[j-1] |  B[j],B[j+1],...B[n-1]
     * 如果我们可以确认：
     * 1.len(leftPart) == len(rightPart) ==> 该条件在 m+n 为奇数时，该推理不成立
     * 2.max(leftPart) <= min(rightPart)
     *
     * median = (max(leftPart) + min(rightPart)) / 2; // 目标结果
     *
     * 要确保这两个条件满足：
     * 1.i + j = m - i + n - j（或 m - i + n - j + 1）如果 n >= m，
     * 只需要使 i = 0 ~ m，j = (m + n + 1) / 2 - i ==> 该条件在m+n为奇数/偶数时，该推理都成立
     * 2.B[j] >= A[i-1] && A[i] >= B[j-1]
     *
     * 注意：
     * 1.临界条件：i = 0，j = 0，i = m，j = n；需要考虑
     * 2.为什么 n >= m ？由于 0 <= i <= m 且 j = (m + n + 1) / 2 - i，必须确保j不能为负数
     *
     * 按照以下步骤进行二叉树搜索（PS：应该是二分查找吧）
     * 1.设 iMin = 0, iMax = m，然后开始在[iMin, iMax]中进行搜索
     * 2.令 i = (iMin + iMax) / 2，j = (m + n + 1) / 2 - i
     * 3.现在我们有len(leftPart) = len(rightPart)。而我们只会遇到三种情况：
     *  a.B[j] >= A[i-1] 并且 A[i] >= B[j-1] 满足条件
     *  b.B[j-1] > A[i]，此时应该把i增大，即iMin = i + 1
     *  c.A[i-1] > B[j]，此时应该把i减小，即iMax = i - 1
     */
    fun findMediaSortedArrays(a: IntArray, b: IntArray): Double {
        var A = a
        var B = b
        var m = A.size
        var n = B.size
        if (m > n) { // to ensure m <= n
            var temp = A
            A = B
            B = temp
            var tmp = m
            m = n
            n = tmp
        }
        val isUneven = (m + n) % 2 == 1
        var iMin = 0
        var iMax = m
        var halfLen = (m + n + 1) / 2
        while (iMin <= iMax) {
            var i = (iMin + iMax) / 2
            var j = halfLen - i
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1 // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1 // i is too big
            } else { // i is perfect
                var maxLeft = 0
                if (i == 0) {
                    maxLeft = B[j - 1]
                } else if (j == 0) {
                    maxLeft = A[i - 1]
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1])
                }
                if (isUneven) {
                    return maxLeft.toDouble()
                }
                var minRight = 0
                if (i == m) {
                    minRight = B[j]
                } else if (j == n) {
                    minRight = A[i]
                } else {
                    minRight = Math.min(B[j], A[i])
                }
                return (maxLeft + minRight) / 2.0
            }
        }
        return 0.0
    }
}