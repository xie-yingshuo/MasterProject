package com.adaptive.platform.common.exception;

/**
 * 平台自定义异常
 */
public class PlatformException extends RuntimeException {
    
    private final Integer code;
    
    public PlatformException(String message) {
        super(message);
        this.code = 500;
    }
    
    public PlatformException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    
    public PlatformException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }
    
    public PlatformException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
    
    public Integer getCode() {
        return code;
    }
    
    /**
     * 资源不存在异常
     */
    public static class ResourceNotFoundException extends PlatformException {
        public ResourceNotFoundException(String message) {
            super(404, message);
        }
    }
    
    /**
     * 部署失败异常
     */
    public static class DeploymentException extends PlatformException {
        public DeploymentException(String message) {
            super(500, message);
        }
        
        public DeploymentException(String message, Throwable cause) {
            super(500, message, cause);
        }
    }
    
    /**
     * 配置错误异常
     */
    public static class ConfigurationException extends PlatformException {
        public ConfigurationException(String message) {
            super(400, message);
        }
    }
    
    /**
     * 云平台连接异常
     */
    public static class CloudConnectionException extends PlatformException {
        public CloudConnectionException(String message) {
            super(503, message);
        }
        
        public CloudConnectionException(String message, Throwable cause) {
            super(503, message, cause);
        }
    }
} 