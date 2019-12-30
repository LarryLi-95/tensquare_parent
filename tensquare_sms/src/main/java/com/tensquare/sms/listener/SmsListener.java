package com.tensquare.sms.listener;

import com.tensquare.sms.util.SendSms;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Map;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.sms.listener
 * @className:SmsListener
 * @author:larry
 * @date:2019/12/30 16:51
 * @description:
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
//    @RabbitHandler
//    public void executeSms(Map<String, String> map) {
//        System.out.println("手机号:" + map.get("mobile"));
//        System.out.println("验证码:" + map.get("checkCode"));
//    }

    /**
     * 发送短信
     */
    @RabbitHandler
    public void sendSms(Map<String, String> map) {
        String telephone = map.get("mobile");

        String code = map.get("checkCode");
        SendSms.sendCode(telephone, code);
    }

}
