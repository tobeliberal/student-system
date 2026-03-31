# 学生信息管理系统

基于 Java Swing + SQLite 开发的学生信息管理系统，支持管理员、教师、学生三种角色的登录与操作。

## 项目简介

本项目是一个桌面端学生信息管理系统，实现了学生信息管理、教师管理、课程管理、选课系统等核心功能。采用 SQLite 轻量级数据库存储数据，无需额外配置数据库服务器。

## 功能特性

### 管理员功能
- 添加/删除/查找学生
- 添加/删除/查找教师
- 添加/删除/查找课程
- 查看所有学生、教师、课程列表

### 教师功能
- 查看本班学生信息
- 修改学生信息（姓名、年龄、班级、成绩等）
- 添加/删除学生
- 查看学生的修改申请
- 查看任课信息
- 按ID查找学生

### 学生功能
- 查看个人信息
- 查看成绩（高数、大英、Java）
- 在线选课（最多3门课程）
- 重新选课
- 查看选课信息
- 申请修改个人信息

## 技术栈

| 技术 | 说明 |
|------|------|
| Java | 开发语言 |
| Swing | GUI 图形界面 |
| SQLite | 轻量级数据库 |
| JDBC | 数据库连接 |

## 项目结构

```
_student_system/
├── src/
│   └── main/
│       ├── GUI/                    # 图形界面
│       │   ├── Login_GUI.java      # 登录界面
│       │   ├── Register_GUI.java   # 注册界面
│       │   ├── Admin_GUI.java      # 管理员界面
│       │   ├── Teacher_GUI.java    # 教师界面
│       │   └── Student_GUI.java    # 学生界面
│       ├── model/                  # 数据模型
│       │   ├── Student.java        # 学生实体类
│       │   ├── Teacher.java        # 教师实体类
│       │   ├── Course.java         # 课程实体类
│       │   ├── Admin.java          # 管理员实体类
│       │   └── BackgroundPanel.java # 背景面板
│       ├── _System.java            # 系统核心逻辑
│       └── main.java               # 程序入口
├── lib/
│   └── sqlite-jdbc-3.7.2.jar       # SQLite JDBC 驱动
├── _student_system.db              # SQLite 数据库文件
└── pom.xml                         # Maven 配置
```

## 快速开始

### 环境要求
- JDK 8 或更高版本
- Maven（可选）

### 运行方式

**方式一：使用 IDE 运行**
1. 使用 IntelliJ IDEA 打开项目
2. 将 `lib/sqlite-jdbc-3.7.2.jar` 添加为项目库
3. 运行 `src/main/main.java`

**方式二：命令行运行**
```bash
# 编译
javac -cp lib/sqlite-jdbc-3.7.2.jar -d out src/main/*.java src/main/GUI/*.java src/main/model/*.java

# 运行
java -cp out;lib/sqlite-jdbc-3.7.2.jar main.main
```

### 默认账号

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 管理员 | 范泽峥 | 1 |
| 教师/学生 | 需注册 | - |

## 数据库设计

### 学生表 (students)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键，自增 |
| name | TEXT | 姓名 |
| password | TEXT | 密码 |
| age | INTEGER | 年龄 |
| className | TEXT | 班级 |
| phoneNumber | TEXT | 手机号 |
| grade1 | TEXT | 高数成绩 |
| grade2 | TEXT | 大英成绩 |
| grade3 | TEXT | Java成绩 |

### 教师表 (teachers)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键，自增 |
| name | TEXT | 姓名 |
| password | TEXT | 密码 |
| age | INTEGER | 年龄 |
| className | TEXT | 所教班级 |
| phoneNumber | TEXT | 手机号 |
| courseInfo | TEXT | 所教课程 |

### 课程表 (courses)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键，自增 |
| name | TEXT | 课程名称 |
| teacher | TEXT | 授课教师 |

## 运行截图

登录界面支持三种角色切换登录，系统会根据角色自动跳转到对应的功能界面。

## 开发者

大学课程设计项目

## 许可证

MIT License
