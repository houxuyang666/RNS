package com.tdkj.RNS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("com.tdkj.RNS.dao")
public class RNSApplication {

    public static void main(String[] args) {
        SpringApplication.run(RNSApplication.class, args);
    }

}
