
<h1 align="center">内部知识库管理平台</h1>

<p align="center">专注于私有化部署的在线知识库管理平台，支持大模型问答等</p>

# 项目简介
前台基于开源项目zyplayer-doc

# 项目改造进度
- pdf等各类解析（可以参考各类知识库）
- 文件上传下载-引入文件存储
- 大模型接入
- docker部署
- 前端改造
    - 界面优化改造
    - 界面重定向问题
    - 主界面除wiki外增加一个文件管理模块
    - 添加大模型模块，可维护知识库以及本地大模型、云端等，和上面文件管理模块可以互动

# 快速启动
## 相关依赖
使用docker部署

```shell
docker-compose up -d
```

启动本系统仅依赖JAVA和MySQL
- JAVA 1.8+
- MySQL 5.7.x、8.x
预计引入python等相关库和中间件

数据库安装成功后，需要**手动创建**一个库：`zyplayer_doc`,文件存储需要手动创建bucket:`doc-llm`，并配置好权限
```sql
-- 建库语句
create database zyplayer_doc;
```

> 建表SQL脚本无需手动执行，每次启动或更新之后都会检查当前版本，然后自动执行升级SQL脚本，所以每次有版本更新需求只需要下载最新版本启动即可，无需其他特殊操作


## Main方法启动
1. 修改`zyplayer-doc-manage/src/main/resources/application.yml`配置文件里面的数据库账号密码
2. 执行`org.dromara.zyplayer.manage.Application.main`方法启动项目


## 其他
更多启动方式请参考文档：[项目下载与部署](http://doc.zyplayer.com/#/integrate/zyplayer-doc/opensource/279)

启动后访问：[http://127.0.0.1:8083](http://127.0.0.1:8083) ，默认登录账号： **zyplayer**  密码： **123456**


# 开源协议
zyplayer-doc 是一个基于Apache License 2.0协议的开源项目，您可以自由使用、复制、修改和分发本软件的源代码和二进制形式。
如需二次开发请必须遵循以下条款：
1. **禁止**隐藏关于弹窗，关于弹窗中必须申明基于 zyplayer-doc 开源项目进行修改和二次开发。
2. **禁止**隐藏控制台输出的版权申明，如需修改必须申明基于 zyplayer-doc 开源项目进行修改和二次开发。

我们不希望将协议改为AGPL，导致大家无法进行二次开发和使用，所以仅包含两项强制性的无实质影响的附加条款。
这是促使我们继续更新开源项目最基本的诉求，同时也希望大家能够尊重我们的劳动成果。
如果您无法满足以上条款，请勿使用本项目，如有侵权行为，我们将保留追究法律责任的权利。
