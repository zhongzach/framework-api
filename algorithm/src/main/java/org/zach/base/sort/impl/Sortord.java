package org.zach.base.sort.impl;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/29 9:01 上午
 */
public abstract class Sortord {

    public abstract void sort(int[] arr);

    public boolean checkRight(int[] arr) {
        return !(arr == null || arr.length <= 1);
    }

}
