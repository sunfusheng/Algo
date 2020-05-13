package com.wangcheng.leetcode

import com.wangcheng.leetcode.LeetCode.Interview.RotateMatrix
import com.wangcheng.leetcode.LeetCode.Interview.UnorderedArrayMedian

/**
 * @author sunfusheng
 * @since 2020/5/11
 */
fun interviewDataSource(dataSource: DataSource) {
    dataSource.interviewChapter {
        item {
            className = RotateMatrix::class.simpleName
            subject = "面试题 01.07. 旋转矩阵"
            hardLevel = 2
        }
        item {
            className = UnorderedArrayMedian::class.simpleName
            subject = "面试题 #.01. 无序数组的中位数"
            hardLevel = 2
        }
    }
}