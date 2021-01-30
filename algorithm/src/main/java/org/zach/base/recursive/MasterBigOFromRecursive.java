package org.zach.base.recursive;

import org.zach.base.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author 钟鹏 ZachZhong
 * @version 1.0
 * @description 好记性不如烂笔头
 * @date 2021/1/30 11:21
 * @desc recursive的bigO计算，如果符合master公式，可以通过master公式计算时间复杂度
 *
 * T(N) = a * T(N/b) + O(N^d)
 *
 * logba < d , 时间复杂度为 O(N^d)
 * logba > d , 时间复杂度为 O(N^logba)
 * logba = d , 时间复杂度为 O(N^d * logN)
 *
 */
public class MasterBigOFromRecursive {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createRandomArray(10, 100);
        System.out.println(Arrays.toString(arr));
        System.out.println(process(arr, 0, arr.length - 1));
    }

    /**
     * 套用master公示   a = 2 , b = 1/2 , d = 0  ,满足第二条，所以时间复杂度为O(N)
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        /*
        * (L+R)/2 -> L + (R-L)/2 -> L+ ((R-L) >> 1)
        *
        * */
        int mid = L + ((R - L) >> 1);
        int maxLeft = process(arr, L, mid);
        int maxRight = process(arr, mid + 1, R);
        return Math.max(maxLeft, maxRight);
    }

}
