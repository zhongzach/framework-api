package org.zach.base.compare;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/2/3 8:05 下午
 */
public class ComparatorDemo implements Comparator<Integer> {

    /**
     * o1 与 o2 比较，返回负值，返回o1
     * 返回正值，返回o2
     * 相等，无所谓
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new ComparatorDemo());
        heap.add(3);
        heap.add(7);
        heap.add(2);
        heap.add(6);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
