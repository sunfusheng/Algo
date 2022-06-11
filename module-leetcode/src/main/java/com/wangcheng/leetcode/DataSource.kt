package com.wangcheng.leetcode

import com.sunfusheng.algo.common.AlgoItem
import com.sunfusheng.algo.common.ChapterDslMarker
import com.sunfusheng.algo.common.DataSourceDslMarker
import com.sunfusheng.algo.common.LEETCODE_ROOT_PATH

@ChapterDslMarker
@DataSourceDslMarker
abstract class ChapterDataSource() {
    val list = ArrayList<AlgoItem>()

    private fun <T : ChapterDataSource> addItem(chapter: T, init: AlgoItem.() -> Unit) {
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

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

open class ArrayChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Array" to "数组"
}

open class NumberChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Number" to "数字"
}

open class StringChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "String" to "字符串"
}

open class LinkedListChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "LinkedList" to "链表"
}

open class BinaryTreeChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "BinaryTree" to "二叉树"
}

open class FindSortChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "FindSort" to "查找排序"
}

open class InterviewChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Interview" to "面试题"
}

@DataSourceDslMarker
open class DataSource {
    open val lists = ArrayList<ArrayList<AlgoItem>>()

    fun arrayChapter(init: ArrayChapter.() -> Unit) {
        lists.add(ArrayChapter().apply(init).list)
    }

    fun numberChapter(init: NumberChapter.() -> Unit) {
        lists.add(NumberChapter().apply(init).list)
    }

    fun stringChapter(init: StringChapter.() -> Unit) {
        lists.add(StringChapter().apply(init).list)
    }

    fun linkedListChapter(init: LinkedListChapter.() -> Unit) {
        lists.add(LinkedListChapter().apply(init).list)
    }

    fun binaryTreeChapter(init: BinaryTreeChapter.() -> Unit) {
        lists.add(BinaryTreeChapter().apply(init).list)
    }

    fun findSortChapter(init: FindSortChapter.() -> Unit) {
        lists.add(FindSortChapter().apply(init).list)
    }

    fun interviewChapter(init: InterviewChapter.() -> Unit) {
        lists.add(InterviewChapter().apply(init).list)
    }
}

fun dataSource(init: DataSource.() -> Unit): ArrayList<ArrayList<AlgoItem>> {
    return DataSource().apply(init).lists
}

val leetCodeDataSource = dataSource {
    arrayDataSource(this)
    numberDataSource(this)
    stringDataSource(this)
    linkedListDataSource(this)
    binaryTreeDataSource(this)
    findSortDataSource(this)
    interviewDataSource(this)
}
