-- 创建数据库
CREATE DATABASE IF NOT EXISTS platform_deployment;
CREATE DATABASE IF NOT EXISTS platform_resource;
CREATE DATABASE IF NOT EXISTS platform_monitor;
CREATE DATABASE IF NOT EXISTS platform_decision;

-- 使用部署引擎数据库
USE platform_deployment;

-- 部署任务表
CREATE TABLE IF NOT EXISTS deployment_task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL COMMENT '任务名称',
    service_name VARCHAR(255) NOT NULL COMMENT '服务名称',
    service_version VARCHAR(50) NOT NULL COMMENT '服务版本',
    cloud_provider VARCHAR(50) NOT NULL COMMENT '云提供商',
    region VARCHAR(100) NOT NULL COMMENT '部署区域',
    instance_type VARCHAR(100) NOT NULL COMMENT '实例类型',
    instance_count INT NOT NULL DEFAULT 1 COMMENT '实例数量',
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING' COMMENT '任务状态',
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 使用资源管理器数据库
USE platform_resource;

-- 云资源表
CREATE TABLE IF NOT EXISTS cloud_resource (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    provider VARCHAR(50) NOT NULL COMMENT '云提供商',
    region VARCHAR(100) NOT NULL COMMENT '区域',
    instance_type VARCHAR(100) NOT NULL COMMENT '实例类型',
    available_count INT NOT NULL DEFAULT 0 COMMENT '可用数量',
    total_count INT NOT NULL DEFAULT 0 COMMENT '总数量',
    price_per_hour DECIMAL(10,4) NOT NULL DEFAULT 0 COMMENT '每小时价格',
    cpu_cores INT NOT NULL DEFAULT 0 COMMENT 'CPU核心数',
    memory_gb INT NOT NULL DEFAULT 0 COMMENT '内存GB',
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 使用监控中心数据库
USE platform_monitor;

-- 监控指标表
CREATE TABLE IF NOT EXISTS monitor_metric (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(255) NOT NULL COMMENT '服务名称',
    instance_id VARCHAR(255) NOT NULL COMMENT '实例ID',
    metric_name VARCHAR(100) NOT NULL COMMENT '指标名称',
    metric_value DECIMAL(10,4) NOT NULL COMMENT '指标值',
    metric_unit VARCHAR(20) NOT NULL COMMENT '指标单位',
    collect_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '采集时间'
);

-- 使用决策引擎数据库
USE platform_decision;

-- 决策记录表
CREATE TABLE IF NOT EXISTS decision_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(255) NOT NULL COMMENT '服务名称',
    decision_type VARCHAR(50) NOT NULL COMMENT '决策类型',
    decision_result TEXT NOT NULL COMMENT '决策结果',
    factors TEXT COMMENT '决策因素',
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
); 