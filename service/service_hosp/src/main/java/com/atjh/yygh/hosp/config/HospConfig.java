package com.atjh.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.atjh.yygh.*.mapper")
public class HospConfig {
}
