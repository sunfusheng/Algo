package com.wangcheng.leetcode

import com.wangcheng.leetcode.LeetCode.String.*


/**
 * @author liwangcheng
 * @date 2020/5/1.
 */
fun stringDataSource(dataSource: DataSource) {
    dataSource.stringChapter {
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
        item {
            className = IsPalindrome::class.simpleName
            subject = "125.验证回文串"
            hardLevel = 1
        }
        item {
            className = IsIsomorphic::class.simpleName
            subject = "205.同构字符串"
            hardLevel = 1
        }
        item {
            className = IsAnagram::class.simpleName
            subject = "242.有效的字母异位词"
            hardLevel = 1
        }
        item {
            className = WordPattern::class.simpleName
            subject = "290.单词规律"
            hardLevel = 1
        }
        item {
            className = ReverseString::class.simpleName
            subject = "344.反转字符串"
            hardLevel = 1
        }
        item {
            className = ReverseVowels::class.simpleName
            subject = "345.反转字符串中的元音字母"
            hardLevel = 1
        }
    }
}