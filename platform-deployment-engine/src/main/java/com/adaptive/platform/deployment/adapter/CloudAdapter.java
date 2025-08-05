package com.adaptive.platform.deployment.adapter;

import com.adaptive.platform.deployment.model.DeploymentRequest;
import com.adaptive.platform.deployment.model.DeploymentResponse;

import java.util.List;
import java.util.Map;

/**
 * 云平台适配器接口
 */
public interface CloudAdapter {
    
    /**
     * 创建实例
     */
    DeploymentResponse createInstance(DeploymentRequest request);
    
    /**
     * 删除实例
     */
    void deleteInstance(String instanceId);
    
    /**
     * 获取实例状态
     */
    String getInstanceStatus(String instanceId);
    
    /**
     * 获取可用实例类型
     */
    List<Map<String, Object>> getAvailableInstanceTypes(String region);
    
    /**
     * 获取实例价格
     */
    Double getInstancePrice(String instanceType, String region);
    
    /**
     * 检查资源可用性
     */
    boolean checkResourceAvailability(String instanceType, String region, Integer count);
} 