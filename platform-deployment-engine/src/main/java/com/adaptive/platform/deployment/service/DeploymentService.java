package com.adaptive.platform.deployment.service;

import com.adaptive.platform.deployment.model.DeploymentRequest;
import com.adaptive.platform.deployment.model.DeploymentResponse;

/**
 * 部署服务接口
 */
public interface DeploymentService {
    
    /**
     * 创建部署任务
     */
    DeploymentResponse createDeployment(DeploymentRequest request);
    
    /**
     * 获取部署状态
     */
    DeploymentResponse getDeploymentStatus(String deploymentId);
    
    /**
     * 取消部署
     */
    void cancelDeployment(String deploymentId);
    
    /**
     * 更新部署状态
     */
    void updateDeploymentStatus(String deploymentId, String status);
} 