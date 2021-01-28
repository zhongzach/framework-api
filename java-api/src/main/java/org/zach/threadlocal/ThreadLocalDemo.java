package org.zach.threadlocal;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/27 5:51 下午
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {

        ThreadLocal<String> threadlocal = new ThreadLocal<>();
        threadlocal.set("zach");


    }

}
