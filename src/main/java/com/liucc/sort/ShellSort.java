package com.liucc.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        // 9	8	7	6	5	4	3	2	1
//        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        shellSort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));
        // 事后统计，选择排序算法的执行时间
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        shellSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("希尔排序算法执行时间（ms）：" + (end-start)); // 11
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        int count= 0;
        // 希尔排序 增量gap = n/2 = 4
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                // 每一个子序列走插入排序
                int temp = arr[i]; // 待插入元素
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap; // j前移
                }
                // 退出循环，说明找到要插入的位置了，也就是j这个索引位置
                arr[j] = temp;
            }
        }
    }
}
