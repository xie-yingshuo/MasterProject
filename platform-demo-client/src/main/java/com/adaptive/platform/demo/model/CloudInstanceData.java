package com.adaptive.platform.demo.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 云实例数据模型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudInstanceData {
    
    private String instanceName;
    private String region;
    private String operatingSystem;
    private String instanceType;
    private Integer vcpu;
    private Double onDemandHourlyRate;
    private String memory;
    private String temporaryStorage;
    
    // 计算性价比 (vCPU/价格)
    public Double getCostEfficiency() {
        if (onDemandHourlyRate != null && onDemandHourlyRate > 0) {
            return vcpu / onDemandHourlyRate;
        }
        return 0.0;
    }
    
    // 获取内存GB数值
    public Integer getMemoryGB() {
        if (memory != null && memory.contains("GiB")) {
            return Integer.parseInt(memory.replace(" GiB", ""));
        }
        return 0;
    }
} 