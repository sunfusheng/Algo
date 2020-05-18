package com.wangcheng.leetcode

import com.wangcheng.leetcode.LeetCode.FindSort.FindRadius
import com.wangcheng.leetcode.LeetCode.FindSort.FirstBadVersion

/**
 * @author liwangcheng
 * @date 2020/5/1.
 */
fun findSortDataSource(dataSource: DataSource) {
    dataSource.findSortChapter {
        item {
            className = FirstBadVersion::class.simpleName
            subject = "278.第一个错误的版本"
            hardLevel = 1
        }
        item {
            className = FindRadius::class.simpleName
            subject = "475.供暖器"
            hardLevel = 2
        }
    }
}