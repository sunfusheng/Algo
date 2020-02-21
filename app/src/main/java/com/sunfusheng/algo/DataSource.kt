package com.sunfusheng.algo

import com.sunfusheng.algo.Algo.StackQueue.生成窗口最大值数组

/**
 * @author sunfusheng
 * @since 2020-02-21
 */
fun getDataSource(): ArrayList<ArrayList<String?>> {
    val lists = ArrayList<ArrayList<String?>>()
    lists.add(getStackQueueDataSource())
//    lists.add(getLinkedListDataSource())
    return lists;
}

private fun getStackQueueDataSource(): ArrayList<String?> {
    val list = ArrayList<String?>()
    list.add("栈和队列")
    list.add(生成窗口最大值数组::class.simpleName)
    return list
}

private fun getLinkedListDataSource(): ArrayList<String> {
    val list = ArrayList<String>()
    list.add("链表")
    return list
}