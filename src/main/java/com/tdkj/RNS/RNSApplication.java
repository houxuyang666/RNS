package com.tdkj.RNS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tdkj.RNS.mapper")
public class RNSApplication {

	public static void main(String[] args) {
		SpringApplication.run(RNSApplication.class, args);
	}

}
