#!/bin/bash

echo "启动自适应微服务部署平台..."

# 检查Docker是否运行
if ! docker info > /dev/null 2>&1; then
    echo "错误: Docker未运行，请先启动Docker"
    exit 1
fi

# 启动基础设施服务
echo "启动基础设施服务..."
docker-compose up -d

# 等待服务启动
echo "等待基础设施服务启动..."
sleep 30

# 编译项目
echo "编译项目..."
mvn clean install -DskipTests

# 启动服务注册中心
echo "启动服务注册中心..."
cd platform-service-registry
mvn spring-boot:run &
cd ..

sleep 10

# 启动配置中心
echo "启动配置中心..."
cd platform-config-server
mvn spring-boot:run &
cd ..

sleep 10

# 启动API网关
echo "启动API网关..."
cd platform-api-gateway
mvn spring-boot:run &
cd ..

sleep 10

# 启动部署引擎
echo "启动部署引擎..."
cd platform-deployment-engine
mvn spring-boot:run &
cd ..

sleep 10

# 启动资源管理器
echo "启动资源管理器..."
cd platform-resource-manager
mvn spring-boot:run &
cd ..

sleep 10

# 启动监控中心
echo "启动监控中心..."
cd platform-monitor
mvn spring-boot:run &
cd ..

sleep 10

# 启动决策引擎
echo "启动决策引擎..."
cd platform-decision-engine
mvn spring-boot:run &
cd ..

sleep 10

# 启动Demo客户端
echo "启动Demo客户端..."
cd platform-demo-client
mvn spring-boot:run &
cd ..

echo "所有服务启动完成！"
echo ""
echo "服务访问地址："
echo "- 服务注册中心: http://localhost:8761"
echo "- 配置中心: http://localhost:8888"
echo "- API网关: http://localhost:8080"
echo "- 部署引擎: http://localhost:8081"
echo "- 资源管理器: http://localhost:8082"
echo "- 监控中心: http://localhost:8083"
echo "- 决策引擎: http://localhost:8084"
echo "- Demo客户端: http://localhost:8085"
echo ""
echo "监控地址："
echo "- Prometheus: http://localhost:9090"
echo "- Grafana: http://localhost:3000 (admin/admin)"
echo "- RabbitMQ管理界面: http://localhost:15672 (admin/admin)"
echo ""
echo "按 Ctrl+C 停止所有服务" 