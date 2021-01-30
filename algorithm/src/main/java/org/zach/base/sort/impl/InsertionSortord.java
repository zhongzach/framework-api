package org.zach.base.sort.impl;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/29 9:27 上午
 * @desc 插入排序思想：当前位置为i ，默认i-1前是已经排好序的，只需要将当前i位置的值遍历前面排好序的数组，选择性插入即可
 * 时间复杂度 O(n^2) 最优时间复杂度(On)
 */
public class InsertionSortord extends Sortord{
    @Override
    public void sort(int[] arr) {
        if (!checkRight(arr)) return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                arr[j] = arr[j] ^ arr[j + 1];
                arr[j + 1] = arr[j] ^ arr[j + 1];
                arr[j] = arr[j] ^ arr[j + 1];
            }
        }
    }
}
