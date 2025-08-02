package com.adaptive.platform.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 资源管理应用启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ResourceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
} 