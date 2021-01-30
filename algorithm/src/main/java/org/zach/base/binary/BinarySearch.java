package org.zach.base.binary;

/**
 * @author 钟鹏 ZachZhong
 * @version 1.0
 * @description 好记性不如烂笔头
 * @date 2021/1/30 11:58
 */
public class BinarySearch {


    public static void main(String[] args) {

        int arr[] = {1,2,3,4,5,6,7};
        int num = 3;
        System.out.println(isExist(arr, 3));

    }

    public static boolean isExist (int[] arr, int num) {
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R ) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return arr[L] == num;
    }

}
