package com.atos.apps.photo.app.api.users;

import feign.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PhotoAppApiUsersApplication {

    private final Environment environment;

    @Autowired
    public PhotoAppApiUsersApplication(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppApiUsersApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    @Profile("production")
    public Logger.Level feignProductionLogger() {
        return Logger.Level.BASIC;
    }

    @Bean
    @Profile("production")
    public String createProductionEnvironmentProperties() {
        System.out.println("Production bean created " + environment.getProperty("myapplication.environment"));
        return "production environment properties";
    }

    @Bean
    @Profile("!production")
    public String createNotProductionEnvironmentProperties() {
        System.out.println("Not Production bean created " + environment.getProperty("myapplication.environment"));
        return "Not production environment properties";
    }

    @Bean
    @Profile("development")
    public String createDevelopmentEnvironmentProperties() {
        System.out.println("Development bean created " + environment.getProperty("myapplication.environment"));
        return "development environment properties";
    }
}
