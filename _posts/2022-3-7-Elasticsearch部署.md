---
layout: post
author: LIU,HONGYANG
tags: [Elasticsearch]
---

本文从零开始部署Elasticsearch，Elastcicsearch版本为`elasticsearch-8.0.1-linux-x86_64.tar.gz`，部署环境为centos 7，部署工具为进程管理工`supervisor`。
[下载地址](https://www.elastic.co/cn/downloads/elasticsearch)

## 前置条件(仅针对自己手动部署)

在部署之前，我们首先创建一个Elasticsearch的用户，叫做`es`，并且赋予`es`用户root权限，正常情况下是不需要赋予这么高的权限的，但是为了便于后期的使用，就一次性将其添加到`root`组

执行命令如下：
```shell
useradd es
passwd es
userdel -r es #如果错了，可以先删除

vim /etc/sudoers
```
编辑文件，
![](https://tva1.sinaimg.cn/large/e6c9d24ely1h01iupecwsj20pz05bq3r.jpg)

在后面添加
```shell
es ALL=(ALL) ALL #为es赋予类似root的权限
```
## 正式部署

前置条件大功告成，即可正式配置Elasticsearch
在云存储中选取需要部署的包，本次选取es版本，为7.8.0
![](https://tva1.sinaimg.cn/large/e6c9d24ely1h02fye7z7gj20z604s0sv.jpg)

通过`wget`方法下载，主要注意的是，s2链接中会存在特殊字符，所以需要能够添加上单引号。

```shell
sudo rpm -i elasticsearch.rpm
```

下载安装后，查看一下system配置文件， 具体路径如下，进入
`/usr/lib/systemd/system` 路径，查看`elasticsearch.service`文件(也有可能在`/etc/systemd`目录下)

```shell
Environment=ES_HOME=/usr/share/elasticsearch
Environment=ES_PATH_CONF=/etc/elasticsearch
Environment=PID_DIR=/var/run/elasticsearch
Environment=ES_SD_NOTIFY=true
EnvironmentFile=-/etc/sysconfig/elasticsearch
```

1.`system`启动文件修改

环境变量中，修改ExecStart变量的值,修改为直接启动

```shell
ExecStart=/usr/share/elasticsearch/bin/elasticsearch
```

2.`elasticsearch`启动ip修改
进入`/etc/elasticsearch`，修改`elasticsearch.yml`文件，
将network.host设置为`0.0.0.0`，最后需要设置一个`discovery.seed_hosts: ["172.18.2.39"]`，
设置为本机ip即可。

3.`Java_Home`，`堆`等环境变量修改
elasticsearch配置自己的堆内存变量，修改`/etc/sysconfig`下的elasitcsearch

4.启动`systemctl start elasticsearch`

通过浏览器访问，返回对应的信息，可以认为配置成功
![](https://tva1.sinaimg.cn/large/e6c9d24ely1h02eqkue1rj20rq0lmgob.jpg)

## 基础使用


- **索引 - 创建**

创建索引:`PUT`

put无法两次创建，相同索引值

```
172.18.2.39:9200/shopping
```

- **查询 & 删除**

删除索引：`DELETE`

```
172.18.2.39:9200/shopping
```

查询当前索引内容：`GET`
```
172.18.2.39:9200/_cat/indices?v
```

- **创建**

创建文档`POST`，注意POST请求并非幂等性，

```
172.18.2.39:9200/shopping/_doc
```
直接创建，则会随机生成ip,
![](https://tva1.sinaimg.cn/large/e6c9d24ely1h02hiycigzj215d0u00w7.jpg)


```
172.18.2.39:9200/shopping/_doc/1001
```
![](https://tva1.sinaimg.cn/large/e6c9d24ely1h02ho3k1x5j21480u0juq.jpg)

或者采用`创建`的方式，

```
172.18.2.39:9200/shopping/_create/1003
```

![](https://tva1.sinaimg.cn/large/e6c9d24ely1h02hrkekk8j21ed0u0dk4.jpg)


- **主键查询 & 全查询**

查询`GET`

单个查询，指定`id`
```
172.18.2.39:9200/shopping/_doc/1002
```

全部查询
```
172.18.2.39:9200/shopping/_doc/_search
```

- **全局修改 & 局部修改**

全量覆盖，`PUT`, 利用幂等性

```
172.18.2.39:9200/shopping/_doc/1001
```

局部修改，添加一个`_update`，表示修改，方法使用`POST`

```
172.18.2.39:9200/shopping/_update/1001
```

```
{
  "doc" :{
    "title" : "华为手机"
  }
}
```
