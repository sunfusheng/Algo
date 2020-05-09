package com.wangcheng.leetcode

import com.wangcheng.leetcode.LeetCode.LinkedList.*

/**
 * @author liwangcheng
 * @date 2020/5/1.
 */
fun linkedListDataSource(dataSource: DataSource) {
    dataSource.linkedListChapter {
        item {
            className = AddTwoNumbers::class.simpleName
            subject = "2.两数相加"
            hardLevel = 2
        }
        item {
            className = MergeTwoLists::class.simpleName
            subject = "21.合并两个有序链表"
            hardLevel = 1
        }
        item {
            className = MergeKLists::class.simpleName
            subject = "23.合并K个排序链表"
            hardLevel = 3
        }
        item {
            className = DeleteDuplicates::class.simpleName
            subject = "83.删除排序链表中的重复元素"
            hardLevel = 1
        }
        item {
            className = HasCycle::class.simpleName
            subject = "141.环形链表"
            hardLevel = 1
        }
        item {
            className = MinStack::class.simpleName
            subject = "155.最小栈"
            hardLevel = 1
        }
        item {
            className = LRUCache::class.simpleName
            subject = "146.LRU缓存机制"
            hardLevel = 2
        }
        item {
            className = GetIntersectionNode::class.simpleName
            subject = "160.相交链表"
            hardLevel = 1
        }
        item {
            className = RemoveElements::class.simpleName
            subject = "203.移除链表元素"
            hardLevel = 1
        }
        item {
            className = ReverseList::class.simpleName
            subject = "206.反转链表"
            hardLevel = 1
        }
        item {
            className = MyStack::class.simpleName
            subject = "225.用队列实现栈"
            hardLevel = 1
        }
        item {
            className = MyQueue::class.simpleName
            subject = "232.用栈实现队列"
            hardLevel = 1
        }
        item {
            className = IsPalindrome::class.simpleName
            subject = "234.回文链表"
            hardLevel = 1
        }
        item {
            className = DeleteNode::class.simpleName
            subject = "237.删除链表中的节点"
            hardLevel = 1
        }
        item {
            className = AddTwoNumbersII::class.simpleName
            subject = "445.两数相加 II"
            hardLevel = 2
        }
    }
}