package com.liucc.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        // 101, 34, 119, 1, -1, 90, 123
        /**
         * 选择排序基本思想：
         * 每一轮排序中找到最小的元素，与第一个元素位置进行交换
         * 第二轮排序，从第二个元素开始找到第二小的元素，与第二个元素进行位置交换
         * 依此类推，直至数组元素有序
         *
         * 可以发现，每一轮排序中又是一个for;
         * 排序的次数为数组长度-1
         */
//        int[] arr = {101, 34, 119, 1, -1, 90, 123};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));
//        selectSort(arr);
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));

        // 事后统计，选择排序算法的执行时间
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("选择排序算法执行时间（ms）：" + (end-start)); // 1345

    }

    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            // 假定当前遍历元素就是数组中的最小元素
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]){ // 说明当前假定最小元素并不是最小，重置
                    min = arr[j]; // 重置min
                    minIndex = j;
                }
            }
            if (minIndex != i){ // 当前元素i不是剩余元素值中最小的，才进行交换
                // 与最小元素进行交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
