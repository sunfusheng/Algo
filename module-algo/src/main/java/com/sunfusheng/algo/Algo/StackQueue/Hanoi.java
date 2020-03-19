package com.sunfusheng.algo.Algo.StackQueue;

import java.util.Stack;

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

    // 递归的方式实现汉诺塔问题
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

    // 定义汉诺塔移动的动作
    private enum Action {
        No, LToM, MToL, MToR, RToM
    }

    // 非递归的方式实现汉诺塔问题
    public static int hanoiUnRecur(int n, String left, String mid, String right) {
        if (n < 1) {
            return 0;
        }

        // 用三个栈模拟汉诺塔的三根柱子
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();

        // 栈底先放上最大值
        ls.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);

        for (int i = n; i > 0; i--) {
            ls.push(i);
        }

        // 记录前一个移动动作
        Action[] record = {Action.No};
        int steps = 0;
        while (rs.size() != n + 1) {
            steps += fromStack2ToStack(record, Action.LToM, Action.MToL, ms, ls, mid, left);
            steps += fromStack2ToStack(record, Action.MToL, Action.LToM, ls, ms, left, mid);
            steps += fromStack2ToStack(record, Action.MToR, Action.RToM, rs, ms, right, mid);
            steps += fromStack2ToStack(record, Action.RToM, Action.MToR, ms, rs, mid, right);
        }
        return steps;
    }

    private static int fromStack2ToStack(Action[] record, Action preAction, Action currAction,
                                         Stack<Integer> fromStack, Stack<Integer> toStack,
                                         String from, String to) {
        if (record[0] != preAction && fromStack.peek() < toStack.peek()) {
            toStack.push(fromStack.pop());
            System.out.println("Move " + toStack + " from " + from + " to " + to);
            record[0] = currAction;
        }
        return 0;
    }

    public static void main(String[] args) {
        // 递归的方式实现汉诺塔问题
        int steps = hanoiRecur(3, "left", "mid", "right");
        System.out.println("It will move " + steps + " steps");

        System.out.println(" ");

        // 非递归的方式实现汉诺塔问题
        steps = hanoiRecur(2, "left", "mid", "right");
        System.out.println("It will move " + steps + " steps");
    }
}
