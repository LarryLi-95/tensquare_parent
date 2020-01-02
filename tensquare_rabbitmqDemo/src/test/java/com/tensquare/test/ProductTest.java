package com.tensquare.test;

import com.tensquare.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.test
 * @className:ProductTest
 * @author:larry
 * @date:2019/12/30 14:55
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage1() {
        rabbitTemplate.convertAndSend("itcast", "直接模式测试");
    }

    /**
     * 分列模式
     */
    @Test
    public void sendMessage2() {
        rabbitTemplate.convertAndSend("chuanzhi", "","分列模式测试");
    }


    /**
     * 主题模式
     */
    @Test
    public void sendMessage3() {
        rabbitTemplate.convertAndSend("topic84", "good.log","主题模式测试");
    }
}
