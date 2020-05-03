package com.sunfusheng.algo

import com.sunfusheng.algo.common.AlgoItem
import com.sunfusheng.algo.common.ChapterDslMarker
import com.sunfusheng.algo.common.DataSourceDslMarker

@ChapterDslMarker
@DataSourceDslMarker
abstract class ChapterDataSource() {
    val list = ArrayList<AlgoItem>()

    private fun <T : ChapterDataSource> addItem(chapter: T, init: AlgoItem.() -> Unit) {
        if (chapter.list.isEmpty()) {
            chapter.list.add(AlgoItem(chapter = getChapter()))
        }
        chapter.list.add(AlgoItem(chapter = getChapter()).apply(init))
    }

    protected abstract fun getChapter(): Pair<String, String>

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

open class StackQueueChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "StackQueue" to "栈和队列问题"
}

open class LinkedListChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "LinkedList" to "链表问题"
}

open class BinaryTreeChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "BinaryTree" to "二叉树问题"
}

open class SortChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Sort" to "排序算法"
}

@DataSourceDslMarker
open class DataSource {
    open val lists = ArrayList<ArrayList<AlgoItem>>()

    fun stackQueueChapter(init: StackQueueChapter.() -> Unit) {
        lists.add(StackQueueChapter().apply(init).list)
    }

    fun linkedListChapter(init: LinkedListChapter.() -> Unit) {
        lists.add(LinkedListChapter().apply(init).list)
    }

    fun binaryTreeChapter(init: BinaryTreeChapter.() -> Unit) {
        lists.add(BinaryTreeChapter().apply(init).list)
    }

    fun sortChapter(init: SortChapter.() -> Unit) {
        lists.add(SortChapter().apply(init).list)
    }
}

fun dataSource(init: DataSource.() -> Unit): ArrayList<ArrayList<AlgoItem>> {
    return DataSource().apply(init).lists
}

val algoDataSource = dataSource {
    stackQueueDataSource(this)

    linkedListDataSource(this)

    binaryTreeDataSource(this)

    sortDataSource(this)
}
