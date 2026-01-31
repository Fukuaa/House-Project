# House-Project

这是我 **2022 年大四下学期的毕业设计**。

一个基于 Spring Boot 的简易房产管理系统，包含登录/注册、房源管理、用户管理、图片上传等功能，前端使用 Thymeleaf 模板。

## 技术栈

- Java 8
- Spring Boot 2.6.3
- MyBatis
- Thymeleaf
- MySQL
- Lombok

## 功能概览

- 登录 / 注册
- 房源列表展示
- 房源新增 / 修改 / 删除
- 用户信息修改
- 管理员用户管理
- 图片上传并保存到本地目录

## 运行环境

- JDK 1.8
- Maven 3.x
- MySQL 5.7+ / 8.0+

## 快速开始

### 1) 数据库初始化

导入项目根目录的 `house-project.sql`：

```sql
CREATE DATABASE `house-project` ...
```

默认库名：`house-project`

### 2) 配置数据库

配置在 `src/main/resources/application.yml`，也可用环境变量覆盖：

- `DB_URL`
- `DB_USER`
- `DB_PASS`

### 3) 启动项目

```bash
mvn -q -DskipTests spring-boot:run
```

### 4) 访问系统

- 默认端口：`8081`
- 访问地址：http://localhost:8081

## 默认账号（初始化脚本）

- 管理员：admin / 123456
- 普通用户：demo / 123456

> 首次登录会自动将明文密码升级为加密存储。

## 图片上传说明

- 上传后文件保存至：`<项目根>/uploads/`
- 访问 URL：`/uploads/xxxx.jpg`
- 上传大小限制可在 `application.yml` 中调整：
  - `spring.servlet.multipart.max-file-size`
  - `spring.servlet.multipart.max-request-size`

## 日志

- 日志文件：`<项目根>/logs/house-project.log`
- 日志配置：`src/main/resources/logback-spring.xml`

## 目录结构（核心）

- `src/main/java/com/example/houseproject`：后端代码
- `src/main/resources/templates`：Thymeleaf 页面
- `src/main/resources/static`：静态资源
- `src/main/resources/mapper`：MyBatis XML
- `uploads/`：上传文件保存目录
- `logs/`：日志目录

## 备注

- 这是本科阶段作品，功能较为基础，主要用于展示 CRUD、登录鉴权和页面渲染流程。
- 若扩展为完整系统，建议补充：权限系统、分页、日志审计、异常处理等。
