package com.ali.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MutipleSqlManagerConfig {

    @Bean
    public MutipleSqlManager mutipleSqlManager(Environment environment){
        return new MutipleSqlManager(environment);
    }

}
