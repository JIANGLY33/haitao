package com.jalinyiel.haitao.haitao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {
        "com.jalinyiel.haitao.haitao.mapper"
})
public class HaitaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaitaoApplication.class, args);
    }

}
