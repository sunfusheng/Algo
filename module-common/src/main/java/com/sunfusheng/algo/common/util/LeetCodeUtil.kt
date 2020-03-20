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

class LeetCodeUtil {

    companion object {
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
    }
}