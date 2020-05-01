package com.wangcheng.leetcode.LeetCode.FindSort

import com.wangcheng.leetcode.DataSource

/**
 * @author liwangcheng
 * @date 2020/5/1.
 */
class FindSortChapterData {

    companion object {
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

}
