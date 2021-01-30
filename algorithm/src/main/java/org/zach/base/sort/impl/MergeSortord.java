package org.zach.base.sort.impl;

/**
 * @author 钟鹏 ZachZhong
 * @version 1.0
 * @description 好记性不如烂笔头
 * @date 2021/1/30 14:33
 */
public class MergeSortord extends Sortord {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0; // 辅助数组的指针
        int p1 = L; // 左边开始指针
        int p2 = M + 1; // 右边开始指针
        
        // 两边指针都没有越界
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }
    }
}
