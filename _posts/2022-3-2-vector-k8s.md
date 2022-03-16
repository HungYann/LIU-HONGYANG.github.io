---
layout: post
author: LIU,HONGYANG
tags: [大数据组件]
---
# Vector - k8s介绍

vector k8s是 vector容器化项目，Vector 是一种高性能的可观察性数据管道，可让组织控制其可观察性数据。收集、转换所有日志、指标和跟踪信息，并将其路由到您今天想要的任何供应商以及您明天可能想要的任何其他供应商。 Vector 可以在您需要的地方实现显着的成本降低、新颖的数据丰富和数据安全，而不是在您的供应商最方便的地方。开源，比所有替代方案快 10 倍。

**主要功能**

|Project | 功能|
|---|---|
| ic-default-kafka-s3 | ic default日志从kafka到s3 |
| ic-k8s-kafka-es | ic k8s日志从kafka到es |
| ic-k8s-kafka-s3 | ic k8s日志从kafka到s3 |
| parent-log-kafka-es | 父日志从kafka到es |


**构建方式**

1.创建新建平级目录，注意环境变量的设置，`CONFD_ENABLE`表示打开使用etcd，如果为0，代表不使用etcd。

```shell
env:
 CONFD_ENABLE: 1
 NOP: N
```

etcd主要用于外部变量的更新，外部变量监控`etcd`的值，如果`etcd`值更新，拉取的值每15分钟更新一次。


2.配置etcd外部值，通过读取etcd key值，进一步分割获得value

3.通过k8s创建项目，在`vector-k8s`项目中，clone配置, 填写创建项目的名字。注意，此处的配置名称，应该和源码中构建项目的目录名称一致。
![](https://tva1.sinaimg.cn/large/e6c9d24ely1h03oam5yimj21gy0h0acl.jpg)

修改项目的环境变量，注意`Source Code Checkout`应该和Bitbucket上的`Vector-k8s`保持一致。



# 学习参考

- vector[官方文档](https://vector.dev/docs/about/what-is-vector/)
- vector[源码](https://github.com/vectordotdev/vector)
