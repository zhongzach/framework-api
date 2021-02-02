package org.zach.base.sort.impl;

import org.zach.base.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author 钟鹏 ZachZhong
 * @version 1.0
 * @description 好记性不如烂笔头
 * @date 2021/2/2 22:15
 * 堆排序，时间复杂度O(NlogN), 空间复杂度（O(1)）
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createRandomArray(10, 1000);
        int[] arrClone = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrClone);
        heapSort(arr);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arrClone));
        System.out.println(Arrays.equals(arr,arrClone));

    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;

//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr,i);
//        }

        for (int i = arr.length - 1 ; i >= 0 ; i--) {
            heapify(arr, i , arr.length);
        }

        // 跟最后一个数字交换并且从堆中去掉
        swap(arr, 0 , --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0 ,heapSize);
            swap(arr, 0 , --heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr , int index , int heapSize) {
        // 0 不能做位运算
        int left = index * 2 + 1; // 获得左孩子
        while (left < heapSize) {
            int lastest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            lastest = arr[index] < arr[lastest] ? lastest : index;
            if (lastest == index) {
                break;
            }
            swap(arr, index , lastest);
            index = lastest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}
