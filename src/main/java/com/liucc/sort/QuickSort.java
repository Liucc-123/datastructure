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
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
//        long end = System.currentTimeMillis();
//        System.out.println("选择排序算法执行时间（ms）：" + (end-start)); // 8
    }

    public static void quickSort(int[] arr, int left, int right) {
        // 递归结束条件
        if (left >= right) {
            return;
        }
//        int pivotIndex = partition1(arr, left, right);
        int pivotIndex = partition2(arr, left, right);
        // 左分区再次递归
        quickSort(arr, left, pivotIndex - 1);
        // 右分区再次递归
        quickSort(arr, pivotIndex + 1, right);
    }

    /**
     * 双边扫描法 实现思路：
     * 1、数组最左侧元素定为基准点
     * 2、i从左向右遍历，寻找比基准点大的元素
     * 3、j从右向左，寻找比基准点小的元素
     * 4、j先开始，j找到小的，i找到大的，则交换位置，重复这个过程直至i与j相遇
     * 5、循环结束，交换基准点与i的位置，即i处的元素为新的基准点
     * 6、开始新一轮的快排，直至子序列长度为1（即left == right）
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition2(int[] arr, int left, int right) {
        int pivot = arr[left]; // 基准点
        int i = left; // 找大的
        int j = right; // 找小的
        while (i < j){
            // 1. 先找小的
            while (i < j && arr[j] > pivot){
                j--;
            }
            // 2. 再找大的
            while (i<j && arr[i] <= pivot){
                i++;
            }
            // 3. 交换位置
            swap(arr, i, j);
        }
        // 4. 循环结束，交换基准点与i的位置
        swap(arr, left, i);
        return i; // 返回新的基准点位置
    }

    /**
     * 单边扫描法
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition1(int[] arr, int left, int right) {
        int i = left-1; // 寻找比基准点大的值
        int pivot = arr[right]; // 数组最右边元素作为基准元素
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot){
                i++; // 指针前移
                swap(arr, i, j); // 交换
            }
        }
        swap(arr, i+1, right); // 更新基准元素位置
        // 循环结束，此时i+1就是新的基准元素位置
        return i+1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
