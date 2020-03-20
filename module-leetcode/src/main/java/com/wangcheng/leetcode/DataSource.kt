package com.wangcheng.leetcode

import com.sunfusheng.algo.common.AlgoItem
import com.sunfusheng.algo.common.ChapterDslMarker
import com.sunfusheng.algo.common.DataSourceDslMarker
import com.sunfusheng.algo.common.LEETCODE_ROOT_PATH
import com.wangcheng.leetcode.LeetCode.Array.FindMedianSortedArrays
import com.wangcheng.leetcode.LeetCode.Array.RemoveDuplicates
import com.wangcheng.leetcode.LeetCode.Array.RemoveElement
import com.wangcheng.leetcode.LeetCode.Array.TwoSum
import com.wangcheng.leetcode.LeetCode.LinkedList.AddTwoNumbers
import com.wangcheng.leetcode.LeetCode.LinkedList.MergeTwoLists
import com.wangcheng.leetcode.LeetCode.Number.PalindromeNum
import com.wangcheng.leetcode.LeetCode.Number.ReverseInt
import com.wangcheng.leetcode.LeetCode.Number.RomeToInt
import com.wangcheng.leetcode.LeetCode.String.*

@ChapterDslMarker
@DataSourceDslMarker
abstract class ChapterDataSource() {
    val list = ArrayList<AlgoItem>()

    protected fun <T : ChapterDataSource> addItem(chapter: T, init: AlgoItem.() -> Unit) {
        if (chapter.list.isEmpty()) {
            chapter.list.add(AlgoItem(chapter = getChapter()))
        }
        chapter.list.add(
            AlgoItem(
                rootPath = LEETCODE_ROOT_PATH,
                chapter = getChapter()
            ).apply(init)
        )
    }

    protected abstract fun getChapter(): Pair<String, String>
}

open class ArrayChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Array" to "数组"

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

open class LinkedListChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "LinkedList" to "链表"

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

open class StringChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "String" to "字符串"

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

open class NumberChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Number" to "数字"

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

@DataSourceDslMarker
open class DataSource {
    open val lists = ArrayList<ArrayList<AlgoItem>>()

    fun arrayChapter(init: ArrayChapter.() -> Unit) {
        lists.add(ArrayChapter().apply(init).list)
    }

    fun linkedListChapter(init: LinkedListChapter.() -> Unit) {
        lists.add(LinkedListChapter().apply(init).list)
    }

    fun stringChapter(init: StringChapter.() -> Unit) {
        lists.add(StringChapter().apply(init).list)
    }

    fun numberChapter(init: NumberChapter.() -> Unit) {
        lists.add(NumberChapter().apply(init).list)
    }

}

fun dataSource(init: DataSource.() -> Unit): ArrayList<ArrayList<AlgoItem>> {
    return DataSource().apply(init).lists
}

val leetCodeDataSource = dataSource {
    arrayChapter {
        item {
            className = TwoSum::class.simpleName
            subject = "1.两数之和"
            hardLevel = 1
        }
        item {
            className = FindMedianSortedArrays::class.simpleName
            subject = "4.寻找两个有序数组的中位数"
            hardLevel = 3
        }
        item {
            className = RemoveDuplicates::class.simpleName
            subject = "26. 删除排序数组中的重复项"
            hardLevel = 1
        }
        item {
            className = RemoveElement::class.simpleName
            subject = "27. 移除元素"
            hardLevel = 1
        }
    }

    linkedListChapter {
        item {
            className = AddTwoNumbers::class.simpleName
            subject = "2.两数相加"
            hardLevel = 2
        }
        item {
            className = MergeTwoLists::class.simpleName
            subject = "21. 合并两个有序链表"
            hardLevel = 1
        }
    }

    stringChapter {
        item {
            className = LengthOfLongestSubstring::class.simpleName
            subject = "3.无重复字符的最长子串"
            hardLevel = 2
        }
        item {
            className = LongestPalindrome::class.simpleName
            subject = "5.最长回文子串"
            hardLevel = 2
        }
        item {
            className = ZShapedConvert::class.simpleName
            subject = "6.Z字形变换"
            hardLevel = 2
        }
        item {
            className = LongestCommonPrefix::class.simpleName
            subject = "14. 最长公共前缀"
            hardLevel = 1
        }
        item {
            className = ValidBrackets::class.simpleName
            subject = "20. 有效的括号"
            hardLevel = 1
        }
    }

    numberChapter {
        item {
            className = ReverseInt::class.simpleName
            subject = "7.反转整数"
            hardLevel = 1
        }
        item {
            className = PalindromeNum::class.simpleName
            subject = "9.回文数判断"
            hardLevel = 1
        }
        item {
            className = RomeToInt::class.simpleName
            subject = "13.罗马数字转整数"
            hardLevel = 1
        }
    }
}
