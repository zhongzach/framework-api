package org.zach.simple;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/22 9:22 下午
 */
public class ZkClient {

    private static final String ZK_ADDRESS = "49.235.226.61";

    private static final int SESSION_TIME = 5000;

    private static final AtomicInteger version = new AtomicInteger(0);

    private static ZooKeeper zooKeeper;

    public static void initClient() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper(ZK_ADDRESS, SESSION_TIME, event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected
                        && event.getType() == Watcher.Event.EventType.None) {
                    countDownLatch.countDown();
                    System.out.println("连接成功...");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("连接中...");
        countDownLatch.await();
    }

    public static void main(String[] args) throws InterruptedException, KeeperException {
        // 1、初始化客户端
        initClient();

        boolean result = createPathForSync("/zach2", "I am Zach");

        if (result) {
            String data = getPathData("/zach2");
            System.out.println("从服务端获取的数据：" + data);
        }
    }

    /**
     * 创建节点
     * @param path 节点路径
     * @param data 节点数据
     * @return
     */
    public static boolean createPathForSync(String path, String data) {
        try {
            String resp = zooKeeper.create(path, data.getBytes(StandardCharsets.UTF_8)
                    , ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("响应：" + resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static String getPathData(String path) throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData(path, false, new Stat());
        return new String(data,StandardCharsets.UTF_8);
    }

    public static void setPathData(String path, String data) throws KeeperException, InterruptedException {
        zooKeeper.setData(path, data.getBytes(StandardCharsets.UTF_8),version.getAndIncrement());
    }
}
