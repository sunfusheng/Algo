package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.Sort.BubbleSort
import com.sunfusheng.algo.Algo.Sort.HeapSort
import com.sunfusheng.algo.Algo.Sort.QuickSort

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
            className = QuickSort::class.simpleName
            subject = "快速排序"
            hardLevel = 3
        }
        item {
            className = HeapSort::class.simpleName
            subject = "堆排序"
            hardLevel = 4
        }
    }
}