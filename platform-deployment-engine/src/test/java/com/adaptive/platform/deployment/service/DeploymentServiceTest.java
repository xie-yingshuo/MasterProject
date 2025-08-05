package com.adaptive.platform.deployment.service;

import com.adaptive.platform.deployment.model.DeploymentRequest;
import com.adaptive.platform.deployment.model.DeploymentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 部署服务测试
 */
@SpringBootTest
public class DeploymentServiceTest {
    
    @Autowired
    private DeploymentService deploymentService;
    
    @Test
    public void testCreateDeployment() {
        // 创建测试请求
        DeploymentRequest request = DeploymentRequest.builder()
            .serviceName("test-service")
            .serviceVersion("1.0.0")
            .cpuRequirement(2)
            .memoryRequirement(4)
            .instanceCount(1)
            .deploymentStrategy("cost-optimized")
            .build();
        
        // 执行部署
        DeploymentResponse response = deploymentService.createDeployment(request);
        
        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getDeploymentId());
        assertEquals("SUCCESS", response.getStatus());
    }
    
    @Test
    public void testGetDeploymentStatus() {
        // 创建部署
        DeploymentRequest request = DeploymentRequest.builder()
            .serviceName("test-service")
            .serviceVersion("1.0.0")
            .cpuRequirement(2)
            .memoryRequirement(4)
            .instanceCount(1)
            .build();
        
        DeploymentResponse createResponse = deploymentService.createDeployment(request);
        
        // 获取状态
        DeploymentResponse statusResponse = deploymentService.getDeploymentStatus(createResponse.getDeploymentId());
        
        // 验证结果
        assertNotNull(statusResponse);
        assertEquals(createResponse.getDeploymentId(), statusResponse.getDeploymentId());
    }
} 