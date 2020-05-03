package com.wangcheng.leetcode

import com.wangcheng.leetcode.LeetCode.FindSort.FirstBadVersion

/**
 * @author liwangcheng
 * @date 2020/5/1.
 */
object FindSortChapterData {

    fun simpleFindSort(dataSource: DataSource) {
        dataSource.findSortChapter {
            item {
                className = FirstBadVersion::class.simpleName
                subject = "278.第一个错误的版本"
                hardLevel = 1
            }
        }
    }
}
