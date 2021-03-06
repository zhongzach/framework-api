package org.zach.base.sort.impl;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/29 9:05 上午
 * @desc 冒泡排序 每一次遍历都两两比对找最大值xx
 */
public class BubbleSortord extends Sortord {

    @Override
    public void sort(int[] arr) {
        if (!checkRight(arr)) return;
        for (int i = arr.length - 1; i > 0  ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1] ) {
                    swap(arr, j , j+ 1);
                }
            }
        }
    }

    static void swap(int[] arr,int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
