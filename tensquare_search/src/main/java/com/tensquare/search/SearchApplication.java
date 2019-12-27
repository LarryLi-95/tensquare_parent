package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.search
 * @className:SearchApplication
 * @author:larry
 * @date:2019/12/27 14:06
 * @description:
 */
@SpringBootApplication
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }
}
