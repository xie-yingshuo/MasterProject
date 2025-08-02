package com.adaptive.platform.deployment.controller;

import com.adaptive.platform.common.model.ApiResponse;
import com.adaptive.platform.deployment.model.DeploymentRequest;
import com.adaptive.platform.deployment.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 部署控制器
 */
@Slf4j
@RestController
@RequestMapping("/deployment")
@RequiredArgsConstructor
public class DeploymentController {
    
    private final DeploymentService deploymentService;
    
    /**
     * 创建部署
     */
    @PostMapping
    public ApiResponse<String> createDeployment(@Valid @RequestBody DeploymentRequest request) {
        log.info("Received deployment request: {}", request.getServiceName());
        String deploymentId = deploymentService.createDeployment(request);
        return ApiResponse.success(deploymentId);
    }
    
    /**
     * 获取部署状态
     */
    @GetMapping("/{deploymentId}")
    public ApiResponse<Object> getDeploymentStatus(@PathVariable String deploymentId) {
        log.info("Getting deployment status for: {}", deploymentId);
        Object status = deploymentService.getDeploymentStatus(deploymentId);
        return ApiResponse.success(status);
    }
    
    /**
     * 取消部署
     */
    @DeleteMapping("/{deploymentId}")
    public ApiResponse<Void> cancelDeployment(@PathVariable String deploymentId) {
        log.info("Cancelling deployment: {}", deploymentId);
        deploymentService.cancelDeployment(deploymentId);
        return ApiResponse.success();
    }
    
    /**
     * 健康检查
     */
    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("Deployment Engine is running");
    }
} 