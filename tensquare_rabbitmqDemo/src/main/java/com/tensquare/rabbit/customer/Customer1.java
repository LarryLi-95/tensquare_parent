package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.rabbit.customer
 * @className:Customer1
 * @author:larry
 * @date:2019/12/30 15:01
 * @description:
 */
@Component
@RabbitListener(queues = "itcast")
public class Customer1 {

    @RabbitHandler
    public void getMessage(String msg){
        System.out.println("itcast:"+msg);
    }
}
