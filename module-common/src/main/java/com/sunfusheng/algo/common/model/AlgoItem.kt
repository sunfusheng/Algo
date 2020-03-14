package com.sunfusheng.algo.common

import java.io.File
import java.io.Serializable

/**
 * @author sunfusheng
 * @since 2020/3/11
 */

const val ALGO_ROOT_PATH = "Algo/"
const val LEETCODE_ROOT_PATH = "LeetCode/"

@DslMarker
annotation class DataSourceDslMarker

@DslMarker
annotation class ChapterDslMarker

@ChapterDslMarker
data class AlgoItem(
    var rootPath: String = ALGO_ROOT_PATH,
    var chapter: Pair<String, String>,
    var className: String? = null,
    var subject: String? = null,
    var hardLevel: Int = 1,
    var suffix: String = ".java"
) : Serializable {
    fun getFilePath(): String {
        return rootPath + chapter.first + File.separator + className + suffix
    }

    fun getAlgoHardLevel(): String {
        var level = hardLevel
        if (hardLevel < 1) level = 1
        if (hardLevel > 4) level = 4
        val sb = StringBuilder("难度：")
        for (i in 1..4) {
            sb.append(if (i <= level) "★" else "☆")
        }
        return sb.toString()
    }

    // <=1: 简单, ==2: 中等, >=3: 困难
    fun getLeetCodeHardLevel(): String {
        val sb = StringBuilder("难度：")
        if (hardLevel <= 1) sb.append("简单")
        else if (hardLevel >= 3) sb.append("困难")
        else sb.append("中等")
        return sb.toString()
    }
}