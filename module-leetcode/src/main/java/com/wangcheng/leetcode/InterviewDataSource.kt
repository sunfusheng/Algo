package com.wangcheng.leetcode

import com.wangcheng.leetcode.LeetCode.Interview.*

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
        item {
            className = ProducerConsumer::class.simpleName
            subject = "面试题 #.02. 生产者消费者模式"
            hardLevel = 2
        }
        item {
            className = PositiveNegative::class.simpleName
            subject = "面试题 #.03. 无序数组排序，负数在前正数在后且有序"
            hardLevel = 2
        }
        item {
            className = BinarySearch::class.simpleName
            subject = "面试题 #.04. 泛型化的二分搜索"
            hardLevel = 1
        }
    }
}