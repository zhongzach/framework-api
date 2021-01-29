package org.zach.base.sort.utils;

import java.util.Random;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/29 9:21 上午
 */
public class ArrayUtils {

    public static int[] createRandomArray(int len, int random) {
        int[] arr = new int[len];
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(random);
        }
        return arr;
    }

}
