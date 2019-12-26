package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.spit
 * @className:SpitApplication
 * @author:larry
 * @date:2019/12/26 14:56
 * @description:
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);

    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }
}
