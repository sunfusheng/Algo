package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.StackQueue.MaxWindow
import java.io.File
import java.io.Serializable

/**
 * @author sunfusheng
 * @since 2020-02-21
 */
data class AlgoItem(
    val category: Pair<String, String>,
    val className: String? = null,
    val subject: String? = null,
    val hardLevel: Int = 1
) : Serializable {

    fun getFilePath(): String {
        return "Algo/" + category.first + File.separator + className + ".java"
    }
}


val StackQueue = "StackQueue" to "栈和队列"


fun getDataSource(): ArrayList<ArrayList<AlgoItem>> {
    val lists = ArrayList<ArrayList<AlgoItem>>()
    lists.add(getStackQueueDataSource())
    return lists
}


private fun getStackQueueDataSource(): ArrayList<AlgoItem> {
    val list = ArrayList<AlgoItem>()
    list.add(AlgoItem(StackQueue))
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
