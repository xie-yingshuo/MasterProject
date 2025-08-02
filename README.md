# 自适应微服务部署平台

## 项目简介

这是一个基于Java Spring Boot的智能微服务部署平台，能够根据用户需求和多云环境中的资源状态，智能选择部署位置并自动完成服务部署与监控。

## 技术栈

- **Java 17** - 主要开发语言
- **Spring Boot 3.2.0** - 应用框架
- **Spring Cloud 2023.0.0** - 微服务框架
- **Spring Cloud Gateway** - API网关
- **Eureka** - 服务注册与发现
- **Maven** - 项目构建工具
- **Docker** - 容器化部署
- **Kubernetes** - 容器编排
- **Prometheus** - 监控系统

## 项目结构

```
Project/
├── platform-parent/              # 父项目，依赖管理
├── platform-common/              # 公共模块
│   ├── model/                   # 通用模型
│   ├── constant/                # 常量定义
│   ├── exception/               # 异常处理
│   └── util/                    # 工具类
├── platform-api-gateway/        # API网关
├── platform-deployment-engine/  # 部署引擎
├── platform-resource-manager/   # 资源管理
└── platform-monitor/           # 监控服务
```

## 核心功能

### 1. 智能部署决策
- 基于资源利用率、成本、性能等多维度评估
- 机器学习算法优化部署策略
- 实时负载预测和容量规划

### 2. 多云环境管理
- 支持AWS、Azure、GCP、阿里云等主流云平台
- 统一的资源抽象层
- 跨云资源监控和状态同步

### 3. 自动化部署流水线
- CI/CD集成
- 蓝绿部署、金丝雀发布等策略
- 自动回滚机制

### 4. 监控和运维
- 分布式链路追踪
- 性能指标收集
- 告警和故障自愈

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- Docker (可选)
- Kubernetes (可选)

### 构建项目
```bash
# 克隆项目
git clone https://github.com/xie-yingshuo/MasterProject.git
cd MasterProject

# 编译项目
mvn clean compile

# 打包项目
mvn clean package
```

### 启动服务

1. **启动Eureka服务注册中心**
```bash
# 需要先启动Eureka Server (端口: 8761)
```

2. **启动API网关**
```bash
cd platform-api-gateway
mvn spring-boot:run
```

3. **启动部署引擎**
```bash
cd platform-deployment-engine
mvn spring-boot:run
```

4. **启动资源管理服务**
```bash
cd platform-resource-manager
mvn spring-boot:run
```

5. **启动监控服务**
```bash
cd platform-monitor
mvn spring-boot:run
```

## API文档

### 部署服务API

#### 创建部署
```http
POST /api/v1/deployment
Content-Type: application/json

{
  "serviceName": "my-service",
  "version": "1.0.0",
  "imageUrl": "docker.io/myapp:latest",
  "portMappings": [
    {
      "containerPort": 8080,
      "hostPort": 8080,
      "protocol": "TCP"
    }
  ],
  "environment": {
    "ENV": "production"
  },
  "resourceConfig": {
    "cpu": 1.0,
    "memory": "1Gi",
    "storage": "10Gi"
  },
  "strategy": {
    "type": "ROLLING",
    "replicas": 3,
    "autoScaling": true,
    "minReplicas": 1,
    "maxReplicas": 10
  },
  "targetCloud": "KUBERNETES"
}
```

#### 获取部署状态
```http
GET /api/v1/deployment/{deploymentId}
```

#### 取消部署
```http
DELETE /api/v1/deployment/{deploymentId}
```

## 开发计划

### 第一阶段：基础框架 ✅
- [x] 多模块Maven项目结构
- [x] Spring Boot微服务架构
- [x] API网关配置
- [x] 基础部署功能

### 第二阶段：智能决策引擎
- [ ] 资源评估算法
- [ ] 部署策略优化
- [ ] 成本分析功能

### 第三阶段：监控和运维
- [ ] 完整的监控体系
- [ ] 自动化运维
- [ ] 故障自愈机制

### 第四阶段：优化和扩展
- [ ] 性能优化
- [ ] 用户体验改进
- [ ] 更多云平台支持

## 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 联系方式

- 项目维护者：谢迎朔
- 邮箱：[xieyshuo01@163.com]
- 项目链接：[https://github.com/xie-yingshuo/MasterProject](https://github.com/xie-yingshuo/MasterProject) 
