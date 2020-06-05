package com.sh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created By Sunhu At 2020/6/3 9:53
 *
 * @author Sun
 */
@SpringBootApplication
@MapperScan(basePackages = "com.sh.mapper")
public class ComponentApp {

    public static void main(String[] args) {
        SpringApplication.run(ComponentApp.class,args);
    }


}
