package com.wangcheng.leetcode

import com.wangcheng.leetcode.LeetCode.Array.TwoSum
import com.sunfusheng.algo.common.AlgoItem
import com.sunfusheng.algo.common.ChapterDslMarker
import com.sunfusheng.algo.common.DataSourceDslMarker
import com.wangcheng.leetcode.LeetCode.LinkedList.AddTwoNumbers

@ChapterDslMarker
@DataSourceDslMarker
abstract class ChapterDataSource() {
    val list = ArrayList<AlgoItem>()

    protected fun <T : ChapterDataSource> addItem(chapter: T, init: AlgoItem.() -> Unit) {
        if (chapter.list.isEmpty()) {
            chapter.list.add(AlgoItem(chapter = getChapter()))
        }
        chapter.list.add(AlgoItem(rootPath = "LeetCode/", chapter = getChapter()).apply(init))
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

@DataSourceDslMarker
open class DataSource {
    open val lists = ArrayList<ArrayList<AlgoItem>>()

    fun arrayChapter(init: ArrayChapter.() -> Unit) {
        lists.add(ArrayChapter().apply(init).list)
    }

    fun linkedListChapter(init: LinkedListChapter.() -> Unit) {
        lists.add(LinkedListChapter().apply(init).list)
    }

}

fun dataSource(init: DataSource.() -> Unit): ArrayList<ArrayList<AlgoItem>> {
    return DataSource().apply(init).lists
}

val leetCodeDataSource = dataSource {
    arrayChapter {
        item {
            className = TwoSum::class.simpleName
            subject = "两数之和"
            hardLevel = 1
        }
    }

    linkedListChapter {
        item {
            className = AddTwoNumbers::class.simpleName
            subject = "两数相加"
            hardLevel = 2
        }
    }
}
