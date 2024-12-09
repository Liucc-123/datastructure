package com.liucc.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        insertSort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));
        // 事后统计，选择排序算法的执行时间
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("插入排序算法执行时间（ms）：" + (end-start)); // 832
    }

    /**
     * 插入排序实现思路（从小到大）：
     * 从数组的第二个元素开始遍历（因为第一个元素插入时一定是有序的）
     * 每一轮比较又是一个循环，依次比较当前遍历元素与前置节点的大小。
     * 如果小，则前置节点后移一个位置。直至遍历到第一个元素。
     *
     * @param arr {101, 34, 119, 1} -> {34, 101, 119, 1}
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int inertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 &&inertVal < arr[insertIndex]){// 插入元素小
                arr[insertIndex+1] = arr[insertIndex]; // 位置后移
                insertIndex--;
            }
            // 插入新元素
            arr[insertIndex+1] = inertVal;
        }
    }
}
