package com.adaptive.platform.deployment.controller;

import com.adaptive.platform.common.response.Result;
import com.adaptive.platform.deployment.model.DeploymentRequest;
import com.adaptive.platform.deployment.model.DeploymentResponse;
import com.adaptive.platform.deployment.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
     * 创建部署任务
     */
    @PostMapping("/create")
    public Result<DeploymentResponse> createDeployment(@Valid @RequestBody DeploymentRequest request) {
        log.info("收到部署请求: {}", request);
        DeploymentResponse response = deploymentService.createDeployment(request);
        return Result.success(response);
    }
    
    /**
     * 获取部署状态
     */
    @GetMapping("/{deploymentId}/status")
    public Result<DeploymentResponse> getDeploymentStatus(@PathVariable String deploymentId) {
        DeploymentResponse response = deploymentService.getDeploymentStatus(deploymentId);
        return Result.success(response);
    }
    
    /**
     * 取消部署
     */
    @PostMapping("/{deploymentId}/cancel")
    public Result<Void> cancelDeployment(@PathVariable String deploymentId) {
        deploymentService.cancelDeployment(deploymentId);
        return Result.success();
    }
} 