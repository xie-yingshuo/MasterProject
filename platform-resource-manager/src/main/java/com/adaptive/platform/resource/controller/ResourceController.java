package com.adaptive.platform.resource.controller;

import com.adaptive.platform.common.response.Result;
import com.adaptive.platform.resource.model.CloudResource;
import com.adaptive.platform.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源管理器控制器
 */
@Slf4j
@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {
    
    private final ResourceService resourceService;
    
    /**
     * 获取可用资源列表
     */
    @GetMapping("/available")
    public Result<List<CloudResource>> getAvailableResources(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String instanceType) {
        List<CloudResource> resources = resourceService.getAvailableResources(region, instanceType);
        return Result.success(resources);
    }
    
    /**
     * 获取资源使用情况
     */
    @GetMapping("/usage")
    public Result<Object> getResourceUsage(@RequestParam String provider) {
        Object usage = resourceService.getResourceUsage(provider);
        return Result.success(usage);
    }
    
    /**
     * 更新资源信息
     */
    @PostMapping("/update")
    public Result<Void> updateResourceInfo(@RequestBody CloudResource resource) {
        resourceService.updateResourceInfo(resource);
        return Result.success();
    }
} 