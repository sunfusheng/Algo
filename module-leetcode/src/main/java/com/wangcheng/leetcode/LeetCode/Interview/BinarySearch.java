package com.wangcheng.leetcode.LeetCode.Interview;

import com.sunfusheng.algo.common.util.AlgoUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 【题目】
 * 面试题 #.04. 泛型化的二分搜索
 *
 * @author sunfusheng
 * @since 2020/5/24
 */
public class BinarySearch {

    /**
     * 二分搜索
     *
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (value > arr[mid]) {
                left = ++mid;
            } else if (value < arr[mid]) {
                right = --mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 泛型化的二分搜索
     *
     * @param arr
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int binarySearch2(T[] arr, T value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (value.compareTo(arr[mid]) > 0) {
                left = ++mid;
            } else if (value.compareTo(arr[mid]) < 0) {
                left = --mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10};
        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        System.out.println("搜索6，输出索引：" + binarySearch(arr, 6));

        Person p1 = new Person("p1", 20);
        Person p2 = new Person("p2", 21);
        Person p3 = new Person("p3", 22);
        Person p4 = new Person("p4", 23);
        Person[] pArr = {p1, p2, p3, p4};
        System.out.println("搜索p4，输出索引：" + binarySearch2(pArr, p4));
    }

    static class Person implements Comparable<Person> {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @NotNull
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Person o) {
            if (age > o.age) {
                return 1;
            } else if (age < o.age) {
                return -1;
            }
            return 0;
        }
    }
}
