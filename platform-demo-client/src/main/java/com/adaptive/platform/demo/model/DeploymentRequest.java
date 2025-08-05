package com.adaptive.platform.demo.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 部署请求模型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentRequest {
    
    @NotBlank(message = "服务名称不能为空")
    private String serviceName;
    
    @NotBlank(message = "服务版本不能为空")
    private String serviceVersion;
    
    @NotNull(message = "CPU需求不能为空")
    @Min(value = 1, message = "CPU需求至少为1")
    private Integer cpuRequirement;
    
    @NotNull(message = "内存需求不能为空")
    @Min(value = 1, message = "内存需求至少为1GB")
    private Integer memoryRequirement;
    
    @NotNull(message = "实例数量不能为空")
    @Min(value = 1, message = "实例数量至少为1")
    private Integer instanceCount;
    
    private String preferredRegion;
    private String preferredCloudProvider;
    private Double maxBudgetPerHour;
    private String deploymentStrategy; // "cost-optimized", "performance-optimized", "balanced"
    
    // 计算总资源需求
    public Integer getTotalCpuRequirement() {
        return cpuRequirement * instanceCount;
    }
    
    public Integer getTotalMemoryRequirement() {
        return memoryRequirement * instanceCount;
    }
} 