package com.template.demo;

import com.template.demo.service.ComponentDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication(scanBasePackages = { "com.template" })
public class Application {

    private static ConfigurableApplicationContext context;

    public static void main(final String[] args) {
        context = SpringApplication.run(Application.class, args);
        context.getBean(ComponentDetailsService.class)
                .createComponentDetails(context.getEnvironment().getProperty("spring.application.name"));
    }

}