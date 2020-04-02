package com.wangcheng.leetcode

import com.sunfusheng.algo.common.AlgoItem
import com.sunfusheng.algo.common.ChapterDslMarker
import com.sunfusheng.algo.common.DataSourceDslMarker
import com.sunfusheng.algo.common.LEETCODE_ROOT_PATH
import com.wangcheng.leetcode.LeetCode.Array.*
import com.wangcheng.leetcode.LeetCode.BinaryTree.*
import com.wangcheng.leetcode.LeetCode.LinkedList.AddTwoNumbers
import com.wangcheng.leetcode.LeetCode.LinkedList.DeleteDuplicates
import com.wangcheng.leetcode.LeetCode.LinkedList.MergeTwoLists
import com.wangcheng.leetcode.LeetCode.Number.*
import com.wangcheng.leetcode.LeetCode.String.*

@ChapterDslMarker
@DataSourceDslMarker
abstract class ChapterDataSource() {
    val list = ArrayList<AlgoItem>()

    private fun <T : ChapterDataSource> addItem(chapter: T, init: AlgoItem.() -> Unit) {
        if (chapter.list.isEmpty()) {
            chapter.list.add(AlgoItem(chapter = getChapter()))
        }
        chapter.list.add(
            AlgoItem(
                rootPath = LEETCODE_ROOT_PATH,
                chapter = getChapter()
            ).apply(init)
        )
    }

    protected abstract fun getChapter(): Pair<String, String>

    fun item(init: AlgoItem.() -> Unit) = addItem(this, init)
}

open class ArrayChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Array" to "数组"
}

open class LinkedListChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "LinkedList" to "链表"
}

open class StringChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "String" to "字符串"
}

open class NumberChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "Number" to "数字"
}

open class BinaryTreeChapter : ChapterDataSource() {
    override fun getChapter(): Pair<String, String> = "BinaryTree" to "二叉树"
}

@DataSourceDslMarker
open class DataSource {
    open val lists = ArrayList<ArrayList<AlgoItem>>()

    fun arrayChapter(init: ArrayChapter.() -> Unit) {
        lists.add(ArrayChapter().apply(init).list)
    }

    fun linkedListChapter(init: LinkedListChapter.() -> Unit) {
        lists.add(LinkedListChapter().apply(init).list)
    }

    fun stringChapter(init: StringChapter.() -> Unit) {
        lists.add(StringChapter().apply(init).list)
    }

    fun numberChapter(init: NumberChapter.() -> Unit) {
        lists.add(NumberChapter().apply(init).list)
    }

    fun binaryTreeChapter(init: BinaryTreeChapter.() -> Unit) {
        lists.add(BinaryTreeChapter().apply(init).list)
    }
}

fun dataSource(init: DataSource.() -> Unit): ArrayList<ArrayList<AlgoItem>> {
    return DataSource().apply(init).lists
}

val leetCodeDataSource = dataSource {
    arrayChapter {
        item {
            className = TwoSum::class.simpleName
            subject = "1.两数之和"
            hardLevel = 1
        }
        item {
            className = FindMedianSortedArrays::class.simpleName
            subject = "4.寻找两个有序数组的中位数"
            hardLevel = 3
        }
        item {
            className = RemoveDuplicates::class.simpleName
            subject = "26.删除排序数组中的重复项"
            hardLevel = 1
        }
        item {
            className = RemoveElement::class.simpleName
            subject = "27.移除元素"
            hardLevel = 1
        }
        item {
            className = SearchInsert::class.simpleName
            subject = "35.搜索插入位置"
            hardLevel = 1
        }
        item {
            className = MaxSubArray::class.simpleName
            subject = "53.最大子序和"
            hardLevel = 2
        }
        item {
            className = PlusOne::class.simpleName
            subject = "66.加一"
            hardLevel = 1
        }
        item {
            className = MergeSortedArray::class.simpleName
            subject = "88.合并两个有序数组"
            hardLevel = 1
        }
        item {
            className = YangHuiTriangle::class.simpleName
            subject = "118.杨辉三角"
            hardLevel = 1
        }
        item {
            className = YangHuiTriangleII::class.simpleName
            subject = "119.杨辉三角 II"
            hardLevel = 1
        }
    }

    linkedListChapter {
        item {
            className = AddTwoNumbers::class.simpleName
            subject = "2.两数相加"
            hardLevel = 2
        }
        item {
            className = MergeTwoLists::class.simpleName
            subject = "21.合并两个有序链表"
            hardLevel = 1
        }
        item {
            className = DeleteDuplicates::class.simpleName
            subject = "83.删除排序链表中的重复元素"
            hardLevel = 1
        }
    }

    stringChapter {
        item {
            className = LengthOfLongestSubstring::class.simpleName
            subject = "3.无重复字符的最长子串"
            hardLevel = 2
        }
        item {
            className = LongestPalindrome::class.simpleName
            subject = "5.最长回文子串"
            hardLevel = 2
        }
        item {
            className = ZShapedConvert::class.simpleName
            subject = "6.Z字形变换"
            hardLevel = 2
        }
        item {
            className = LongestCommonPrefix::class.simpleName
            subject = "14.最长公共前缀"
            hardLevel = 1
        }
        item {
            className = ValidBrackets::class.simpleName
            subject = "20.有效的括号"
            hardLevel = 1
        }
        item {
            className = ImplStrStr::class.simpleName
            subject = "28.实现strStr()"
            hardLevel = 1
        }
        item {
            className = CountAndSay::class.simpleName
            subject = "38.外观数列"
            hardLevel = 2
        }
        item {
            className = LengthOfLastWord::class.simpleName
            subject = "58.最后一个单词的长度"
            hardLevel = 1
        }
    }

    numberChapter {
        item {
            className = ReverseInt::class.simpleName
            subject = "7.反转整数"
            hardLevel = 1
        }
        item {
            className = PalindromeNum::class.simpleName
            subject = "9.回文数判断"
            hardLevel = 1
        }
        item {
            className = RomeToInt::class.simpleName
            subject = "13.罗马数字转整数"
            hardLevel = 1
        }
        item {
            className = AddBinary::class.simpleName
            subject = "67.二进制求和"
            hardLevel = 2
        }
        item {
            className = MySqrt::class.simpleName
            subject = "69.x的平方根"
            hardLevel = 2
        }
        item {
            className = ClimbStairs::class.simpleName
            subject = "70.爬楼梯"
            hardLevel = 2
        }
    }

    binaryTreeChapter {
        item {
            className = InorderTraversal::class.simpleName
            subject = "94.二叉树的中序遍历"
            hardLevel = 2
        }
        item {
            className = BSTNum::class.simpleName
            subject = "96.不同的二叉搜索树"
            hardLevel = 2
        }
        item {
            className = SameTree::class.simpleName
            subject = "100.相同的树"
            hardLevel = 1
        }
        item {
            className = IsSymmetric::class.simpleName
            subject = "101.对称二叉树"
            hardLevel = 1
        }
        item {
            className = MaxDepth::class.simpleName
            subject = "104.二叉树的最大深度"
            hardLevel = 1
        }
        item {
            className = LevelOrderBottom::class.simpleName
            subject = "107.二叉树的层次遍历 II"
            hardLevel = 1
        }
        item {
            className = SortedArrayToBST::class.simpleName
            subject = "108.将有序数组转换为二叉搜索树"
            hardLevel = 1
        }
        item {
            className = IsBalanced::class.simpleName
            subject = "110.平衡二叉树"
            hardLevel = 1
        }
        item {
            className = MinDepth::class.simpleName
            subject = "111.二叉树的最小深度"
            hardLevel = 1
        }
        item {
            className = HasPathSum::class.simpleName
            subject = "112.路径总和"
            hardLevel = 2
        }
        item {
            className = DeleteBinarySearchTreeNode::class.simpleName
            subject = "450.删除二叉搜索树中的节点"
            hardLevel = 2
        }
    }
}
