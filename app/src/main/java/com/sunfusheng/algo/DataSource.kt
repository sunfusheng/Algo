package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.LinkedList.*
import com.sunfusheng.algo.Algo.StackQueue.*
import java.io.File
import java.io.Serializable

@ChapterDslMarker
open class AlgoItem(
    open var chapter: Pair<String, String>,
    open var className: String? = null,
    open var subject: String? = null,
    open var hardLevel: Int = 1
) : Serializable {
    fun getFilePath(): String {
        return "Algo/" + chapter.first + File.separator + className + ".java"
    }

    fun getHardLevel(): String {
        var level = hardLevel
        if (hardLevel < 1) level = 1
        if (hardLevel > 4) level = 4
        val sb = StringBuilder("难度：")
        for (i in 1..4) {
            sb.append(if (i <= level) "★" else "☆")
        }
        return sb.toString()
    }
}

@DslMarker
annotation class DataSourceDslMarker

@DslMarker
annotation class ChapterDslMarker

@ChapterDslMarker
@DataSourceDslMarker
abstract class ChapterDataSource() {
    val list = ArrayList<AlgoItem>()

    protected fun <T : ChapterDataSource> addItem(chapter: T, init: AlgoItem.() -> Unit) {
        if (chapter.list.isEmpty()) {
            chapter.list.add(AlgoItem(getChapter()))
        }
        chapter.list.add(AlgoItem(getChapter()).apply(init))
    }

    protected abstract fun getChapter(): Pair<String, String>
}

open class StackQueueChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "StackQueue" to "栈和队列"

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

open class LinkedListChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "LinkedList" to "链表"

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

open class BinaryTreeChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "BinaryTree" to "二叉树"

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
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
}

fun dataSource(init: DataSource.() -> Unit): ArrayList<ArrayList<AlgoItem>> {
    return DataSource().apply(init).lists
}

val lists = dataSource {
    stackQueueChapter {
        item {
            className = MinStack::class.simpleName
            subject = "设计一个有getMin功能的栈"
            hardLevel = 1
        }
        item {
            className = TwoStacksQueue::class.simpleName
            subject = "用两个栈实现队列"
            hardLevel = 1
        }
        item {
            className = RecursionReverseStack::class.simpleName
            subject = "如何仅用递归函数和栈操作逆序一个栈"
            hardLevel = 2
        }
        item {
            className = CatDogQueue::class.simpleName
            subject = "猫狗队列"
            hardLevel = 1
        }
        item {
            className = SortStackByStack::class.simpleName
            subject = "用一个栈实现另一个栈的排序"
            hardLevel = 1
        }
        item {
            className = MaxWindow::class.simpleName
            subject = "生成窗口最大值数组"
            hardLevel = 2
        }
    }

    linkedListChapter {
        item {
            className = PrintCommonPart::class.simpleName
            subject = "打印两个有序链表的公共部分"
            hardLevel = 1
        }
        item {
            className = RemoveLastKthNode::class.simpleName
            subject = "删除单链表和双链表倒数第K个节点"
            hardLevel = 1
        }
        item {
            className = ReverseLinkedList::class.simpleName
            subject = "反转单向链表和双向链表"
            hardLevel = 1
        }
        item {
            className = ReversePartLinkedList::class.simpleName
            subject = "反转部分单向链表"
            hardLevel = 1
        }
        item {
            className = Josephus::class.simpleName
            subject = "环形单链表的约瑟夫问题"
            hardLevel = 1
        }
    }

    binaryTreeChapter {

    }
}
