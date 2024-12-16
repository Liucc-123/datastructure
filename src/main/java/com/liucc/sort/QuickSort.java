package com.liucc.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {5, 3, 8, 6, 7};
//        quickSort(arr, 0, arr.length-1);
//        System.out.println("排序后：" + Arrays.toString(arr));

        // 事后统计，选择排序算法的执行时间
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("选择排序算法执行时间（ms）：" + (end-start)); // 8
    }

    public static void quickSort(int[] arr, int left, int right) {
        // 递归结束条件
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(arr, left, right);
        // 左分区再次递归
        quickSort(arr, left, pivotIndex - 1);
        // 右分区再次递归
        quickSort(arr, pivotIndex + 1, right);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex]; // 选取第一个元素作为基准元素
        int left = startIndex;
        int right = endIndex;
        while (left < right) {
            // 右指针左移
            while (right > left && arr[right] > pivot) {
                right--; //左移
            }
            // 左指针右移
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // 如果左右指针未贴合，两个元素交换位置
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        // pivot和重合指针交换位置
        arr[startIndex] = arr[left];
        arr[left] = pivot;
        return left;  // 新的pivot位置
    }
}
