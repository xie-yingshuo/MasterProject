package com.adaptive.platform.demo.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 测试结果模型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {
    
    private String testName;
    private String testDescription;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long executionTimeMs;
    private String deploymentStrategy;
    private DeploymentRequest request;
    private DeploymentResponse response;
    private Map<String, Object> metrics;
    private List<String> errors;
    private TestStatus status;
    
    public enum TestStatus {
        SUCCESS, FAILED, PARTIAL_SUCCESS
    }
    
    // 计算执行时间
    public void calculateExecutionTime() {
        if (startTime != null && endTime != null) {
            this.executionTimeMs = java.time.Duration.between(startTime, endTime).toMillis();
        }
    }
} 