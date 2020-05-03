package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.LinkedList.*

/**
 * @author sunfusheng
 * @since 2020/5/3
 */
fun linkedListDataSource(dataSource: DataSource) {
    dataSource.linkedListChapter {
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
        item {
            className = Partition::class.simpleName
            subject = "将单向链表按某值划分成左边小、中间相等、右边大的形式"
            hardLevel = 2
        }
        item {
            className = CopyRandomLinkedList::class.simpleName
            subject = "复制含有随机指针节点的链表"
            hardLevel = 2
        }
        item {
            className = AddTwoLinkedList::class.simpleName
            subject = "两个单链表生成相加链表"
            hardLevel = 1
        }
        item {
            className = IntersectNode::class.simpleName
            subject = "两个单链表相交的一系列问题"
            hardLevel = 4
        }
        item {
            className = ReverseKNodes::class.simpleName
            subject = "将单链表的每K个节点之间逆序"
            hardLevel = 2
        }
    }
}