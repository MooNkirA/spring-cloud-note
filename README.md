# spring-cloud-note

## 项目介绍

`spring-cloud-note`项目是用于学习 Spring Cloud 技术栈的示例项目。

## 本项目的分支说明

- `master`：主分支，是可用的、稳定的、可直接发布的版本，不在该分支上开发。各个版本示例完成后合并到develop分支上，而且测试没有问题了再合并到master上
- `develop`：开发主分支，代码永远是最新，所有新功能以这个分支来创建自己的开发分支，该分支只做只合并操作，不在此分支上开发
- `feature-xxx`：功能开发分支，在develop上创建分支，以Spring Cloud的大版本为模块命名，按每个版本完成相应的基础使用示例。功能测试正常后合并到develop分支

## 项目目录结构

```
spring-cloud-note
 ├── spring-cloud-greenwich-sample  # Spring Cloud Greenwich版本的示例项目
 └── spring-cloud-alibaba-2.1.x-sample # Spring Cloud Alibaba 2.1.x 版本的使用示例
```

