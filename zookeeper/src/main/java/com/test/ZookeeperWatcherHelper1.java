package com.test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperWatcherHelper1 {

    private CountDownLatch latch = new CountDownLatch(1);

    ZookeeperWatcherHelper1() {
    }

    private static final int SESSION_TIMEOUT = 30000;
    private static final String ROOT_PATH = "/zk_test/app";
//    private static final String ROOT_PATH = "/zookroot/app";
    private ZooKeeper zooKeeper;

    public void connect() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.208.186:2181", SESSION_TIMEOUT,
                new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        // 连接状态
                        KeeperState keeperState = event.getState();
                        // 事件类型
                        EventType eventType = event.getType();
                        if (KeeperState.SyncConnected == keeperState) {
                            // 成功连接上ZK服务器
                            if (EventType.None == eventType) {
                                System.out.println("zookeeper is ok !");
                                latch.countDown();
                            }
                        }
                    }
                });
        latch.await();
        syncNodes();
    }

    public void close() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

    public void syncNodes() {
        try {
            zooKeeper.exists(ROOT_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    // 主结点的数据发生改变时
                    if (event.getType() == EventType.NodeDataChanged) {
                        try {
                            byte b[] = zooKeeper
                                    .getData(ROOT_PATH, false, null);
                            System.out.println("getData: " + new String(b));
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.info("Node data changed:" + event.getPath());
                    }
                    if (event.getType() == EventType.NodeDeleted) {
                        Log.info("Node deleted:" + event.getPath());
                        try {
                            zooKeeper.getData(ROOT_PATH, this, null);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (event.getType() == EventType.NodeCreated) {
                        Log.info("Node created:" + event.getPath());
                        try {
                            zooKeeper.exists(ROOT_PATH, this);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    syncNodes();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ZookeeperWatcherHelper helper = new ZookeeperWatcherHelper();
        try {
            helper.connect();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        for (;;) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Log1 {
    public static void info(String info) {
        System.out.println(info);
    }
}
