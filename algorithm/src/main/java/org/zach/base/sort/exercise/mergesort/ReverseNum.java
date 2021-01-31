package org.zach.base.sort.exercise.mergesort;

import org.zach.base.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author 钟鹏 ZachZhong
 * @version 1.0
 * @description 好记性不如烂笔头
 * @date 2021/1/31 12:05
 * @desc 求一个数组中的逆序对的总数
 * <p>
 * 例子：
 * 3，2，1，4，5
 * <p>
 * 3，2、3，1
 * 2，1
 *
 * <p>
 * 一共4对逆序对
 * <p>
 * 这种问题都是可以使用归并排序进行解答
 */
public class ReverseNum {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtils.createRandomArray(10, 10000);
            int[] arrClone = Arrays.copyOf(arr, arr.length);

//            System.out.println(Arrays.toString(arr));
//            System.out.println(Arrays.toString(arrClone));

            int i1 = reverseNum(arr);
            int i2 = compareReverse(arrClone);
            if (i1 != i2) {
                System.out.println("程序有BUG");
                System.out.println(i1);
                System.out.println(i2);
                break;
            }
        }
        System.out.println("程序牛逼");
    }

    public static int reverseNum(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid)
                + process(arr, mid + 1, R)
                + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;

        while (p1 <= M && p2 <= R) {
            res += arr[p1] > arr[p2] ? M -  p1+ 1 : 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
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


    public static int compareReverse(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                res += arr[i] > arr[j] ? 1 : 0;
            }
        }
        return res;
    }

}


