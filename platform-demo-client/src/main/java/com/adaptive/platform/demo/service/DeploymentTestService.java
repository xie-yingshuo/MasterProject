package com.adaptive.platform.demo.service;

import com.adaptive.platform.demo.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 部署测试服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeploymentTestService {
    
    private final CloudDataLoader cloudDataLoader;
    
    /**
     * 模拟部署过程
     */
    public DeploymentResponse simulateDeployment(DeploymentRequest request) {
        List<CloudInstanceData> availableInstances = cloudDataLoader.generateTestData();
        
        // 根据策略筛选合适的实例
        List<CloudInstanceData> suitableInstances = cloudDataLoader.filterInstancesByStrategy(
            availableInstances, 
            request.getDeploymentStrategy(), 
            request.getCpuRequirement(), 
            request.getMemoryRequirement()
        );
        
        if (suitableInstances.isEmpty()) {
            return DeploymentResponse.builder()
                .deploymentId("DEP-" + System.currentTimeMillis())
                .status("FAILED")
                .failureReason("没有找到合适的实例")
                .deploymentTime(LocalDateTime.now())
                .build();
        }
        
        // 选择最佳实例
        CloudInstanceData selectedInstance = suitableInstances.get(0);
        
        // 计算成本
        double totalCostPerHour = selectedInstance.getOnDemandHourlyRate() * request.getInstanceCount();
        
        // 生成部署的实例列表
        List<String> deployedInstances = new ArrayList<>();
        for (int i = 0; i < request.getInstanceCount(); i++) {
            deployedInstances.add(selectedInstance.getInstanceName() + "-" + (i + 1));
        }
        
        return DeploymentResponse.builder()
            .deploymentId("DEP-" + System.currentTimeMillis())
            .status("SUCCESS")
            .selectedCloudProvider("Azure") // 基于数据来源
            .selectedRegion(selectedInstance.getRegion())
            .selectedInstanceType(selectedInstance.getInstanceName())
            .actualInstanceCount(request.getInstanceCount())
            .estimatedCostPerHour(selectedInstance.getOnDemandHourlyRate())
            .totalCostPerHour(totalCostPerHour)
            .deploymentTime(LocalDateTime.now())
            .deployedInstances(deployedInstances)
            .build();
    }
    
    /**
     * 运行性能测试
     */
    public List<TestResult> runPerformanceTest(Map<String, Object> testConfig) {
        List<TestResult> results = new ArrayList<>();
        
        // 生成不同规模的测试请求
        List<DeploymentRequest> testRequests = generateTestRequests(testConfig);
        
        for (DeploymentRequest request : testRequests) {
            TestResult result = TestResult.builder()
                .testName("性能测试-" + request.getServiceName())
                .testDescription("测试不同规模服务的部署性能")
                .startTime(LocalDateTime.now())
                .deploymentStrategy(request.getDeploymentStrategy())
                .request(request)
                .build();
            
            try {
                // 模拟部署过程
                DeploymentResponse response = simulateDeployment(request);
                result.setResponse(response);
                result.setStatus(TestResult.TestStatus.SUCCESS);
                
                // 添加性能指标
                Map<String, Object> metrics = new HashMap<>();
                metrics.put("deploymentTime", ThreadLocalRandom.current().nextInt(1000, 5000));
                metrics.put("resourceUtilization", ThreadLocalRandom.current().nextDouble(0.6, 0.9));
                metrics.put("throughput", ThreadLocalRandom.current().nextInt(1000, 10000));
                result.setMetrics(metrics);
                
            } catch (Exception e) {
                result.setStatus(TestResult.TestStatus.FAILED);
                result.setErrors(Arrays.asList(e.getMessage()));
            }
            
            result.setEndTime(LocalDateTime.now());
            result.calculateExecutionTime();
            results.add(result);
        }
        
        return results;
    }
    
    /**
     * 运行成本优化测试
     */
    public List<TestResult> runCostOptimizationTest(Map<String, Object> testConfig) {
        List<TestResult> results = new ArrayList<>();
        
        // 测试不同策略的成本效果
        String[] strategies = {"cost-optimized", "performance-optimized", "balanced"};
        
        for (String strategy : strategies) {
            DeploymentRequest request = DeploymentRequest.builder()
                .serviceName("cost-test-service")
                .serviceVersion("1.0.0")
                .cpuRequirement(4)
                .memoryRequirement(8)
                .instanceCount(2)
                .deploymentStrategy(strategy)
                .build();
            
            TestResult result = TestResult.builder()
                .testName("成本优化测试-" + strategy)
                .testDescription("测试" + strategy + "策略的成本效果")
                .startTime(LocalDateTime.now())
                .deploymentStrategy(strategy)
                .request(request)
                .build();
            
            try {
                DeploymentResponse response = simulateDeployment(request);
                result.setResponse(response);
                result.setStatus(TestResult.TestStatus.SUCCESS);
                
                // 添加成本指标
                Map<String, Object> metrics = new HashMap<>();
                metrics.put("hourlyCost", response.getTotalCostPerHour());
                metrics.put("monthlyCost", response.getTotalCostPerHour() * 24 * 30);
                metrics.put("costEfficiency", response.getCostEfficiency());
                result.setMetrics(metrics);
                
            } catch (Exception e) {
                result.setStatus(TestResult.TestStatus.FAILED);
                result.setErrors(Arrays.asList(e.getMessage()));
            }
            
            result.setEndTime(LocalDateTime.now());
            result.calculateExecutionTime();
            results.add(result);
        }
        
        return results;
    }
    
    /**
     * 运行对比测试
     */
    public Map<String, Object> runComparisonTest(Map<String, Object> testConfig) {
        Map<String, Object> comparisonResults = new HashMap<>();
        
        // 运行不同策略的测试
        List<TestResult> performanceResults = runPerformanceTest(testConfig);
        List<TestResult> costResults = runCostOptimizationTest(testConfig);
        
        // 计算对比指标
        Map<String, Object> performanceComparison = calculatePerformanceComparison(performanceResults);
        Map<String, Object> costComparison = calculateCostComparison(costResults);
        
        comparisonResults.put("performanceComparison", performanceComparison);
        comparisonResults.put("costComparison", costComparison);
        comparisonResults.put("overallRecommendation", generateRecommendation(performanceComparison, costComparison));
        
        return comparisonResults;
    }
    
    /**
     * 生成测试报告
     */
    public Map<String, Object> generateTestReport() {
        Map<String, Object> report = new HashMap<>();
        
        // 模拟测试数据
        report.put("totalTests", 50);
        report.put("successfulTests", 45);
        report.put("failedTests", 5);
        report.put("successRate", 90.0);
        
        // 性能指标
        Map<String, Object> performanceMetrics = new HashMap<>();
        performanceMetrics.put("averageDeploymentTime", 2500);
        performanceMetrics.put("averageResourceUtilization", 0.75);
        performanceMetrics.put("averageThroughput", 5000);
        report.put("performanceMetrics", performanceMetrics);
        
        // 成本指标
        Map<String, Object> costMetrics = new HashMap<>();
        costMetrics.put("averageHourlyCost", 0.15);
        costMetrics.put("averageMonthlyCost", 108.0);
        costMetrics.put("costSavings", 25.5);
        report.put("costMetrics", costMetrics);
        
        // 策略对比
        Map<String, Object> strategyComparison = new HashMap<>();
        strategyComparison.put("costOptimized", Map.of("cost", 0.12, "performance", 0.7));
        strategyComparison.put("performanceOptimized", Map.of("cost", 0.25, "performance", 0.9));
        strategyComparison.put("balanced", Map.of("cost", 0.18, "performance", 0.8));
        report.put("strategyComparison", strategyComparison);
        
        return report;
    }
    
    /**
     * 生成测试请求
     */
    private List<DeploymentRequest> generateTestRequests(Map<String, Object> testConfig) {
        List<DeploymentRequest> requests = new ArrayList<>();
        
        // 小规模服务
        requests.add(DeploymentRequest.builder()
            .serviceName("small-service")
            .serviceVersion("1.0.0")
            .cpuRequirement(2)
            .memoryRequirement(4)
            .instanceCount(1)
            .deploymentStrategy("cost-optimized")
            .build());
        
        // 中等规模服务
        requests.add(DeploymentRequest.builder()
            .serviceName("medium-service")
            .serviceVersion("1.0.0")
            .cpuRequirement(4)
            .memoryRequirement(8)
            .instanceCount(2)
            .deploymentStrategy("balanced")
            .build());
        
        // 大规模服务
        requests.add(DeploymentRequest.builder()
            .serviceName("large-service")
            .serviceVersion("1.0.0")
            .cpuRequirement(8)
            .memoryRequirement(16)
            .instanceCount(3)
            .deploymentStrategy("performance-optimized")
            .build());
        
        return requests;
    }
    
    /**
     * 计算性能对比
     */
    private Map<String, Object> calculatePerformanceComparison(List<TestResult> results) {
        Map<String, Object> comparison = new HashMap<>();
        
        double avgDeploymentTime = results.stream()
            .mapToLong(r -> r.getExecutionTimeMs())
            .average()
            .orElse(0.0);
        
        double avgResourceUtilization = results.stream()
            .mapToDouble(r -> (Double) r.getMetrics().getOrDefault("resourceUtilization", 0.0))
            .average()
            .orElse(0.0);
        
        comparison.put("averageDeploymentTime", avgDeploymentTime);
        comparison.put("averageResourceUtilization", avgResourceUtilization);
        comparison.put("totalTests", results.size());
        
        return comparison;
    }
    
    /**
     * 计算成本对比
     */
    private Map<String, Object> calculateCostComparison(List<TestResult> results) {
        Map<String, Object> comparison = new HashMap<>();
        
        double avgHourlyCost = results.stream()
            .mapToDouble(r -> r.getResponse().getTotalCostPerHour())
            .average()
            .orElse(0.0);
        
        double avgCostEfficiency = results.stream()
            .mapToDouble(r -> r.getResponse().getCostEfficiency())
            .average()
            .orElse(0.0);
        
        comparison.put("averageHourlyCost", avgHourlyCost);
        comparison.put("averageCostEfficiency", avgCostEfficiency);
        comparison.put("totalTests", results.size());
        
        return comparison;
    }
    
    /**
     * 生成推荐
     */
    private String generateRecommendation(Map<String, Object> performanceComparison, Map<String, Object> costComparison) {
        double avgCost = (Double) costComparison.get("averageHourlyCost");
        double avgPerformance = (Double) performanceComparison.get("averageResourceUtilization");
        
        if (avgCost < 0.15 && avgPerformance > 0.8) {
            return "推荐使用balanced策略，在成本和性能之间取得良好平衡";
        } else if (avgCost < 0.1) {
            return "推荐使用cost-optimized策略，成本控制效果显著";
        } else if (avgPerformance > 0.85) {
            return "推荐使用performance-optimized策略，性能表现优异";
        } else {
            return "建议根据具体业务需求选择合适的策略";
        }
    }
} 