package com.adaptive.platform.common.constant;

/**
 * 平台常量定义
 */
public class PlatformConstants {
    
    /**
     * API版本
     */
    public static final String API_VERSION = "v1";
    
    /**
     * API基础路径
     */
    public static final String API_BASE_PATH = "/api/" + API_VERSION;
    
    /**
     * 部署状态
     */
    public static class DeploymentStatus {
        public static final String PENDING = "PENDING";
        public static final String RUNNING = "RUNNING";
        public static final String SUCCESS = "SUCCESS";
        public static final String FAILED = "FAILED";
        public static final String CANCELLED = "CANCELLED";
    }
    
    /**
     * 云平台类型
     */
    public static class CloudProvider {
        public static final String AWS = "AWS";
        public static final String AZURE = "AZURE";
        public static final String GCP = "GCP";
        public static final String ALIYUN = "ALIYUN";
        public static final String KUBERNETES = "KUBERNETES";
    }
    
    /**
     * 资源类型
     */
    public static class ResourceType {
        public static final String CPU = "CPU";
        public static final String MEMORY = "MEMORY";
        public static final String STORAGE = "STORAGE";
        public static final String NETWORK = "NETWORK";
    }
    
    /**
     * 监控指标类型
     */
    public static class MetricType {
        public static final String CPU_USAGE = "CPU_USAGE";
        public static final String MEMORY_USAGE = "MEMORY_USAGE";
        public static final String NETWORK_IO = "NETWORK_IO";
        public static final String DISK_IO = "DISK_IO";
        public static final String RESPONSE_TIME = "RESPONSE_TIME";
        public static final String THROUGHPUT = "THROUGHPUT";
    }
    
    /**
     * 默认配置
     */
    public static class DefaultConfig {
        public static final int DEFAULT_PORT = 8080;
        public static final String DEFAULT_HOST = "localhost";
        public static final int DEFAULT_TIMEOUT = 30000; // 30秒
        public static final int DEFAULT_RETRY_COUNT = 3;
    }
} 