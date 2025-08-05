# API文档

## 部署引擎API

### 创建部署任务
```http
POST /api/deployment/create
Content-Type: application/json

{
  "serviceName": "user-service",
  "serviceVersion": "1.0.0",
  "cpuRequirement": 4,
  "memoryRequirement": 8,
  "instanceCount": 2,
  "deploymentStrategy": "cost-optimized"
}
```

### 获取部署状态
```http
GET /api/deployment/{deploymentId}/status
```

### 取消部署
```http
POST /api/deployment/{deploymentId}/cancel
```

## 资源管理器API

### 获取可用资源
```http
GET /api/resource/available?region=us-east-1&instanceType=t2.micro
```

### 获取资源使用情况
```http
GET /api/resource/usage?provider=aws
```

## 决策引擎API

### 获取部署建议
```http
POST /api/decision/recommend
Content-Type: application/json

{
  "serviceName": "user-service",
  "cpuRequirement": 4,
  "memoryRequirement": 8,
  "preferredStrategy": "balanced"
}
```

## Demo客户端API

### 获取云实例数据
```http
GET /api/demo/cloud-instances
```

### 运行性能测试
```http
POST /api/demo/performance-test
Content-Type: application/json

{
  "testType": "performance",
  "iterations": 10
}
```

### 运行成本优化测试
```http
POST /api/demo/cost-optimization-test
Content-Type: application/json

{
  "testType": "cost-optimization",
  "strategies": ["cost-optimized", "performance-optimized", "balanced"]
}
``` 