package com.liucc.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 7};
        mergeSort(arr, 0, arr.length-1);
        System.out.println("排序后的数组：" + Arrays.toString( arr));
    }

    /**
     * 归并排序实现思路：
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right){
        // 打印分治过程，看看数组如何被拆分的
//        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
        // 2、治理：子序列左右指针相等，说明子序列只有一个元素，无需再分解
        if (left== right){
            return;
        }
        // 1、分解
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid); // 分解左半部分
        mergeSort(arr, mid + 1, right); // 分解右半部分
        // 3、合并
        merge(arr, left, right, mid);
    }

    /**
     * 合并
     * @param arr 原始数组
     * @param left
     * @param right
     * @param mid
     */
    private static void merge(int[] arr, int left, int right, int mid) {
        // 定义临时数组temp
        int[] temp = new int[right - left + 1];
        int i = left; // 左指针
        int j = mid + 1; // 右指针
        int k = 0;// 遍历temp数组
        while (i <= mid && j <= right){
            if (arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }else {
                temp[k++] = arr[j++];
            }
        }
        // 将第一个数组中剩余元素填充到temp中  实际上这两个循环只有一个会执行
        while (i <= mid){
            temp[k++] = arr[i++];
        }
        // 将第二个数组中剩余元素填充到temp中
        while (j <= mid){
            temp[k++] = arr[j++];
        }
        // 将temp数组拷贝回原数组
        System.arraycopy(temp, 0, arr, left, k);
    }
}
