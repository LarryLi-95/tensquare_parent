package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.qa.client.impl
 * @className:BaseClientImpl
 * @author:larry
 * @date:2020/1/3 10:26
 * @description:
 */
@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {

        return new Result(false, StatusCode.ERROR, "熔断器触发了!");
    }
}
