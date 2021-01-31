package org.zach.base.sort.impl;

/**
 * @author 钟鹏 ZachZhong
 * @version 1.0
 * @description 好记性不如烂笔头
 * @date 2021/1/31 16:48
 */
public class QuickSortord extends Sortord {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quick(arr, 0, arr.length - 1);
    }

    public static void quick(int[] arr, int L, int R) {
        if (L < R) {
            // 随机交互一个值到最后
            swap(arr, L + (int) Math.random() * (R - L + 1), R);
            int[] p = partition(arr, L, R);
            quick(arr, L, p[0]);
            quick(arr, p[1] + 1, R);
        }
    }

    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R]) {
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less, more};
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
