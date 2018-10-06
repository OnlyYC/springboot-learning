package com.liaoyb.zookeeper.curator;

import com.liaoyb.zookeeper.ApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liaoyb
 * @date 2018-10-05 0:51
 */
@Slf4j
public class CuratorApiTest extends ApplicationTests {
    @Autowired
    private CuratorFramework client;

    @Test
    public void testNode() throws Exception {
        //创建节点，如果已经存在，抛出NodeExistsException
        client.create().forPath("/head", new byte[0]);
        //创建临时节点(断开连接，自动删除)
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/head/child", new byte[0]);
        //永久节点
        client.create().forPath("/head/child1", "node data".getBytes());


        //检查节点是否存在, 如果不存在，返回Null；如果存在，返回Stat对象。可以加上watch方法来监听节点变化
        Stat stat = client.checkExists().forPath("/head/child1");
        System.out.println(stat);

        //getData
        byte[] bs = client.getData().forPath("/head/child1");
        System.out.println("节点数据:" + new String(bs));


        //设置节点数据
        client.setData().forPath("/head/child1", "node update data".getBytes());

        //删除节点,如果节点不存在，抛出NoNodeException、非空节点则抛出NotEmptyException
        client.delete().forPath("/head/child1");

        //查询删除/head下节点(遍历删除)
        List<String> children = client.getChildren().forPath("/head");
        for (String childNode : children) {
            client.delete().forPath("/head/" + childNode);
        }
        client.delete().forPath("/head");
    }

    /**
     * 监听节点变化(当前监听节点)(监听到事件后，客户端还需再色或者一次监听，这样才能收到后面的监听事件)
     */
    @Test
    public void testWatch() throws Exception {
        client.create().forPath("/head", new byte[0]);

        client.checkExists().watched().forPath("/head");
        client.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                CuratorEventType type = event.getType();
                //事件包括CREATE、DELETE、EXISTS 、连接断开等
                if (type == CuratorEventType.WATCHED) {
                    WatchedEvent watchedEvent = event.getWatchedEvent();
                    Watcher.Event.EventType eventType = watchedEvent.getType();
                    log.info("节点监听事件:" + eventType + ":" + watchedEvent.getPath());
                    //这里还需添加监听（一个watch只能使用一次，如果需要多次,需要再添加watch）
                    Stat stat = client.checkExists().watched().forPath(watchedEvent.getPath());
                    byte[] bytes = client.getData().forPath(watchedEvent.getPath());
                    log.info("节点数据:" + new String(bytes));
                }
            }
        });
        //修改节点数据
        client.setData().forPath("/head", "node data".getBytes());
        TimeUnit.SECONDS.sleep(10);
        client.delete().forPath("/head");
    }

    /**
     * 节点及子节点时间监听
     *
     * @throws Exception
     */
    @Test
    public void registerWatcher() throws Exception {
        PathChildrenCache watcher = new PathChildrenCache(client, "/head", true);
        watcher.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                log.info("事件:" + event);
            }
        });
        watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        log.info("注册watcher成功...");
        TimeUnit.SECONDS.sleep(20);
    }


    /**
     * 树形监听(初次开启监听器会把当前节点及所有子目录节点，触发[type=NODE_ADDED]事件添加所有节点（小等于maxDepth目录）)
     * [type=NODE_UPDATED],set更新节点值操作，范围[当前节点，maxDepth目录节点](闭区间)
     * [type=NODE_ADDED] 增加节点 范围[当前节点，maxDepth目录节点](左闭右闭区间)
     * [type=NODE_REMOVED] 删除节点， 范围[当前节点， maxDepth目录节点](闭区间),删除当前节点无异常
     * ...
     *
     * @throws Exception
     */
    @Test
    public void registerTreeCache() throws Exception {
        // maxDepth值设置说明，比如当前监听节点/t1，目录最深为/t1/t2/t3/t4,则maxDepth=3,说明下面3级子目录全
        TreeCache treeCache = TreeCache.newBuilder(client, "/").setCacheData(true).setMaxDepth(2).build();

        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                log.info("树形监听:" + event);
            }
        });
        //没有开启模式作为入参的方法
        treeCache.start();
        TimeUnit.SECONDS.sleep(20);
    }
}
