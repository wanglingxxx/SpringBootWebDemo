package com.research;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Adm on 2016/12/11.
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("com.research.mapper")
public class RunApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RunApplication.class, args);
    }

    @RequestMapping("/")
    String home() {
        System.out.print("你好 home ");
        return "redirect:index.html";
    }
}