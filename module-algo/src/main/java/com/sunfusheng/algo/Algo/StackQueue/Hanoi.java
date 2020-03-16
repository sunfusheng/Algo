package com.sunfusheng.algo.Algo.StackQueue;

/**
 * 【题目】
 * 用栈来求解汉诺塔问题
 * <p>
 * 经典的汉诺塔问题是：有三根相邻的柱子，标号为left、mid、right，left柱子上从下到上按金字塔状叠放着n个不同大小的圆盘，
 * 要把所有盘子一个一个移动到柱子right上，并且每次移动同一根柱子上都不能出现大盘子在小盘子上方，请问至少需要多少次移动？
 * <p>
 * 这里修改一下游戏规则：现在限制不能从最左侧的塔直接移动到最右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。
 * 求当塔有N层的时候，打印最优移动过程和最优移动总步数。
 * <p>
 * 例如，当塔数为两层时，最上层的塔记为1，最下层的塔记为2，则打印：
 * 1、Move 1 from left to mid
 * 2、Move 1 from mid to right
 * 3、Move 2 from left to mid
 * 4、Move 1 from right to mid
 * 5、Move 1 from mid to left
 * 6、Move 2 from mid to right
 * 7、Move 1 from left to mid
 * 8、Move 1 from mid to right
 * <p>
 * It will move 8 steps
 * <p>
 * 【要求】
 * 用下面两种方式分别实现：
 * 一：递归的方式
 * 二、非递归的方式，用栈模拟汉诺塔的三个塔
 *
 * @author sunfusheng
 * @since 2020/3/16
 */
public class Hanoi {

    public static int hanoiRecur(int n, String left, String mid, String right) {
        if (n < 1) {
            return 0;
        }

        return move1(n, left, mid, right, left, right);
    }

    private static int move1(int n, String left, String mid, String right, String from, String to) {
        if (n == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }

        if (from.equals(mid) || to.equals(mid)) {
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = move1(n - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + n + " from " + from + " to " + to);
            int part3 = move1(n - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = move1(n - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + n + " from " + from + " to " + mid);
            int part3 = move1(n - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + n + " from " + mid + " to " + to);
            int part5 = move1(n - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    public static void main(String[] args) {
        // 递归的方式实现汉诺塔问题
        int steps = hanoiRecur(3, "left", "mid", "right");
        System.out.println("It will move " + steps + " steps");
    }
}
