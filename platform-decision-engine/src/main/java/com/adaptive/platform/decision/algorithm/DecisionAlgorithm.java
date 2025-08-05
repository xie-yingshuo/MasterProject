package com.adaptive.platform.decision.algorithm;

import com.adaptive.platform.decision.model.DecisionRequest;
import com.adaptive.platform.decision.model.DecisionResponse;

import java.util.List;
import java.util.Map;

/**
 * 决策算法接口
 */
public interface DecisionAlgorithm {
    
    /**
     * 多目标优化算法
     */
    DecisionResponse optimizeDeployment(DecisionRequest request);
    
    /**
     * 成本优化算法
     */
    DecisionResponse optimizeCost(DecisionRequest request);
    
    /**
     * 性能优化算法
     */
    DecisionResponse optimizePerformance(DecisionRequest request);
    
    /**
     * 平衡优化算法
     */
    DecisionResponse optimizeBalanced(DecisionRequest request);
    
    /**
     * 获取算法评分
     */
    Map<String, Double> getAlgorithmScore(DecisionRequest request);
    
    /**
     * 获取推荐策略
     */
    List<String> getRecommendedStrategies(DecisionRequest request);
} 