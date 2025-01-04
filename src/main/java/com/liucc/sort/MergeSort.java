package com.liucc.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 7};
        // 优化：将临时数组的定义放到外面，避免每次递归调用都创建销毁临时数组，节省内存开销
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
        System.out.println("排序后的数组：" + Arrays.toString( arr));
    }

    /**
     * 归并排序实现思路：
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        // 打印分治过程，看看数组如何被拆分的
//        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
        // 2、治理：子序列左右指针相等，说明子序列只有一个元素，无需再分解
        if (left== right){
            return;
        }
        // 1、分解
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, temp); // 分解左半部分
        mergeSort(arr, mid + 1, right, temp); // 分解右半部分
        // 3、合并
        merge(arr, left, right, mid, temp);
    }

    /**
     * 合并
     * @param arr 原始数组
     * @param left 数组左边界
     * @param right 数组右边界
     * @param mid 中间切分位置
     */
    private static void merge(int[] arr, int left, int right, int mid, int[] temp) {
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
