package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.Sort.*

/**
 * @author sunfusheng
 * @since 2020/5/3
 */
fun sortDataSource(dataSource: DataSource) {
    dataSource.sortChapter {
        item {
            className = BubbleSort::class.simpleName
            subject = "冒泡排序"
            hardLevel = 1
        }
        item {
            className = SelectionSort::class.simpleName
            subject = "选择排序"
            hardLevel = 1
        }
        item {
            className = InsertionSort::class.simpleName
            subject = "插入排序"
            hardLevel = 2
        }
        item {
            className = CountSort::class.simpleName
            subject = "计数排序"
            hardLevel = 2
        }
        item {
            className = QuickSort::class.simpleName
            subject = "快速排序"
            hardLevel = 3
        }
        item {
            className = MergeSort::class.simpleName
            subject = "归并排序"
            hardLevel = 3
        }
        item {
            className = HeapSort::class.simpleName
            subject = "堆排序"
            hardLevel = 4
        }
    }
}