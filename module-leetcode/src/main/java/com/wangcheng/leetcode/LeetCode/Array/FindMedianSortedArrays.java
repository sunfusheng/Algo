package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 【示例】
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 * 【要求】
 * 算法的时间复杂度为 O(log(m + n))
 * 【提示】
 * 在统计中，中位数被用来：将一个集合划分为两个长度相等的子集，
 * 其中一个子集中的元素总是大于另一个子集中的元素。
 *
 * 【解法】
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-shu-b/
 * @author liwangcheng
 * @date 2020/3/14.
 */
public class FindMedianSortedArrays {

    public double solution(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        // to ensure m<=n
        if (m > n) {
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                // i is too small
                iMin = i + 1;
            }
            else if (i > iMin && A[i-1] > B[j]) {
                // i is too big
                iMax = i - 1;
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        FindMedianSortedArrays find = new FindMedianSortedArrays();
        double mid = find.solution(new int[]{1, 3}, new int[]{2});
        LeetCodeUtil.logln("[1, 3],[2] 的中位数为：" + mid);
        mid = find.solution(new int[]{1, 2}, new int[]{3, 4});
        LeetCodeUtil.logln("[1, 2],[3, 4] 的中位数为：" + mid);
    }
}
