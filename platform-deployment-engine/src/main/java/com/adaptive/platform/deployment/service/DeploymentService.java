package com.adaptive.platform.deployment.service;

import com.adaptive.platform.deployment.model.DeploymentRequest;

/**
 * 部署服务接口
 */
public interface DeploymentService {
    
    /**
     * 创建部署
     * @param request 部署请求
     * @return 部署ID
     */
    String createDeployment(DeploymentRequest request);
    
    /**
     * 获取部署状态
     * @param deploymentId 部署ID
     * @return 部署状态
     */
    Object getDeploymentStatus(String deploymentId);
    
    /**
     * 取消部署
     * @param deploymentId 部署ID
     */
    void cancelDeployment(String deploymentId);
} 