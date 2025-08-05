package com.adaptive.platform.deployment.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 部署请求模型
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deployment_request")
public class DeploymentRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
    private String deploymentStrategy;
    
    @Enumerated(EnumType.STRING)
    private DeploymentStatus status;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public enum DeploymentStatus {
        PENDING, PROCESSING, SUCCESS, FAILED, CANCELLED
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 