package com.wangcheng.leetcode.LeetCode.Number

import com.wangcheng.leetcode.DataSource

/**
 * @author liwangcheng
 * @date 2020/5/1.
 */
class NumberChapterData {

    companion object {
        fun simpleNumber(dataSource: DataSource) {
            dataSource.numberChapter {
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
                item {
                    className = ConvertToTitle::class.simpleName
                    subject = "168.Excel表列名"
                    hardLevel = 1
                }
                item {
                    className = TitleToNumber::class.simpleName
                    subject = "171.Excel表列序号"
                    hardLevel = 1
                }
                item {
                    className = TrailingZeroes::class.simpleName
                    subject = "172.阶乘后的零"
                    hardLevel = 1
                }
                item {
                    className = ReverseBits::class.simpleName
                    subject = "190.颠倒二进制位"
                    hardLevel = 1
                }
                item {
                    className = HammingWeight::class.simpleName
                    subject = "191.位1的个数"
                    hardLevel = 1
                }
                item {
                    className = IsHappy::class.simpleName
                    subject = "202.快乐数"
                    hardLevel = 2
                }
                item {
                    className = CountPrimes::class.simpleName
                    subject = "204.计数质数"
                    hardLevel = 2
                }
                item {
                    className = IsPowerOfTwo::class.simpleName
                    subject = "231.2的幂"
                    hardLevel = 1
                }
                item {
                    className = AddDigits::class.simpleName
                    subject = "258.各位相加"
                    hardLevel = 1
                }
                item {
                    className = IsUgly::class.simpleName
                    subject = "263.丑数"
                    hardLevel = 1
                }
                item {
                    className = MissingNumber::class.simpleName
                    subject = "268.缺失数字"
                    hardLevel = 1
                }
            }
        }

    }
}
