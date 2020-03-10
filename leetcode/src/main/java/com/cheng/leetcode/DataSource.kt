package com.cheng.leetcode

import com.cheng.leetcode.LeetCode.Array.TwoSum
import java.io.File
import java.io.Serializable

@ChapterDslMarker
open class AlgoItem(
    open var chapter: Pair<String, String>,
    open var className: String? = null,
    open var subject: String? = null,
    open var hardLevel: Int = 1
) : Serializable {
    fun getFilePath(): String {
        return "LeetCode/" + chapter.first + File.separator + className + ".java"
    }

    fun hardLevel(): Int {
        return hardLevel
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

@DslMarker
annotation class DataSourceDslMarker

@DslMarker
annotation class ChapterDslMarker

@ChapterDslMarker
@DataSourceDslMarker
abstract class ChapterDataSource() {
    val list = ArrayList<AlgoItem>()

    protected fun <T : ChapterDataSource> addItem(chapter: T, init: AlgoItem.() -> Unit) {
        if (chapter.list.isEmpty()) {
            chapter.list.add(AlgoItem(getChapter()))
        }
        chapter.list.add(AlgoItem(getChapter()).apply(init))
    }

    protected abstract fun getChapter(): Pair<String, String>
}

open class ArrayChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Array" to "数组"

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

@DataSourceDslMarker
open class DataSource {
    open val lists = ArrayList<ArrayList<AlgoItem>>()

    fun arrayChapter(init: ArrayChapter.() -> Unit) {
        lists.add(ArrayChapter().apply(init).list)
    }

}

fun dataSource(init: DataSource.() -> Unit): ArrayList<ArrayList<AlgoItem>> {
    return DataSource().apply(init).lists
}

val leetCodeList = dataSource {
    arrayChapter {
        item {
            className = TwoSum::class.simpleName
            subject = "两数之和"
            hardLevel = 1
        }
    }
}
