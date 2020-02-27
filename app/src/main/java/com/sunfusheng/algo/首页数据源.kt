package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.LinkedList.Josephus
import com.sunfusheng.algo.Algo.LinkedList.ReverseLinkedList
import com.sunfusheng.algo.Algo.LinkedList.ReversePartLinkedList
import com.sunfusheng.algo.Algo.StackQueue.MaxWindow
import com.sunfusheng.algo.Algo.StackQueue.MinStack
import java.io.File
import java.io.Serializable


data class AlgoItem(
    val category: Pair<String, String>,
    val className: String? = null,
    val subject: String? = null,
    val hardLevel: Int = 1
) : Serializable {
    fun getFilePath(): String {
        return "Algo/" + category.first + File.separator + className + ".java"
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


val StackQueue = "StackQueue" to "栈和队列"
val LinkedList = "LinkedList" to "链表"
val BinaryTree = "BinaryTree" to "二叉树"


fun getDataSource(): ArrayList<ArrayList<AlgoItem>> {
    val lists = ArrayList<ArrayList<AlgoItem>>()
    lists.add(getStackQueueDataSource())
    lists.add(getLinkedListDataSource())
    return lists
}


private fun getStackQueueDataSource(): ArrayList<AlgoItem> {
    val list = ArrayList<AlgoItem>()
    list.add(AlgoItem(StackQueue))
    list.add(
        AlgoItem(
            category = StackQueue,
            className = MinStack::class.simpleName,
            subject = "设计一个有getMin功能的栈",
            hardLevel = 1
        )
    )
    list.add(
        AlgoItem(
            category = StackQueue,
            className = MaxWindow::class.simpleName,
            subject = "生成窗口最大值数组",
            hardLevel = 2
        )
    )
    return list
}

private fun getLinkedListDataSource(): ArrayList<AlgoItem> {
    val list = ArrayList<AlgoItem>()
    list.add(AlgoItem(LinkedList))
    list.add(
        AlgoItem(
            category = LinkedList,
            className = ReverseLinkedList::class.simpleName,
            subject = "反转单向链表和双向链表",
            hardLevel = 1
        )
    )
    list.add(
        AlgoItem(
            category = LinkedList,
            className = ReversePartLinkedList::class.simpleName,
            subject = "反转部分单向链表",
            hardLevel = 1
        )
    )
    list.add(
        AlgoItem(
            category = LinkedList,
            className = Josephus::class.simpleName,
            subject = "环形单链表的约瑟夫问题",
            hardLevel = 1
        )
    )
    return list
}
