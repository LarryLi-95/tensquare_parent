package com.tensquare.sms.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.sms.util
 * @className:SendSms
 * @author:larry
 * @date:2019/12/30 17:06
 * @description:
 */
@Component
public class SendSms {
    //对应自身阿里云账户的assessKeyId
    private static final String assessKeyId = "LTAI4FczSLSgpzMALuqyy7Ar";
    //对应自身阿里云账户的assessKeySecret
    private static final String assessKeySecret = "Tz0l4txsCVpWOhwwb20j4x1Zs9zbzv";
    //对应阿里云的签名名称
    private static final String signName = "十次方论坛";
    //对应阿里云的模板code
    private static final String templateCode = "SMS_181545939";


    /**
     * 短信发送
     */
    public static void sendCode(String telephone, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("default", assessKeyId, assessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //请求方式
        request.setMethod(MethodType.POST);
        //请求地址
        request.setDomain("dysmsapi.aliyuncs.com");
        //对应版本号
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        //电话号码
        request.putQueryParameter("PhoneNumbers", telephone);
        //签名
        request.putQueryParameter("SignName", signName);
        //模板code
        request.putQueryParameter("TemplateCode", templateCode);
        //验证码
        request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

}
