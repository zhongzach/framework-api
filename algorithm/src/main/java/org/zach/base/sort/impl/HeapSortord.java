package org.zach.base.sort.impl;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/2/2 6:26 下午
 */
public class HeapSortord extends Sortord {

    @Override
    public void sort(int[] arr) {
        if (arr == null | arr.length < 2) {
            return;
        }

        // 1、将数组勾画成大根堆
        /*for (int i = arr.length - 1; i >=0 ; i--) {
            heapify(arr, i, arr.length);
        }*/

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        swap(arr, 0 , --heapSize);

        while (heapSize > 0 ) {
            heapify(arr, 0 , heapSize);
            swap(arr, 0 , --heapSize);
        }
    }

    /**
     * 往大根堆里插入一个节点
     *
     * @param arr
     * @param index
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            largest = arr[index] < arr[largest] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    public static void swap(int[] arr, int a, int b) {
        /*arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];*/
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}
