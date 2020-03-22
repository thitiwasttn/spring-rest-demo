package com.thitiwas.config;


import com.thitiwas.controller.FrontController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.thitiwas")
public class DemoAppConfig {

    @Bean(initMethod = "init")
    public FrontController frontController()
    {
        return new FrontController();
    }
}
