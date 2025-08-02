package com.adaptive.platform.deployment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 部署引擎启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DeploymentApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DeploymentApplication.class, args);
    }
} 