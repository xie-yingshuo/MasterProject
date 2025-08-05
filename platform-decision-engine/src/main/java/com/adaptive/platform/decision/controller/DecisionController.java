package com.adaptive.platform.decision.controller;

import com.adaptive.platform.common.response.Result;
import com.adaptive.platform.decision.model.DecisionRequest;
import com.adaptive.platform.decision.model.DecisionResponse;
import com.adaptive.platform.decision.service.DecisionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 智能决策引擎控制器
 */
@Slf4j
@RestController
@RequestMapping("/decision")
@RequiredArgsConstructor
public class DecisionController {
    
    private final DecisionService decisionService;
    
    /**
     * 获取部署建议
     */
    @PostMapping("/recommend")
    public Result<DecisionResponse> getDeploymentRecommendation(@Valid @RequestBody DecisionRequest request) {
        log.info("收到决策请求: {}", request);
        DecisionResponse response = decisionService.getDeploymentRecommendation(request);
        return Result.success(response);
    }
    
    /**
     * 获取策略分析
     */
    @PostMapping("/analyze")
    public Result<Object> analyzeStrategy(@Valid @RequestBody DecisionRequest request) {
        Object analysis = decisionService.analyzeStrategy(request);
        return Result.success(analysis);
    }
    
    /**
     * 获取成本预测
     */
    @PostMapping("/cost-prediction")
    public Result<Object> predictCost(@Valid @RequestBody DecisionRequest request) {
        Object prediction = decisionService.predictCost(request);
        return Result.success(prediction);
    }
} 