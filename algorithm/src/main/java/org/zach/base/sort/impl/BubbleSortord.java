package org.zach.base.sort.impl;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/29 9:05 上午
 * @desc 冒泡排序 每一次遍历都两两比对找最大值
 */
public class BubbleSortord extends Sortord {

    @Override
    public void sort(int[] arr) {
        if (!checkRight(arr)) return;
        boolean flag; // 定义交换标识
        for (int i = 0; i < arr.length; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    flag = true;
                }
            }
            // 如果从头遍历到尾没有进行过一次交换，就代表当前数组已经是有序的
            if (!flag) return;
        }
    }
}
