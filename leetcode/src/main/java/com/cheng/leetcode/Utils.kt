package com.cheng.leetcode

import java.lang.StringBuilder

/**
 * @author liwangcheng
 * @date 2020/3/10.
 */
fun IntArray.format() : String {
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

class Utils {

    companion object {
        @JvmStatic
        fun log(s: String) {
            println(s)
        }
    }
}