#!/usr/bin/env python3
"""
自适应微服务部署平台测试脚本
用于生成测试请求和验证系统功能
"""

import requests
import json
import time
import pandas as pd
from datetime import datetime
import matplotlib.pyplot as plt
import seaborn as sns

class DeploymentPlatformTester:
    
    def __init__(self, base_url="http://localhost:8080"):
        self.base_url = base_url
        self.results = []
        
    def test_deployment_request(self, request_data):
        """测试部署请求"""
        try:
            response = requests.post(
                f"{self.base_url}/api/demo/deploy",
                json=request_data,
                headers={"Content-Type": "application/json"}
            )
            return response.json()
        except Exception as e:
            print(f"请求失败: {e}")
            return None
    
    def test_performance_comparison(self):
        """性能对比测试"""
        print("开始性能对比测试...")
        
        strategies = ["cost-optimized", "performance-optimized", "balanced"]
        test_config = {"testType": "performance"}
        
        for strategy in strategies:
            request_data = {
                "serviceName": f"test-service-{strategy}",
                "serviceVersion": "1.0.0",
                "cpuRequirement": 4,
                "memoryRequirement": 8,
                "instanceCount": 2,
                "deploymentStrategy": strategy
            }
            
            start_time = time.time()
            result = self.test_deployment_request(request_data)
            end_time = time.time()
            
            if result and result.get("code") == 200:
                data = result.get("data", {})
                self.results.append({
                    "strategy": strategy,
                    "deploymentTime": (end_time - start_time) * 1000,
                    "cost": data.get("totalCostPerHour", 0),
                    "instanceType": data.get("selectedInstanceType", ""),
                    "status": data.get("status", "")
                })
                print(f"策略 {strategy}: 成本=${data.get('totalCostPerHour', 0):.4f}/小时")
        
        return self.results
    
    def test_cost_optimization(self):
        """成本优化测试"""
        print("开始成本优化测试...")
        
        # 测试不同规模的部署
        test_scenarios = [
            {"cpu": 2, "memory": 4, "instances": 1, "name": "small"},
            {"cpu": 4, "memory": 8, "instances": 2, "name": "medium"},
            {"cpu": 8, "memory": 16, "instances": 3, "name": "large"}
        ]
        
        for scenario in test_scenarios:
            request_data = {
                "serviceName": f"cost-test-{scenario['name']}",
                "serviceVersion": "1.0.0",
                "cpuRequirement": scenario["cpu"],
                "memoryRequirement": scenario["memory"],
                "instanceCount": scenario["instances"],
                "deploymentStrategy": "cost-optimized"
            }
            
            result = self.test_deployment_request(request_data)
            if result and result.get("code") == 200:
                data = result.get("data", {})
                print(f"{scenario['name']}规模: 成本=${data.get('totalCostPerHour', 0):.4f}/小时")
    
    def test_resource_efficiency(self):
        """资源效率测试"""
        print("开始资源效率测试...")
        
        # 测试不同资源配置的效率
        resource_configs = [
            {"cpu": 2, "memory": 1, "ratio": "2:1"},
            {"cpu": 4, "memory": 4, "ratio": "1:1"},
            {"cpu": 8, "memory": 16, "ratio": "1:2"},
            {"cpu": 16, "memory": 32, "ratio": "1:2"}
        ]
        
        for config in resource_configs:
            request_data = {
                "serviceName": f"efficiency-test-{config['ratio']}",
                "serviceVersion": "1.0.0",
                "cpuRequirement": config["cpu"],
                "memoryRequirement": config["memory"],
                "instanceCount": 1,
                "deploymentStrategy": "balanced"
            }
            
            result = self.test_deployment_request(request_data)
            if result and result.get("code") == 200:
                data = result.get("data", {})
                efficiency = config["cpu"] / data.get("totalCostPerHour", 1)
                print(f"配置 {config['ratio']}: 效率={efficiency:.2f} vCPU/$")
    
    def generate_test_report(self):
        """生成测试报告"""
        if not self.results:
            print("没有测试结果可生成报告")
            return
        
        df = pd.DataFrame(self.results)
        
        # 创建图表
        fig, axes = plt.subplots(2, 2, figsize=(15, 10))
        
        # 成本对比
        axes[0, 0].bar(df['strategy'], df['cost'])
        axes[0, 0].set_title('不同策略的成本对比')
        axes[0, 0].set_ylabel('每小时成本 ($)')
        
        # 部署时间对比
        axes[0, 1].bar(df['strategy'], df['deploymentTime'])
        axes[0, 1].set_title('不同策略的部署时间对比')
        axes[0, 1].set_ylabel('部署时间 (ms)')
        
        # 成本效率散点图
        efficiency = df['cost'].apply(lambda x: 1/x if x > 0 else 0)
        axes[1, 0].scatter(df['cost'], efficiency)
        axes[1, 0].set_xlabel('成本 ($/小时)')
        axes[1, 0].set_ylabel('成本效率 (1/成本)')
        axes[1, 0].set_title('成本与效率关系')
        
        # 策略分布饼图
        status_counts = df['status'].value_counts()
        axes[1, 1].pie(status_counts.values, labels=status_counts.index, autopct='%1.1f%%')
        axes[1, 1].set_title('部署状态分布')
        
        plt.tight_layout()
        plt.savefig('test_report.png', dpi=300, bbox_inches='tight')
        print("测试报告已保存为 test_report.png")
        
        # 保存数据
        df.to_csv('test_results.csv', index=False)
        print("测试数据已保存为 test_results.csv")
        
        return df
    
    def run_comprehensive_test(self):
        """运行综合测试"""
        print("=" * 50)
        print("自适应微服务部署平台综合测试")
        print("=" * 50)
        
        # 1. 性能对比测试
        self.test_performance_comparison()
        print()
        
        # 2. 成本优化测试
        self.test_cost_optimization()
        print()
        
        # 3. 资源效率测试
        self.test_resource_efficiency()
        print()
        
        # 4. 生成报告
        print("生成测试报告...")
        report_df = self.generate_test_report()
        
        print("=" * 50)
        print("测试完成！")
        print("=" * 50)
        
        return report_df

def main():
    """主函数"""
    tester = DeploymentPlatformTester()
    
    # 运行综合测试
    results = tester.run_comprehensive_test()
    
    # 打印总结
    if results is not None and not results.empty:
        print("\n测试总结:")
        print(f"- 测试策略数量: {len(results)}")
        print(f"- 平均成本: ${results['cost'].mean():.4f}/小时")
        print(f"- 平均部署时间: {results['deploymentTime'].mean():.2f}ms")
        print(f"- 成功率: {(results['status'] == 'SUCCESS').mean() * 100:.1f}%")

if __name__ == "__main__":
    main() 