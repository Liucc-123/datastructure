package com.liucc.algorithm;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestInteger {
    public static void main(String[] args) {
       int[] arr =   {1, 2, 4, 5};
       int target = 10; // 目标元素
       int insertionPoint = Arrays.binarySearch(arr, target);
       log.info("insertionPoint: {}", insertionPoint);
       if (insertionPoint < 0) { // 说明没找到
         // 获取应该插入的位置
         int insertIndex = -(insertionPoint + 1);
         // 创建新的数组，大小比原数组大1
         // 将原数组插入点之前的元素直接拷贝到新数组
         // 将目标元素放入插入点位置
         // 将原数组剩余元素拷贝到新数组剩余位置
         int[] newArr = new int[arr.length + 1];
         System.arraycopy(arr, 0, newArr, 0, insertIndex);
         newArr[insertIndex] = target;
         System.arraycopy(arr, insertIndex, newArr, insertIndex + 1, arr.length - insertIndex);
         log.info("newArr: {}", Arrays.toString(newArr));
       }
    }

}
