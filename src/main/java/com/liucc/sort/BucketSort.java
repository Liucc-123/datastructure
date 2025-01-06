package com.liucc.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序
 */
public class BucketSort {
    public static void main(String[] args) {
        double[] arr = new double[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.round(Math.random() * 10000) / 100.0;
        }
        System.out.println("原始数组：" + Arrays.toString(arr));
        double[] sortedArr = bucketSort(arr);
        System.out.println("桶排序后：" + Arrays.toString(sortedArr));
    }

    /**
     * 桶排序
     * 
     * @param arr 原始数组
     * @return
     */
    private static double[] bucketSort(double[] arr) {
        int arrLen = arr.length;
        // 1、求原始数组最大值、最小值，并求出差值
        double max = arr[0];
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        double diff = max - min;
        // 2、初始化桶
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            bucketList.add(new LinkedList<Double>());
        }
        // 3、遍历原始数组，填充桶
        for (int i = 0; i < arr.length; i++) {
            // 放入桶的位置 min就是偏移量
            int index = (int) ((arr[i] - min) * (arrLen - 1) / diff);
            bucketList.get(index).add(arr[i]);
        }
        // 4、每个桶内的元素进行内部排序
        for (LinkedList<Double> list : bucketList) {

            Collections.sort(list);
        }

        // 5、遍历桶数组，填充返回数组，并返回
        double[] returnArr = new double[arr.length];
        int index = 0;
        for (LinkedList<Double> list : bucketList) {
            for (Double item : list) {
                returnArr[index++] = item;
            }
        }
        return returnArr;
    }
}
