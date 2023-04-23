package com.moon.example.rocketmq.service;

import com.moon.example.rocketmq.model.Product;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * 事务消息监听实现类。
 * 需要继承 RocketMQLocalTransactionListener 接口，实现 executeLocalTransaction 与 checkLocalTransaction 方法。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-07 10:32
 * @description
 */
@Service
@RocketMQTransactionListener
public class TxMessageServiceListener implements RocketMQLocalTransactionListener {

    /**
     * 执行本地事务
     *
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("executeLocalTransaction 方法调用，执行本地事务...");
        String txId = (String) msg.getHeaders().get("tx_id");
        Product product = (Product) arg;
        System.out.println("executeLocalTransaction 方法获取到的消息体：" + txId);
        System.out.println("executeLocalTransaction 方法获取到的参数：" + product);

        // 模拟本地一些业务逻辑(30s)
        try {
            Thread.sleep(30000);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 消息回查
     *
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println("checkLocalTransaction 方法调用，进行消息回查...");
        String txId = (String) msg.getHeaders().get("tx_id");
        System.out.println("checkLocalTransaction 方法获取到的消息体：" + txId);

        // 模拟本地一些业务逻辑(10s)，如：查询本地数据库，是否已经操作成功 orderDao.findById(txId)
        try {
            Thread.sleep(10000);
            // 如果确认本地事务成功，则提交
            return RocketMQLocalTransactionState.COMMIT;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
