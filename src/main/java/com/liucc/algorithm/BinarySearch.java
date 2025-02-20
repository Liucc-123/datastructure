package com.liucc.algorithm;

/**
 * 二分查找实现思路
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9 };
        System.out.println(binarySearch(arr, 4));
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) { // left和right指针重合时，需要再比较一次\
            // 中间值
            // int midVal = arr[(left + right) / 2];
            int midVal = arr[(left + right) >>> 1];
            // 找到目标值
            if (target == midVal) {
                return (left + right) >>> 1;
            }
            // 比目标值大
            if (midVal > target) {
                right = ((left + right) >>> 1) - 1;
            }
            // 比目标值小
            if (midVal < target) {
                left = ((left + right) >>> 1) + 1;
            }
        }
        return -1; // 未找到目标值
    }
}
