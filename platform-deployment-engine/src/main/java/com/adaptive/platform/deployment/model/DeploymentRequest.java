package com.adaptive.platform.deployment.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.List;

/**
 * 部署请求模型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentRequest {
    
    /**
     * 服务名称
     */
    private String serviceName;
    
    /**
     * 服务版本
     */
    private String version;
    
    /**
     * 镜像地址
     */
    private String imageUrl;
    
    /**
     * 端口映射
     */
    private List<PortMapping> portMappings;
    
    /**
     * 环境变量
     */
    private Map<String, String> environment;
    
    /**
     * 资源配置
     */
    private ResourceConfig resourceConfig;
    
    /**
     * 部署策略
     */
    private DeploymentStrategy strategy;
    
    /**
     * 目标云平台
     */
    private String targetCloud;
    
    /**
     * 端口映射
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PortMapping {
        private Integer containerPort;
        private Integer hostPort;
        private String protocol;
    }
    
    /**
     * 资源配置
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResourceConfig {
        private Double cpu;
        private String memory;
        private String storage;
    }
    
    /**
     * 部署策略
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeploymentStrategy {
        private String type; // BLUE_GREEN, CANARY, ROLLING
        private Integer replicas;
        private Boolean autoScaling;
        private Integer minReplicas;
        private Integer maxReplicas;
    }
} 