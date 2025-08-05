package com.adaptive.platform.monitor.service;

import java.util.List;
import java.util.Map;

/**
 * 监控服务接口
 */
public interface MonitorService {
    
    /**
     * 收集性能指标
     */
    void collectMetrics(String serviceName, Map<String, Object> metrics);
    
    /**
     * 获取服务性能指标
     */
    List<Map<String, Object>> getServiceMetrics(String serviceName);
    
    /**
     * 获取系统整体性能
     */
    Map<String, Object> getSystemPerformance();
    
    /**
     * 设置告警规则
     */
    void setAlertRule(String ruleName, Map<String, Object> rule);
    
    /**
     * 检查告警
     */
    List<Map<String, Object>> checkAlerts();
    
    /**
     * 获取资源使用情况
     */
    Map<String, Object> getResourceUsage(String provider);
} 