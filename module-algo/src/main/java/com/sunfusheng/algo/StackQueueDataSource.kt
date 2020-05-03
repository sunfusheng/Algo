package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.StackQueue.*

/**
 * @author sunfusheng
 * @since 2020/5/3
 */
fun stackQueueDataSource(dataSource: DataSource) {
    dataSource.stackQueueChapter {
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
        item {
            className = MaxRecSize::class.simpleName
            subject = "求最大子矩阵的大小"
            hardLevel = 3
        }
    }
}