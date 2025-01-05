package com.liucc.sort;

import java.util.Arrays;

public class CountSort {
    public static void main(String[] args) {
        int[] arr = { 0, 2, 2, 1, 3, -5 };
        // countSort(arr);
        countSort2(arr);
        System.out.println("计数排序后：" + Arrays.toString(arr));
    }

    /**
     * 计数排序（支持负数）
     * 核心思路：
     * 原始数组：[2, 5, -1, 3, 2]
     * 计数数组：[1, 0, 0, 2, 1, 0, 1]
     * 1、建立映射关系。计数数组的索引0处表示原数组最小元素，比如这里0表示-1，那么其他位置就依次进行平移
     * 2、计数数组的长度为(max-min+1)
     * 
     * @param arr
     */
    private static void countSort2(int arr[]) {
        // 1、找到max和min
        int max = 0;
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 2、创建计数数组，并统计每个元素出现的次数
        int[] countArr = new int[max - min + 1];
        for (int item : arr) { // item-min 才是当前元素应该在计数数组中的索引位置
            countArr[item - min]++;
        }
        // 3、重排序
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i]-- > 0) {
                arr[index++] = i + min; // 当前下标+min表示原始数组的真实元素
            }
        }
    }

    /**
     * 计数排序(仅支持>=0的整数)
     * 
     * @param arr
     */
    private static void countSort(int[] arr) {
        // 遍历获取数组中最大的元素
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        // 创建基数数组
        int[] countArr = new int[max + 1];
        // 统计每个元素出现的次数
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
        }
        // System.out.println(Arrays.toString(countArr));
        // 重排序
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }

}