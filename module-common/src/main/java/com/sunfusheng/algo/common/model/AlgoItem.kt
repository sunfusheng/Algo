package com.sunfusheng.algo.common

import java.io.File
import java.io.Serializable

/**
 * @author sunfusheng
 * @since 2020/3/11
 */
@DslMarker
annotation class DataSourceDslMarker

@DslMarker
annotation class ChapterDslMarker

@ChapterDslMarker
open class AlgoItem(
    open var rootPath: String = "Algo/",
    open var chapter: Pair<String, String>,
    open var className: String? = null,
    open var subject: String? = null,
    open var hardLevel: Int = 1,
    open var suffix: String = ".java"
) : Serializable {
    fun getFilePath(): String {
        return rootPath + chapter.first + File.separator + className + suffix
    }

    fun getHardLevel(): String {
        var level = hardLevel
        if (hardLevel < 1) level = 1
        if (hardLevel > 4) level = 4
        val sb = StringBuilder("难度：")
        for (i in 1..4) {
            sb.append(if (i <= level) "★" else "☆")
        }
        return sb.toString()
    }
}