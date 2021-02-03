package org.zach.base.sort;

import org.zach.base.sort.impl.*;
import org.zach.base.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/29 9:15 上午
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createRandomArray(10, 1000);
        int[] arrClone = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrClone);

//        Sortord sortord = new BubbleSortord();
//        Sortord sortord = new InsertionSortord();
//        Sortord sortord = new SelectionSortord();
//        MergeSortord sortord = new MergeSortord();
//        QuickSortord sortord = new QuickSortord();
//        HeapSortord sortord = new HeapSortord();
        Sortord sortord = new RadixSortord();
        sortord.sort(arr);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arrClone));
        System.out.println(Arrays.equals(arr,arrClone));
    }



}
