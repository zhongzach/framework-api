package org.zach.base.sort.impl;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/29 10:26 上午
 * @desc 选择排序，认为当前位置左边的所有值是排好序的，后面去选择查找最小的值进行交换
 */
public class SelectionSortord extends Sortord{
    @Override
    public void sort(int[] arr) {
        if (!checkRight(arr)) return;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }
}
