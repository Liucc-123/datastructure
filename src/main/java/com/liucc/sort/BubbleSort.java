package com.liucc.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序算法基本规则：
 * 1、一共进行n-1次大的排序  （n是数组的大小）
 * 2、每一次排序确定当前剩余元素中最大的元素
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//        bubbleSort(arr);

        // 事后统计80000长度数组冒泡排序时间
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("冒泡执行时间（ms）：" + (end-start));
    }

    public static void bubbleSort(int[] arr) {
        /**
         * 优化：如果在某一趟排序中，发现数组已经是有序的了，则直接退出排序
         */
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = 0;
            boolean isSort = false; // 是否交换过
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) { // 逆序则交换
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSort = true;
                }
            }
            if (!isSort) {
                return;
            }
        }
    }
}
