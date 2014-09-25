package com.test;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookeeperWatcherHelper extends Thread implements Watcher {

    private CountDownLatch latch = new CountDownLatch(1);

    ZookeeperWatcherHelper() {
        if (this.getState().compareTo(State.NEW) == 0) {
            System.out.println("state: " + this.getState());
        }
    }

    private static final int SESSION_TIMEOUT = 30000;
//    private static final String ROOT_PATH = "/zookroot";
    private static final String ROOT_PATH = "/zk_test";
    private static List<String> nodeList;// 所要监控的结点的子结点列表
    private ZooKeeper zooKeeper;

    public void connect() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.208.186:2181", SESSION_TIMEOUT, this);
        latch.await();
        this.start();
    }

    public void close() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

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

    public void syncNodes() {
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ZookeeperWatcherHelper().syncNodes();
    }

    @Override
    public void run() {
        synchronized (this) {
            Watcher wc = new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    // 结点数据改变之前的结点列表
                    List<String> nodeListBefore = nodeList;
                    // 主结点的数据发生改变时
                    if (event.getType() == EventType.NodeDataChanged) {
                        Log.info("Node data changed:" + event.getPath());
                    }
                    if (event.getType() == EventType.NodeDeleted) {
                        Log.info("Node deleted:" + event.getPath());
                    }
                    if (event.getType() == EventType.NodeCreated) {
                        Log.info("Node created:" + event.getPath());
                    }

                    // 获取更新后的nodelist
                    try {
                        nodeList = zooKeeper
                                .getChildren(event.getPath(), false);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                        System.out.println(event.getPath()
                                + " has no child, deleted.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<String> nodeListNow = nodeList;
                    // 增加结点
                    if (nodeListBefore.size() < nodeListNow.size()) {
                        for (String str : nodeListNow) {
                            if (!nodeListBefore.contains(str)) {
                                Log.info("Node created:" + event.getPath()
                                        + "/" + str);
                            }
                        }
                    }
                }

            };
            /**
             * 持续监控ROOT_PATH下的结点
             */
            for (;;) {
                try {
                    zooKeeper.exists(ROOT_PATH, wc);// 所要监控的主结点
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    nodeList = zooKeeper.getChildren(ROOT_PATH, wc);
                    for (String child : nodeList) {
                        Log.info("child: " + child);
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                // 对PATH下的每个结点都设置一个watcher

                for (String nodeName : nodeList) {
                    try {
                        zooKeeper.exists(ROOT_PATH + "/" + nodeName, wc);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(3000);// sleep一会，减少CUP占用率
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Log {
    public static void info(String info) {
        System.out.println(info);
    }
}