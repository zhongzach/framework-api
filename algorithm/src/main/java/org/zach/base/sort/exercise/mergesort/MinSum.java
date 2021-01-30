package org.zach.base.sort.exercise.mergesort;

import java.util.Arrays;

/**
 * @author 钟鹏 ZachZhong
 * @version 1.0
 * @description 好记性不如烂笔头
 * @date 2021/1/30 14:45
 * @desc 求数组的最小和
 * <p>
 * 最小和： 当前位置前的数比当前数小的值求和
 * 思想转变：后面有多少个数比当前数大
 */
public class MinSum {

    public static void main(String[] args) {

        int arr[] = {1,3,2,4,5};

        System.out.println(minSum(arr));
        System.out.println(Arrays.toString(arr));

    }

    public static int minSum(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int arr[], int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid)
                + process(arr, mid + 1, R)
                + merge(arr, L, mid, R);

    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0; // 求最小和
        while (p1 <= M && p2 <= R) {
            res += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return res;
    }

}
