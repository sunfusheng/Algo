package com.sunfusheng.algo.Algo;

/**
 * @author sunfusheng
 * @since 2020-02-22
 */
public class Utils {

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("{}");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        System.out.println(sb);
    }
}
