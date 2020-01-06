package com.tensquare.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.web
 * @className:WebApplication
 * @author:larry
 * @date:2020/1/3 11:04
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
