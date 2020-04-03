package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.BinaryTree.BinaryTreeTraverse
import com.sunfusheng.algo.Algo.BinaryTree.PrintEdgeNodes
import com.sunfusheng.algo.Algo.BinaryTree.SerializeDeserializeBinaryTree
import com.sunfusheng.algo.Algo.LinkedList.*
import com.sunfusheng.algo.Algo.StackQueue.*
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

val algoDataSource = dataSource {
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
            className = Hanoi::class.simpleName
            subject = "用栈来求解汉诺塔问题"
            hardLevel = 3
        }
        item {
            className = MaxWindow::class.simpleName
            subject = "生成窗口最大值数组"
            hardLevel = 2
        }
        item {
            className = NearLessNum::class.simpleName
            subject = "单调栈结构"
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
            className = RemoveMidNode::class.simpleName
            subject = "删除链表的中间节点和a/b处的节点"
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
        item {
            className = LinkedListPalindrome::class.simpleName
            subject = "判断一个链表是否为回文结构"
            hardLevel = 1
        }
    }

    binaryTreeChapter {
        item {
            className = BinaryTreeTraverse::class.simpleName
            subject = "用递归和非递归方式实现二叉树先序、中序、后序遍历"
            hardLevel = 3
        }
        item {
            className = PrintEdgeNodes::class.simpleName
            subject = "打印二叉树的边界节点"
            hardLevel = 2
        }
        item {
            className = SerializeDeserializeBinaryTree::class.simpleName
            subject = "二叉树的序列化和反序列化"
            hardLevel = 1
        }
    }
}
