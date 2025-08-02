package com.adaptive.platform.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 监控应用启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MonitorApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }
} 