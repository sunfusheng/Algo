package com.sunfusheng.algo.common.util

/**
 * @author liwangcheng
 * @date 2020/3/10.
 */
fun IntArray.format(): String {
    val buf = StringBuilder()
    buf.append("[")
    for (i in this) {
        buf.append(i)
        buf.append(",")
    }
    buf.deleteCharAt(buf.lastIndexOf(","))
    buf.append("]")
    return buf.toString()
}

object LeetCodeUtil {

    @JvmStatic
    fun log(s: String) {
        print(s)
    }

    @JvmStatic
    fun logln(s: String) {
        println(s)
    }

    @JvmStatic
    fun logln(arr: IntArray) {
        logln(arr.format())
    }

    @JvmStatic
    fun isEmpty(str: String?): Boolean {
        return null == str || str.isEmpty()
    }

    @JvmStatic
    fun isEmpty(strs: Array<String>?): Boolean {
        return null == strs || strs.isEmpty()
    }

    @JvmStatic
    fun isEmpty(nums: IntArray?): Boolean {
        return null == nums || nums.isEmpty()
    }

    @JvmStatic
    fun isEmpty(nums: CharArray?): Boolean {
        return null == nums || nums.isEmpty()
    }

    @JvmStatic
    fun length(str: String?): Int {
        return str?.length ?: 0
    }
}