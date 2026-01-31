# House-Project

这是我 **2022 年大四下学期的毕业设计**。

一个基于 Spring Boot 的简易房产管理系统，包含登录/注册、房源管理、用户管理等功能，前端使用 Thymeleaf 模板。

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

## 如何运行

1. **准备数据库**
   - 确保本机已安装 MySQL。
   - 导入项目根目录的 `house-project.sql`。

2. **配置数据库连接**
   - 默认配置在 `src/main/resources/application.yml`。
   - 也可以使用环境变量覆盖：
     - `DB_URL`
     - `DB_USER`
     - `DB_PASS`

3. **启动项目**
   ```bash
   mvn -q -DskipTests spring-boot:run
   ```

4. **访问页面**
   - 默认端口：`8081`
   - 地址：http://localhost:8081

## 默认账号（来自初始化脚本）

- 管理员：admin / 123456
- 普通用户：demo / 123456

> 首次登录会自动将明文密码升级为加密存储。

## 目录结构（核心）

- `src/main/java/com/example/houseproject`：后端代码
- `src/main/resources/templates`：Thymeleaf 页面
- `src/main/resources/static`：静态资源
- `src/main/resources/mapper`：MyBatis XML

## 备注

- 这是本科阶段的完整作品，功能偏简单，主要用于展示 CRUD、登录鉴权和页面渲染流程。
- 若要扩展为完整商用系统，建议补充：权限系统、分页、日志与审计等功能。
