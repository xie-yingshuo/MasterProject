package com.adaptive.platform.demo.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部署响应模型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentResponse {
    
    private String deploymentId;
    private String status;
    private String selectedCloudProvider;
    private String selectedRegion;
    private String selectedInstanceType;
    private Integer actualInstanceCount;
    private Double estimatedCostPerHour;
    private Double totalCostPerHour;
    private LocalDateTime deploymentTime;
    private List<String> deployedInstances;
    private String failureReason;
    
    // 计算成本效益
    public Double getCostEfficiency() {
        if (estimatedCostPerHour != null && estimatedCostPerHour > 0) {
            // 这里可以根据实际需求调整计算方式
            return 1.0 / estimatedCostPerHour;
        }
        return 0.0;
    }
} 