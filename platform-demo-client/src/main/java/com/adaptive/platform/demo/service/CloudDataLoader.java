package com.adaptive.platform.demo.service;

import com.adaptive.platform.demo.model.CloudInstanceData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 云数据加载服务
 */
@Slf4j
@Service
public class CloudDataLoader {
    
    /**
     * 加载云实例数据
     */
    public List<CloudInstanceData> loadCloudInstanceData() {
        List<CloudInstanceData> data = new ArrayList<>();
        
        try (InputStream is = getClass().getResourceAsStream("/data/cloud-instances.csv");
             InputStreamReader reader = new InputStreamReader(is);
             CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            
            for (CSVRecord record : parser) {
                CloudInstanceData instance = CloudInstanceData.builder()
                    .instanceName(record.get("Instance Name"))
                    .region(record.get("Region"))
                    .operatingSystem(record.get("Operating System"))
                    .instanceType(record.get("Instance Type"))
                    .vcpu(Integer.parseInt(record.get("vCPU")))
                    .onDemandHourlyRate(Double.parseDouble(record.get("On-Demand Hourly Rate")))
                    .memory(record.get("Memory"))
                    .temporaryStorage(record.get("Temporary Storage"))
                    .build();
                
                data.add(instance);
            }
            
            log.info("成功加载 {} 条云实例数据", data.size());
            
        } catch (IOException e) {
            log.error("加载云实例数据失败", e);
        }
        
        return data;
    }
    
    /**
     * 根据策略筛选实例
     */
    public List<CloudInstanceData> filterInstancesByStrategy(List<CloudInstanceData> allInstances, 
                                                           String strategy, 
                                                           Integer cpuRequirement, 
                                                           Integer memoryRequirement) {
        
        return allInstances.stream()
            .filter(instance -> instance.getVcpu() >= cpuRequirement)
            .filter(instance -> instance.getMemoryGB() >= memoryRequirement)
            .sorted((a, b) -> {
                switch (strategy) {
                    case "cost-optimized":
                        return Double.compare(a.getOnDemandHourlyRate(), b.getOnDemandHourlyRate());
                    case "performance-optimized":
                        return Double.compare(b.getVcpu(), a.getVcpu());
                    case "balanced":
                        return Double.compare(b.getCostEfficiency(), a.getCostEfficiency());
                    default:
                        return 0;
                }
            })
            .collect(Collectors.toList());
    }
    
    /**
     * 生成测试数据
     */
    public List<CloudInstanceData> generateTestData() {
        List<CloudInstanceData> testData = new ArrayList<>();
        
        // 添加你提供的20条数据
        String[][] rawData = {
            {"B2pts v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "2", "0.0095", "1 GiB", "0 GiB"},
            {"B2pls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "2", "0.038", "4 GiB", "0 GiB"},
            {"B2ps v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "2", "0.0759", "8 GiB", "0 GiB"},
            {"B4pls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "4", "0.134", "8 GiB", "0 GiB"},
            {"B4ps v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "4", "0.152", "16 GiB", "0 GiB"},
            {"B8pls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "8", "0.269", "16 GiB", "0 GiB"},
            {"B8ps v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "8", "0.304", "32 GiB", "0 GiB"},
            {"B16pls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "16", "0.538", "32 GiB", "0 GiB"},
            {"B16ps v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "16", "0.607", "64 GiB", "0 GiB"},
            {"B2ts v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "2", "0.0118", "1 GiB", "0 GiB"},
            {"B2ls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "2", "0.047", "4 GiB", "0 GiB"},
            {"B2s v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "2", "0.094", "8 GiB", "0 GiB"},
            {"B4ls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "4", "0.167", "8 GiB", "0 GiB"},
            {"B4s v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "4", "0.188", "16 GiB", "0 GiB"},
            {"B8ls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "8", "0.333", "16 GiB", "0 GiB"},
            {"B8s v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "8", "0.376", "32 GiB", "0 GiB"},
            {"B16ls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "16", "0.666", "32 GiB", "0 GiB"},
            {"B16s v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "16", "0.752", "64 GiB", "0 GiB"},
            {"B32ls v2", "Central US", "CentOS or Ubuntu Linux", "General purpose", "32", "1.332", "64 GiB", "0 GiB"}
        };
        
        for (String[] row : rawData) {
            CloudInstanceData instance = CloudInstanceData.builder()
                .instanceName(row[0])
                .region(row[1])
                .operatingSystem(row[2])
                .instanceType(row[3])
                .vcpu(Integer.parseInt(row[4]))
                .onDemandHourlyRate(Double.parseDouble(row[5]))
                .memory(row[6])
                .temporaryStorage(row[7])
                .build();
            
            testData.add(instance);
        }
        
        return testData;
    }
} 