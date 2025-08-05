package com.adaptive.platform.demo.controller;

import com.adaptive.platform.common.response.Result;
import com.adaptive.platform.demo.model.*;
import com.adaptive.platform.demo.service.CloudDataLoader;
import com.adaptive.platform.demo.service.DeploymentTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Demo控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {
    
    private final CloudDataLoader cloudDataLoader;
    private final DeploymentTestService deploymentTestService;
    
    /**
     * 获取云实例数据
     */
    @GetMapping("/cloud-instances")
    public Result<List<CloudInstanceData>> getCloudInstances() {
        List<CloudInstanceData> instances = cloudDataLoader.generateTestData();
        return Result.success(instances);
    }
    
    /**
     * 根据策略筛选实例
     */
    @PostMapping("/filter-instances")
    public Result<List<CloudInstanceData>> filterInstances(@RequestBody Map<String, Object> request) {
        String strategy = (String) request.get("strategy");
        Integer cpuRequirement = (Integer) request.get("cpuRequirement");
        Integer memoryRequirement = (Integer) request.get("memoryRequirement");
        
        List<CloudInstanceData> allInstances = cloudDataLoader.generateTestData();
        List<CloudInstanceData> filteredInstances = cloudDataLoader.filterInstancesByStrategy(
            allInstances, strategy, cpuRequirement, memoryRequirement);
        
        return Result.success(filteredInstances);
    }
    
    /**
     * 模拟部署请求
     */
    @PostMapping("/deploy")
    public Result<DeploymentResponse> deploy(@Valid @RequestBody DeploymentRequest request) {
        log.info("收到部署请求: {}", request);
        
        // 模拟部署过程
        DeploymentResponse response = deploymentTestService.simulateDeployment(request);
        
        return Result.success(response);
    }
    
    /**
     * 运行性能测试
     */
    @PostMapping("/performance-test")
    public Result<List<TestResult>> runPerformanceTest(@RequestBody Map<String, Object> testConfig) {
        log.info("开始性能测试: {}", testConfig);
        
        List<TestResult> results = deploymentTestService.runPerformanceTest(testConfig);
        
        return Result.success(results);
    }
    
    /**
     * 运行成本优化测试
     */
    @PostMapping("/cost-optimization-test")
    public Result<List<TestResult>> runCostOptimizationTest(@RequestBody Map<String, Object> testConfig) {
        log.info("开始成本优化测试: {}", testConfig);
        
        List<TestResult> results = deploymentTestService.runCostOptimizationTest(testConfig);
        
        return Result.success(results);
    }
    
    /**
     * 运行对比测试
     */
    @PostMapping("/comparison-test")
    public Result<Map<String, Object>> runComparisonTest(@RequestBody Map<String, Object> testConfig) {
        log.info("开始对比测试: {}", testConfig);
        
        Map<String, Object> results = deploymentTestService.runComparisonTest(testConfig);
        
        return Result.success(results);
    }
    
    /**
     * 获取测试报告
     */
    @GetMapping("/test-report")
    public Result<Map<String, Object>> getTestReport() {
        Map<String, Object> report = deploymentTestService.generateTestReport();
        return Result.success(report);
    }
} 